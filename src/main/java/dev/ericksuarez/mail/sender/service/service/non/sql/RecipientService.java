package dev.ericksuarez.mail.sender.service.service.non.sql;

import dev.ericksuarez.mail.sender.service.model.document.Recipient;
import dev.ericksuarez.mail.sender.service.repository.non.sql.MailingListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Profile("norelational")
public class RecipientService {

    private MailingListRepository mailingListRepository;

    @Autowired
    public RecipientService(MailingListRepository mailingListRepository) {
        this.mailingListRepository = mailingListRepository;
    }

    @Cacheable(value = "recipientList", key = "#p0")
    public Set<Recipient> findRecipientsByMailingLists(Set<String> mailingListsId) {
        log.info("event=findRecipientsByMailingListsInvoked");
        return mailingListsId.stream()
                .map( id -> mailingListRepository.findById(id))
                .filter( optional -> optional.isPresent())
                .map(mailingList -> mailingList.get())
                .flatMap(mailingList -> mailingList.getRecipients().stream())
                .collect(Collectors.toSet());
    }

    public List<Recipient> findAll() {
        return mailingListRepository.findAll().stream()
                .flatMap(module -> module.getRecipients().stream())
                .collect(Collectors.toList());
    }

    public Optional<Recipient> findById(String recipientId) {
        return mailingListRepository.findAll().stream()
                .flatMap(module -> module.getRecipients().stream())
                .filter(recipient -> recipient.getId().equals(recipientId))
                .findFirst();
    }

    public Recipient saveRecipient(String mailingListId, Recipient recipient) {
        mailingListRepository.findById(mailingListId).get()
                .getRecipients()
                .add(recipient);
        return recipient;
    }
}
