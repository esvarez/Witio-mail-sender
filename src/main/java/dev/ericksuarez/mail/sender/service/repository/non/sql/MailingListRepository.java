package dev.ericksuarez.mail.sender.service.repository.non.sql;

import dev.ericksuarez.mail.sender.service.model.document.MailingList;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("norelational")
public interface MailingListRepository extends MongoRepository<MailingList, String> {
}
