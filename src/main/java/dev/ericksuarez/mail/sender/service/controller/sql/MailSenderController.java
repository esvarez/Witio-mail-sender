package dev.ericksuarez.mail.sender.service.controller.sql;

import dev.ericksuarez.mail.sender.service.model.SenderDto;
import dev.ericksuarez.mail.sender.service.model.entity.Process;
import dev.ericksuarez.mail.sender.service.service.sql.SenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static dev.ericksuarez.mail.sender.service.config.UrlConfig.SEND_MAILS;

@Slf4j
@RestController
@Profile("relational")
public class MailSenderController {

    private SenderService senderService;

    @Autowired
    public MailSenderController(SenderService senderService) {
        this.senderService = senderService;
    }

    @PostMapping(SEND_MAILS)
    public String sendMails(@RequestBody SenderDto senderDto) {
        log.info("event=sendMailsInvoked senderDto={}", senderDto);
        return senderService.sendMails(senderDto);
    }

    @PostMapping("/save")
    public Process sendMails(@RequestBody Process process) {

        //return senderService.saveProcess(process);
        return null;
    }
}
