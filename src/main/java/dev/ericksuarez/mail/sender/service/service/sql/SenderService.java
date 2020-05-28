package dev.ericksuarez.mail.sender.service.service.sql;

import dev.ericksuarez.mail.sender.service.model.SenderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.regex.Pattern;

@Service
@Profile("relational")
public class SenderService {

    // TODO process repository

    @Autowired
    public SenderService() {

    }

    public void sendMails(SenderDto senderDto) {
        // TODO Get Process
        var process = senderDto.getProcess();

        StringBuilder msj = new StringBuilder("%not_place% Simulacion %name% mensaje %otro%");
        var placeHolder = senderDto.getPlaceHolder();

        //Se obtiene el mensaje
        placeHolder.entrySet().stream()
                .forEach((map) -> {
                    int index = msj.indexOf("%" + map.getKey().toLowerCase() + "%");
                    while (index != -1) {
                        msj.replace(index, index + map.getKey().length() + 2, map.getValue());
                        index += map.getValue().length();
                        index = msj.indexOf("%" + map.getKey() + "%", index);
                    }
                });

        // TODO fid MailListing
        var emailListing = process.getMailingLists();

        // Get Mails
        var token = new StringTokenizer(senderDto.getEmails(), ";");
        while (token.hasMoreTokens()) {
            token.nextToken();
        }

        if (senderDto.getReplyTo() != null && senderDto.isReply()){
            // TODO set reply
        }

        //TODO Send Mails
        System.out.println(msj.toString());
    }
}
