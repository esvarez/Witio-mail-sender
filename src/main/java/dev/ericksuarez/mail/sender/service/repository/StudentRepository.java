package dev.ericksuarez.mail.sender.service.repository;

import dev.ericksuarez.mail.sender.service.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
}
