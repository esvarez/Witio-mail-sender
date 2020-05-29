package dev.ericksuarez.mail.sender.service.service;

import dev.ericksuarez.mail.sender.service.model.MailSendDto;
import dev.ericksuarez.mail.sender.service.model.SenderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Slf4j
@Service
public class EmailService implements EmailSender {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(MailSendDto mailSendDto) {

        if (!StringUtils.isEmpty(mailSendDto.getEmails())) {
            String[] bcc = Arrays.stream(mailSendDto.getEmails().split(";"))
                    .toArray(String[]::new);
            sendMessageBcc(mailSendDto.getRecipients(), bcc, mailSendDto.getSubject(), mailSendDto.getMessage(),
                    mailSendDto.isReply(), mailSendDto.getReplyTo());
        } else {
            sendMessage(mailSendDto.getRecipients(), mailSendDto.getSubject(), mailSendDto.getMessage(),
                    mailSendDto.isReply(), mailSendDto.getReplyTo());
        }

    }

    @Override
    public void sendMessage(String[] to, String subject, String text, boolean reply, String replyTo) {
        try {
            var message = new SimpleMailMessage();
            message.setFrom("no-replay@ericksuarez.dev");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            setReplyTo(message, reply, replyTo);

            // javaMailSender.send(message);
            log.info("event=sendSimulation to={}", message.getTo());
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void sendMessageBcc(String[] to, String[] bcc, String subject, String text, boolean reply, String replyTo) {
        try {
            var message = new SimpleMailMessage();
            message.setTo(to);
            message.setBcc(bcc);
            message.setSubject(subject);
            message.setText(text);
            setReplyTo(message, reply, replyTo);

            // javaMailSender.send(message);
            log.info("event=sendSimulation to={}", message.getTo());
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

    private void setReplyTo(SimpleMailMessage message, boolean reply, String replyTo) {
        if (reply && !StringUtils.isEmpty(replyTo)){
            message.setReplyTo(replyTo);
        }
    }
}
