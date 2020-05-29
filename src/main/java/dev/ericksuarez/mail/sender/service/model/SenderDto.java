package dev.ericksuarez.mail.sender.service.model;

import dev.ericksuarez.mail.sender.service.model.entity.Module;
import dev.ericksuarez.mail.sender.service.model.entity.Process;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SenderDto {

    private String moduleId;

    @NotEmpty(message = "Please provide a process")
    private String processId;

    @NotEmpty(message = "Please provide a the text to replace")
    private Map<String, String> placeHolder;

    private String emails;

    private boolean reply;

    private String replyTo;
}
