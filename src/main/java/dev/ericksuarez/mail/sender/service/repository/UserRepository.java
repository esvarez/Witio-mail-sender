package dev.ericksuarez.mail.sender.service.repository;

import dev.ericksuarez.mail.sender.service.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("norelational")
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByName(String name);
}
