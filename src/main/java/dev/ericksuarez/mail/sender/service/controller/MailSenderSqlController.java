package dev.ericksuarez.mail.sender.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static dev.ericksuarez.mail.sender.service.config.UrlConfig.SEND_MAILS;

@Slf4j
@RestController
@Profile("relational")
public class MailSenderSqlController {

    @Autowired
    public MailSenderSqlController() {

    }

    @PostMapping(SEND_MAILS)
    public void sendMails(@RequestBody Object senderDto) {
        log.info("event=sendMailsInvoked senderDto={}", senderDto);
    }
}
