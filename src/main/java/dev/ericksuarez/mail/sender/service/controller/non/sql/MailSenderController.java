package dev.ericksuarez.mail.sender.service.controller.non.sql;

import dev.ericksuarez.mail.sender.service.model.SenderDto;
import dev.ericksuarez.mail.sender.service.service.non.sql.SenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static dev.ericksuarez.mail.sender.service.config.UrlConfig.SEND_MAILS;

@Slf4j
@RestController
@Profile("norelational")
public class MailSenderController {

    private SenderService senderService;

    @Autowired
    public MailSenderController(SenderService senderService) {
        this.senderService = senderService;
    }

    @PostMapping(SEND_MAILS)
    public boolean sendMails(@RequestBody SenderDto senderDto) throws IOException {
        log.info("event=sendMailsInvoked senderDto={}", senderDto);
        return senderService.sendMails(senderDto);
    }

    @GetMapping("/init")
    public boolean init() {
        senderService.init();
        return true;
    }
}
