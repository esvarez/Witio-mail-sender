package dev.ericksuarez.mail.sender.service.service;

import dev.ericksuarez.mail.sender.service.model.entity.Recipient;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Slf4j
public class SenderServiceUtils {
    protected String replacePlaceholders(String template, Map<String, String> placeHolders){
        log.info("event=replacePlaceholdersInvoked, template={}, placeHolders={}", template, placeHolders);
        StringBuilder message = new StringBuilder(template);
        placeHolders.forEach((key, value) -> {
            int index = message.indexOf("%" + key.toLowerCase() + "%");
            while (index != -1) {
                message.replace(index, index + key.length() + 2, value);
                index += value.length();
                index = message.indexOf("%" + key + "%", index);
            }
        });
        return message.toString();
    }

    protected String[] getMailsToRecipientsListDoc(Set<dev.ericksuarez.mail.sender.service.model.document.Recipient> recipientsList) {
        if (recipientsList.isEmpty()){
            throw new RuntimeException("The MailsList is empty");
        }
        return recipientsList.stream()
                .map(getMailFromRecipientDoc)
                .toArray(String[]::new);
    }

    protected String[] getMailsToRecipientsList(Set<Recipient> recipientsList) {
        if (recipientsList.isEmpty()){
            throw new RuntimeException("The MailsList is empty");
        }
        return recipientsList.stream()
                .map(getMailFromRecipient)
                .toArray(String[]::new);
    }

    protected Function<Recipient, String> getMailFromRecipient = recipient -> recipient.getEmail();
    protected Function<dev.ericksuarez.mail.sender.service.model.document.Recipient, String> getMailFromRecipientDoc = recipient -> recipient.getEmail();
}
