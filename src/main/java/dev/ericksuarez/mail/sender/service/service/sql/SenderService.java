package dev.ericksuarez.mail.sender.service.service.sql;

import dev.ericksuarez.mail.sender.service.model.SenderDto;
import dev.ericksuarez.mail.sender.service.model.entity.MailingList;
import dev.ericksuarez.mail.sender.service.model.entity.Process;
import dev.ericksuarez.mail.sender.service.model.entity.Recipient;
import dev.ericksuarez.mail.sender.service.repository.sql.MailingListRepository;
import dev.ericksuarez.mail.sender.service.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private MailingListService mailingListService;

    private EmailService emailService;

    @Autowired
    public SenderService(ProcessService processService, MailingListService mailingListService, EmailService emailService) {
        this.processService = processService;
        this.emailService = emailService;
        this.mailingListService = mailingListService;
    }

    public String sendMails(SenderDto senderDto) {
        log.info("event=sendMailsInvoked senderDto={}", senderDto);
        var process = processService.getProcessById(senderDto.getProcess().getId());

        String message = replacePlaceholders(process.getMessage(), senderDto.getPlaceHolder());

        String[] recipients = getMailsToFromMailList(process.getMailingLists());

        emailService.sendMessageToMultipleTo(process.getName(), message, recipients);



        // Get Mails
        var token = new StringTokenizer(senderDto.getEmails(), ";");
        while (token.hasMoreTokens()) {
            token.nextToken();
        }

        if (senderDto.getReplyTo() != null && senderDto.isReply()){
            // TODO set reply
        }

        //TODO Send Mails
        System.out.println(message);
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

    private String[] getMailsToFromMailList(Set<MailingList> mailingLists) {
        return mailingLists.stream()
                .flatMap(getRecipients)
                .map(getMailFromRecipient)
                .toArray(String[]::new);
    }

    private Function<MailingList, Stream<Recipient>> getRecipients = mailingList -> mailingList.getRecipients().stream();

    private Function<Recipient, String> getMailFromRecipient = recipient -> recipient.getEmail();
}
