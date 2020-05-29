package dev.ericksuarez.mail.sender.service.service;

public interface EmailSender {
    void sendMessage(String[] to, String subject, String text, boolean reply, String replyTo);

    void sendMessageBcc(String[] to, String[] bcc,String subject, String text, boolean reply, String replyTo);
}
