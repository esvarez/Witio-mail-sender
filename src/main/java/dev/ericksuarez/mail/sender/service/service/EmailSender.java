package dev.ericksuarez.mail.sender.service.service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

public interface EmailSender {
    void sendSimpleMessage(String to, String subject, String text);

    void sendMessageToMultipleTo(String subject, String text, String... to);

    void sendSimpleMessageUsingTemplate(String to, String subject, String ...templateModel);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);

    void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String, Object> templateModel)
            throws IOException, MessagingException;
}
