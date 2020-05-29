package dev.ericksuarez.mail.sender.service.service.non.sql;

import dev.ericksuarez.mail.sender.service.model.MailSendDto;
import dev.ericksuarez.mail.sender.service.model.SenderDto;
import dev.ericksuarez.mail.sender.service.model.document.MailingList;
import dev.ericksuarez.mail.sender.service.model.document.Module;
import dev.ericksuarez.mail.sender.service.model.document.Process;
import dev.ericksuarez.mail.sender.service.model.document.Recipient;
import dev.ericksuarez.mail.sender.service.model.document.Seller;
import dev.ericksuarez.mail.sender.service.repository.non.sql.MailingListRepository;
import dev.ericksuarez.mail.sender.service.repository.non.sql.ModuleRepository;
import dev.ericksuarez.mail.sender.service.repository.non.sql.SellerRepository;
import dev.ericksuarez.mail.sender.service.service.EmailService;
import dev.ericksuarez.mail.sender.service.service.SenderServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

@Slf4j
@Service
@Profile("norelational")
public class SenderService extends SenderServiceUtils {

    private SellerRepository sellerRepository;

    private ModuleRepository moduleRepository;

    private ProcessService processService;

    private RecipientService recipientService;

    private MailingListRepository mailingListRepository;

    private EmailService emailService;

    @Autowired
    public SenderService(SellerRepository sellerRepository, ModuleRepository moduleRepository,
                         ProcessService processService, RecipientService recipientService,
                         MailingListRepository mailingListRepository, EmailService emailService) {
        this.sellerRepository = sellerRepository;
        this.moduleRepository = moduleRepository;
        this.processService = processService;
        this.recipientService = recipientService;
        this.mailingListRepository = mailingListRepository;
        this.emailService = emailService;
    }

    public String sendMails(SenderDto senderDto) {
        log.info("event=sendMailsInvoked senderDto={}", senderDto);
        var process = processService.findProccessByModuleIdAndProccessId(senderDto.getModuleId(),
                String.valueOf(senderDto.getProcessId())).get();

        String message = replacePlaceholders(process.getMessage(), senderDto.getPlaceHolder());

        var recipientsList = recipientService.findRecipientsByMailingLists(process.getMailingLists());

        String[] recipients = getMailsToRecipientsListDoc(recipientsList);


        emailService.sendMessage(MailSendDto.builder()
                .emails(senderDto.getEmails())
                .message(process.getMessage())
                .recipients(recipients)
                .reply(senderDto.isReply())
                .replyTo(senderDto.getReplyTo())
                .subject(process.getName())
                .build());

        return "null";
    }

    public void init () {

        var recipients = new HashSet<Recipient>();
        recipients.add(Recipient.builder()
                .id(UUID.randomUUID().toString())
                .name("Erick")
                .job("Job 1")
                .email("4.20rck@gmail.com").build());

        var recipients2 = new HashSet<Recipient>();
        recipients2.add(Recipient.builder().id(UUID.randomUUID().toString()).name("Erick").job("Job 2").email("420rck@gmail.com").build());
        recipients2.add(Recipient.builder().id(UUID.randomUUID().toString()).name("Erick").job("Job 3").email("serick03@gmail.com").build());


        var mailingList = MailingList.builder().name("Confimracián al cliente").recipients(recipients).build();
        var mailingList2 = MailingList.builder().name("Envío a agente de servicio").recipients(recipients2).build();

        var mSaved = mailingListRepository.save(mailingList);
        var m2Saved = mailingListRepository.save(mailingList2);

        var process = Process.builder().name("Contacto")
                .id(UUID.randomUUID().toString())
                .message("Hola Que tal %nombre_cliente%: Hemos recibido su cotización de %auto%, con %nombre_banco%, con plazo de %plazo%, con un enganche de %enganche%")
                .mailingLists(Collections.singleton(mSaved.getId()))
                .build();

        var sets = new HashSet<String>();
        sets.add(mSaved.getId());
        sets.add(m2Saved.getId());

        var process2 = Process.builder().id(UUID.randomUUID().toString())
                .name("Cita Servicio")
                .message("Hola Que tal %nombre_cliente%: Hemos recibido su cotización de %auto%, con %nombre_banco%, con plazo de %plazo%, con un enganche de %enganche%")
                .mailingLists(sets)
                .build();

        var mod1 = Module.builder().name("Post-venta").processes(Collections.singleton(process)).build();
        var mod2 = Module.builder().name("Venta").processes(Collections.singleton(process2)).build();

        var modSaved = moduleRepository.save(mod1);
        var modSaved2 = moduleRepository.save(mod2);

        var sellers = new ArrayList<Seller>();
        sellers.add(Seller.builder().name("Hugo").modules(Collections.singleton(modSaved.getId())).build());
        sellers.add(Seller.builder().name("Alma").modules(Collections.singleton(modSaved2.getId())).build());
        sellers.add(Seller.builder().name("Paco")
                .modules(new HashSet<>(Arrays.asList(modSaved.getId(), modSaved2.getId()))).build());

        sellerRepository.saveAll(sellers);
    }
}
