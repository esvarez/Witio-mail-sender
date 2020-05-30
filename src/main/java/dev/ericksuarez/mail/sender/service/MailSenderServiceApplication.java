package dev.ericksuarez.mail.sender.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MailSenderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailSenderServiceApplication.class, args);
	}
}
