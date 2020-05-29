package dev.ericksuarez.mail.sender.service.service.sql;

import dev.ericksuarez.mail.sender.service.error.NotFoundException;
import dev.ericksuarez.mail.sender.service.model.entity.Recipient;
import dev.ericksuarez.mail.sender.service.model.entity.MailingList;
import dev.ericksuarez.mail.sender.service.repository.sql.MailingListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Profile("relational")
public class MailingListService {

    private MailingListRepository mailingListRepository;

    @Autowired
    public MailingListService(MailingListRepository mailingListRepository) {
        this.mailingListRepository = mailingListRepository;
    }

    public List<MailingList> getAllMailingList() {
        log.info("event=getAllMailingListInvoked");
        return mailingListRepository.findAll();
    }

    public MailingList getMailingListById(Integer id) {
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

    public MailingList updateMailingList(Integer id, MailingList mailingList) {
        log.info("event=updateMailingListInvoked mailingList={}", mailingList);
        mailingListRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("event=mailListingToUpdateNotFound id={}", id);
                    throw new NotFoundException("Mailing list with id: " + id);
                });
        mailingList.setId(id);
        return  mailingListRepository.save(mailingList);
    }

    public void addAddressee(Integer id, Recipient recipient) {
        log.info("event=addAddresseeInvoked id={}, recipient={}", id, recipient);
        mailingListRepository.findById(id)
                .ifPresentOrElse(mailingList -> {
                    mailingList.getRecipients().add(recipient);
                    var mailingListUpdated = mailingListRepository.save(mailingList);
                    log.info("event=recipientAdded mailingListUpdated={}", mailingListUpdated);
                },() -> {
                    log.error("event=mailListingToAddRecipientNotFound id={}", id);
                    throw new NotFoundException("Mailing list with id: " + id);
                });
    }

    public void removeAddressee(Integer id, Recipient recipient) {
        log.info("event=removeAddresseeInvoked id={}, recipient={}", id, recipient);
        mailingListRepository.findById(id)
                .ifPresentOrElse(mailingList -> {
                    mailingList.getRecipients().remove(recipient);
                    var mailingListUpdated = mailingListRepository.save(mailingList);
                    log.info("event=recipientRemoved mailingListUpdated={}", mailingListUpdated);
                },() -> {
                    log.error("event=mailListingToRemoveAddresseeNotFound id={}", id);
                    throw new NotFoundException("Mailing list with id: " + id);
                });
    }

    public ResponseEntity<?> deleteMailingList(Integer id) {
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
