package dev.ericksuarez.mail.sender.service.repository.sql;

import dev.ericksuarez.mail.sender.service.model.entity.Recipient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Profile("relational")
public interface RecipientRepository extends JpaRepository<Recipient, Long> {

    @Cacheable(value = "recipientList", key = "#p0")
    @Query("SELECT r FROM Recipient r JOIN FETCH r.mailingList m JOIN FETCH m.processes p WHERE p.id = :id")
    Set<Recipient> findByProcessId(@Param("id") Long id);
}
