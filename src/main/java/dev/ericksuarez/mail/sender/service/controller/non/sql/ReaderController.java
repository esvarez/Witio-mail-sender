package dev.ericksuarez.mail.sender.service.controller.non.sql;

import dev.ericksuarez.mail.sender.service.model.document.MailingList;
import dev.ericksuarez.mail.sender.service.model.document.Module;
import dev.ericksuarez.mail.sender.service.model.document.Process;
import dev.ericksuarez.mail.sender.service.model.document.Recipient;
import dev.ericksuarez.mail.sender.service.model.document.Seller;
import dev.ericksuarez.mail.sender.service.repository.non.sql.MailingListRepository;
import dev.ericksuarez.mail.sender.service.repository.non.sql.ModuleRepository;
import dev.ericksuarez.mail.sender.service.repository.non.sql.SellerRepository;
import dev.ericksuarez.mail.sender.service.service.non.sql.ProcessService;
import dev.ericksuarez.mail.sender.service.service.non.sql.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Profile("norelational")
public class ReaderController {

    private MailingListRepository mailingListRepository;

    private ModuleRepository moduleRepository;

    private SellerRepository sellerRepository;

    private ProcessService processService;

    private RecipientService recipientService;

    @Autowired
    public ReaderController(MailingListRepository mailingListRepository, ModuleRepository moduleRepository,
                            SellerRepository sellerRepository, ProcessService processService,
                            RecipientService recipientService) {
        this.mailingListRepository = mailingListRepository;
        this.moduleRepository = moduleRepository;
        this.sellerRepository = sellerRepository;
        this.processService = processService;
        this.recipientService = recipientService;
    }

    @GetMapping("/getMailingLists")
    public List<MailingList> getMailingList(){
        return mailingListRepository.findAll();
    }

    @GetMapping("/getMailingLists/{id}")
    public MailingList getMailingList(@PathVariable String id) {
        return mailingListRepository.findById(id).get();
    }

    @PostMapping("/saveMailingLists")
    public MailingList saveMailingList(@RequestBody MailingList mailingList) {
        return mailingListRepository.save(mailingList);
    }

    @GetMapping("/getModules")
    public List<Module> getModule(){
        return moduleRepository.findAll();
    }

    @GetMapping("/getModule/{id}")
    public Module getModule(@PathVariable String id) {
        return moduleRepository.findById(id).get();
    }

    @PostMapping("/saveModule")
    public Module saveModule(@RequestBody Module module) {
        return moduleRepository.save(module);
    }

    @GetMapping("/getSellers")
    public List<Seller> getSellers(){
        return sellerRepository.findAll();
    }

    @GetMapping("/getSeller/{id}")
    public Seller getSeller(@PathVariable String id) {
        return sellerRepository.findById(id).get();
    }

    @PostMapping("/saveSeller")
    public Seller saveSeller(@RequestBody Seller module) {
        return sellerRepository.save(module);
    }

    @GetMapping("/getProcesses")
    public List<Process> getProcesses(){
        return processService.findAll();
    }

    @GetMapping("/getProcess/{processId}")
    public Process getProcess(@PathVariable String processId) {
        return processService.findProccessById(processId).get();
    }

    @PostMapping("/saveProcess/{moduleId}")
    public Process saveProcess(@PathVariable String moduleId, @RequestBody Process process) {
        return processService.saveProcess(moduleId, process);
    }

    @GetMapping("/getRecipient")
    public List<Recipient> getRecipients(){
        return recipientService.findAll();
    }

    @GetMapping("/getRecipient/{recipientId}")
    public Recipient getRecipient(@PathVariable String recipientId) {
        return recipientService.findById(recipientId).get();
    }

    @PostMapping("/saveRecipient/{mailingListId}")
    public Recipient saveRecipient(@PathVariable String mailingListId, @RequestBody Recipient recipient) {
        return recipientService.saveRecipient(mailingListId, recipient);
    }
}
