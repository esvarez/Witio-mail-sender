package dev.ericksuarez.mail.sender.service.controller;

import dev.ericksuarez.mail.sender.service.model.Student;
import dev.ericksuarez.mail.sender.service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    private StudentRepository studentRepository;

    @Autowired
    public WebController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/")
    public Student save() {
        var student = Student.builder()
                .gender(Student.Gender.FEMALE)
                .id("ENG2015")
                .name("John")
                .grade(1)
                .build();
        return studentRepository.save(student);
    }

    @GetMapping("/")
    public Student get() {
        return studentRepository.findById("ENG2015")
                .orElseThrow(() -> new RuntimeException("Not student found"));
    }
}
