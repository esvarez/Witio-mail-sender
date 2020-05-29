package dev.ericksuarez.mail.sender.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Collections;
import java.util.Set;

@SpringBootApplication
public class MailSenderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailSenderServiceApplication.class, args);

		justA(Collections.singleton(new A()));
		justA(Collections.singleton(new B()));
	}

	static void justA(Set<A> ok) {

	}

	static class A {

	}

	static class B extends A {

	}
}
