package dev.ericksuarez.mail.sender.service.repository.sql;

import dev.ericksuarez.mail.sender.service.model.entity.Module;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("relational")
public interface ModuleRepository extends JpaRepository<Module, Integer> {
}
