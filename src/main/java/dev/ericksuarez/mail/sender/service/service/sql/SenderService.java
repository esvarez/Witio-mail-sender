package dev.ericksuarez.mail.sender.service.service.sql;

import dev.ericksuarez.mail.sender.service.model.SenderDto;
import dev.ericksuarez.mail.sender.service.model.entity.MailingList;
import dev.ericksuarez.mail.sender.service.model.entity.Process;
import dev.ericksuarez.mail.sender.service.model.entity.Recipient;
import dev.ericksuarez.mail.sender.service.repository.sql.MailingListRepository;
import dev.ericksuarez.mail.sender.service.repository.sql.RecipientRepository;
import dev.ericksuarez.mail.sender.service.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@Profile("relational")
public class SenderService {

    private ProcessService processService;

    private RecipientRepository recipientRepository;

    private EmailService emailService;

    @Autowired
    public SenderService(ProcessService processService, RecipientRepository recipientRepository, EmailService emailService) {
        this.processService = processService;
        this.emailService = emailService;
        this.recipientRepository = recipientRepository;
    }

    public String sendMails(SenderDto senderDto) {
        log.info("event=sendMailsInvoked senderDto={}", senderDto);
        var process = processService.getProcessById(senderDto.getProcessId());
        String message = replacePlaceholders(process.getMessage(), senderDto.getPlaceHolder());

        var recipientsList = recipientRepository.findByProcessId(senderDto.getProcessId());

        String[] recipients = getMailsToRecipientsList(recipientsList);

        if (!StringUtils.isEmpty(senderDto.getEmails())) {
            String[] bcc = Arrays.stream(senderDto.getEmails().split(";"))
                    .toArray(String[]::new);
            emailService.sendMessageBcc(recipients, bcc, process.getName(), message, senderDto.isReply(), senderDto.getReplyTo());
        } else {
            emailService.sendMessage(recipients, process.getName(), message, senderDto.isReply(), senderDto.getReplyTo());
        }

        return message;
    }

    private String replacePlaceholders(String template, Map<String, String> placeHolders){
        log.info("event=replacePlaceholdersInvoked, template={}, placeHolders={}", template, placeHolders);
        StringBuilder message = new StringBuilder(template);
        placeHolders.forEach((key, value) -> {
            int index = message.indexOf("%" + key.toLowerCase() + "%");
            while (index != -1) {
                message.replace(index, index + key.length() + 2, value);
                index += value.length();
                index = message.indexOf("%" + key + "%", index);
            }
        });
        return message.toString();
    }

    private String[] getMailsToRecipientsList(Set<Recipient> recipientsList) {
        if (recipientsList.isEmpty()){
            throw new RuntimeException("The MailsList is empty");
        }
        return recipientsList.stream()
                .map(getMailFromRecipient)
                .toArray(String[]::new);
    }

    private Function<Recipient, String> getMailFromRecipient = recipient -> recipient.getEmail();
}
