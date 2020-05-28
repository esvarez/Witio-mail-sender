package dev.ericksuarez.mail.sender.service.model;

import dev.ericksuarez.mail.sender.service.model.entity.Module;
import dev.ericksuarez.mail.sender.service.model.entity.Process;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SenderDto {

    private Module module;

    private Process process;

    private Map<String, String> placeHolder;

    private String emails;

    private boolean reply;

    private String replyTo;
}
