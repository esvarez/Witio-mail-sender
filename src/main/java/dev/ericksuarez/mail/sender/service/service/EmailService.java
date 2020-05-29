package dev.ericksuarez.mail.sender.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
public class EmailService implements EmailSender {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        sendMessageToMultipleTo(subject, text, to);
    }

    @Override
    public void sendMessageToMultipleTo(String subject, String text, String... to) {
        try {
            var message = new SimpleMailMessage();
            message.setFrom("no-replay@ericksuarez.dev");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setReplyTo("ericksuarezdev@gmail.com");

            // javaMailSender.send(message);
            log.info("event=sendSimulation to={}", message.getTo());
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void sendSimpleMessageUsingTemplate(String to, String subject, String... templateModel) {

    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {

    }

    @Override
    public void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String, Object> templateModel) throws IOException, MessagingException {

    }
}
