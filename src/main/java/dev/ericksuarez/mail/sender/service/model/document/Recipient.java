package dev.ericksuarez.mail.sender.service.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Document
public class Recipient {
    @Id
    private String id;

    private String name;

    private String email;

    private String job;
}
