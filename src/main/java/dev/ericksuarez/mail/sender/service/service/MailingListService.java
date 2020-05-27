package dev.ericksuarez.mail.sender.service.service;

import dev.ericksuarez.mail.sender.service.error.NotFoundException;
import dev.ericksuarez.mail.sender.service.model.Addressee;
import dev.ericksuarez.mail.sender.service.model.MailingList;
import dev.ericksuarez.mail.sender.service.repository.MailingListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MailingListService {

    private MailingListRepository mailingListRepository;

    @Autowired
    public MailingListService(MailingListRepository mailingListRepository) {
        this.mailingListRepository = mailingListRepository;
    }

    public List<MailingList> getAllMailingList() {
        log.info("event=getAllMailingListInvoked");
        var mailingLists = new ArrayList<MailingList>();
        mailingListRepository.findAll().forEach(mailingLists::add);
        return mailingLists;
    }

    public MailingList getMailingListById(String id) {
        log.info("event=getMailingListByIdInvoked id={}", id);
        return mailingListRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("event=mailListingNotFound id={}", id);
                    return new NotFoundException("Mailing list with id: " + id);
                });
    }

    public MailingList createMailingList(MailingList mailingList) {
        log.info("event=createMailingListInvoked mailingList={}", mailingList);
        return mailingListRepository.save(mailingList);
    }

    public MailingList updateMailingList(String id, MailingList mailingList) {
        log.info("event=updateMailingListInvoked mailingList={}", mailingList);
        mailingListRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("event=mailListingToUpdateNotFound id={}", id);
                    throw new NotFoundException("Mailing list with id: " + id);
                });
        mailingList.setId(id);
        return  mailingListRepository.save(mailingList);
    }

    public void addAddressee(String id, Addressee addressee) {
        log.info("event=addAddresseeInvoked id={}, addressee={}", id, addressee);
        mailingListRepository.findById(id)
                .ifPresentOrElse(mailingList -> {
                    mailingList.getAddressees().add(addressee);
                    var mailingListUpdated = mailingListRepository.save(mailingList);
                    log.info("event=addresseeAdded mailingListUpdated={}, addressee={}", mailingListUpdated);
                },() -> {
                    log.error("event=mailListingToAddAddresseeNotFound id={}", id);
                    throw new NotFoundException("Mailing list with id: " + id);
                });
    }

    public void removeAddressee(String id, Addressee addressee) {
        log.info("event=removeAddresseeInvoked id={}, addressee={}", id, addressee);
        mailingListRepository.findById(id)
                .ifPresentOrElse(mailingList -> {
                    mailingList.getAddressees().remove(addressee);
                    var mailingListUpdated = mailingListRepository.save(mailingList);
                    log.info("event=addresseeRemoved mailingListUpdated={}, addressee={}", mailingListUpdated);
                },() -> {
                    log.error("event=mailListingToRemoveAddresseeNotFound id={}", id);
                    throw new NotFoundException("Mailing list with id: " + id);
                });
    }

    public ResponseEntity<?> deleteMailingList(String id) {
        log.info("event=deleteMailingListInvoked");
        mailingListRepository.findById(id)
                .ifPresentOrElse(mailingList -> {
                    mailingListRepository.delete(mailingList);
                    log.info("event=mailingListDeleted");
                }, () -> {
                    log.error("event=mailListingTodeleteNotFound id={}", id);
                    throw new NotFoundException("Mailing list with id: " + id);
                });

        return ResponseEntity.ok().build();
    }
}
