package dev.ericksuarez.mail.sender.service.service.non.sql;

import dev.ericksuarez.mail.sender.service.model.document.Recipient;
import dev.ericksuarez.mail.sender.service.repository.non.sql.MailingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile("norelational")
public class RecipientService {

    private MailingListRepository mailingListRepository;

    @Autowired
    public RecipientService(MailingListRepository mailingListRepository) {
        this.mailingListRepository = mailingListRepository;
    }

    public Set<Recipient> findRecipientsByMailingLists(Set<String> mailingListsId) {
        return mailingListsId.stream()
                .map( id -> mailingListRepository.findById(id))
                .filter( optional -> optional.isPresent())
                .map(mailingList -> mailingList.get())
                .flatMap(mailingList -> mailingList.getRecipients().stream())
                .collect(Collectors.toSet());
    }
}
