package dev.ericksuarez.mail.sender.service.service;

// import com.sendgrid.*;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import dev.ericksuarez.mail.sender.service.model.MailSendDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Service
public class SendGridService {

    @Value("${application.senGrid.apiKey}")
    private String sendGridApiKey;

    public void sendMail(MailSendDto mailSendDto) throws IOException {
        Email from = new Email("serick03@gmail.com");

        StringBuilder mails = new StringBuilder();
        String semicolon = "";
        for (String mail: mailSendDto.getRecipients()) {
            mails.append(semicolon);
            mails.append(mail);
            semicolon = "; ";
        }

        System.out.println(mails.toString());
        Email to = new Email(mails.toString());

        Content content = new Content("text/plain", mailSendDto.getMessage());

        Mail mail = new Mail(from, mailSendDto.getSubject(), to, content);
        if (!StringUtils.isEmpty(mailSendDto.getEmails())) {
            mail.personalization.get(0).addBcc(new Email(mailSendDto.getEmails()));
        }

        if (mailSendDto.isReply() && !StringUtils.isEmpty(mailSendDto.getReplyTo())){
            mail.setReplyTo(new Email(mailSendDto.getReplyTo()));
        }

        System.out.println(sendGridApiKey);
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

    private void test() throws IOException {
        /*
        Email from = new Email("serick03@gmail.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("420rck@gmail.com");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        System.out.println("TEST");
        sendGridApiKey = "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";
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
        */
    }
}
