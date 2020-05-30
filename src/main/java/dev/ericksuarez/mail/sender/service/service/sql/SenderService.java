package dev.ericksuarez.mail.sender.service.service.sql;

import dev.ericksuarez.mail.sender.service.model.MailSendDto;
import dev.ericksuarez.mail.sender.service.model.SenderDto;
import dev.ericksuarez.mail.sender.service.repository.sql.RecipientRepository;
import dev.ericksuarez.mail.sender.service.service.EmailService;
import dev.ericksuarez.mail.sender.service.service.SendGridService;
import dev.ericksuarez.mail.sender.service.service.SenderServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@Profile("relational")
public class SenderService extends SenderServiceUtils {

    private ProcessService processService;

    private RecipientRepository recipientRepository;

    private EmailService emailService;

    private SendGridService sendGridService;

    @Autowired
    public SenderService(ProcessService processService, RecipientRepository recipientRepository,
                         EmailService emailService, SendGridService sendGridService) {
        this.processService = processService;
        this.emailService = emailService;
        this.recipientRepository = recipientRepository;
        this.sendGridService =  sendGridService;
    }

    public String sendMails(SenderDto senderDto) throws IOException {
        log.info("event=sendMailsInvoked senderDto={}", senderDto);
        var process = processService.getProcessById(Long.valueOf(senderDto.getProcessId()));
        String message = replacePlaceholders(process.getMessage(), senderDto.getPlaceHolder());

        var recipientsList = recipientRepository.findByProcessId(Long.valueOf(senderDto.getProcessId()));
        log.info("recipientsList={}", recipientsList);

        String[] recipients = getMailsToRecipientsList(recipientsList);

        var mail = MailSendDto.builder()
                .emails(senderDto.getEmails())
                .message(message)
                .recipients(recipients)
                .reply(senderDto.isReply())
                .replyTo(senderDto.getReplyTo())
                .subject(process.getName())
                .build();

        emailService.sendMessage(mail);
        sendGridService.sendMail(mail);

        return message;
    }
}
