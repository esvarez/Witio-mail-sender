package dev.ericksuarez.mail.sender.service.controller;

import dev.ericksuarez.mail.sender.service.model.entity.Addressee;
import dev.ericksuarez.mail.sender.service.model.entity.MailingList;
import dev.ericksuarez.mail.sender.service.service.MailingListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static dev.ericksuarez.mail.sender.service.config.UrlConfig.ADD_ADDRESSEE;
import static dev.ericksuarez.mail.sender.service.config.UrlConfig.MAILING_LISTS;
import static dev.ericksuarez.mail.sender.service.config.UrlConfig.REMOVE_ADDRESSEE;

@Slf4j
@RestController
@Profile("relational")
public class MailingListController {

    private MailingListService mailingListService;

    @Autowired
    public MailingListController (MailingListService mailingListService) {
        this.mailingListService = mailingListService;
    }

    @GetMapping(MAILING_LISTS)
    public List<MailingList> getMailingListings() {
        log.info("relational profile");
        return mailingListService.getAllMailingList();
    }

    @GetMapping(MAILING_LISTS + "/{id}")
    public MailingList getMailingListById(@PathVariable Integer id) {
        return mailingListService.getMailingListById(id);
    }

    @PostMapping(MAILING_LISTS)
    public MailingList saveMailingListById(@Valid @RequestBody MailingList mailingList) {
        return mailingListService.createMailingList(mailingList);
    }

    @PutMapping(MAILING_LISTS + "/{id}")
    public MailingList updateMailingListById(@PathVariable Integer id, @Valid @RequestBody MailingList mailingList) {
        return mailingListService.updateMailingList(id, mailingList);
    }

    @PatchMapping(ADD_ADDRESSEE + "/{id}")
    public void addAddressee(@PathVariable Integer id, @RequestBody Addressee addressee) {
        mailingListService.addAddressee(id, addressee);
    }

    @PatchMapping(REMOVE_ADDRESSEE + "/{id}")
    public void removeAddressee(@PathVariable Integer id, @RequestBody Addressee addressee) {
        mailingListService.removeAddressee(id, addressee);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteMailingListById(@PathVariable Integer id) {
        return mailingListService.deleteMailingList(id);
    }
}
