package dev.ericksuarez.mail.sender.service.repository;

import dev.ericksuarez.mail.sender.service.model.MailingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailingListRepository extends CrudRepository<MailingList, String> {
}
