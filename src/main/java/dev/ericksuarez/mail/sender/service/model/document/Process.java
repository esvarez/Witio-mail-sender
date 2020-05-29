package dev.ericksuarez.mail.sender.service.model.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Document
public class Process {
    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String message;
/*
    @JsonIgnore
    @ToString.Exclude
    private Module module;
*/
    @ToString.Exclude
    private Set<String> mailingLists = new HashSet<>();
}
