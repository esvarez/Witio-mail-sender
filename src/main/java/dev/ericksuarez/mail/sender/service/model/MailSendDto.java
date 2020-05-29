package dev.ericksuarez.mail.sender.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MailSendDto {
    private String emails;

    private String[] recipients;

    private String subject;

    private String message;

    private boolean reply;

    private String replyTo;
}
