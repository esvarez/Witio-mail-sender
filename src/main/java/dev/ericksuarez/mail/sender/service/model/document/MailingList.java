package dev.ericksuarez.mail.sender.service.model.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Document
public class MailingList {
    @Id
    private String id;

    @NotNull
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Recipient> recipients = new HashSet<>();

/*
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Process> processes = new HashSet<>();
 */
}
