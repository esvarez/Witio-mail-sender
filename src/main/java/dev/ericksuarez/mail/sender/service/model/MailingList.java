package dev.ericksuarez.mail.sender.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@RedisHash("MailingList")
public class MailingList {
    @NotNull
    private String id;

    @NotNull
    private String name;

    private Set<Addressee> addressees = new HashSet<>();
}
