package dev.ericksuarez.mail.sender.service.repository;

import dev.ericksuarez.mail.sender.service.model.entity.MailingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailingListRepository extends JpaRepository<MailingList, Integer> {
}
