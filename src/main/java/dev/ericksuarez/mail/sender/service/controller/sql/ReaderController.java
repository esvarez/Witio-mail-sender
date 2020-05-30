package dev.ericksuarez.mail.sender.service.controller.sql;

import dev.ericksuarez.mail.sender.service.model.entity.MailingList;
import dev.ericksuarez.mail.sender.service.model.entity.Recipient;
import dev.ericksuarez.mail.sender.service.model.entity.Seller;
import dev.ericksuarez.mail.sender.service.model.entity.Process;
import dev.ericksuarez.mail.sender.service.model.entity.Module;
import dev.ericksuarez.mail.sender.service.repository.sql.ModuleRepository;
import dev.ericksuarez.mail.sender.service.repository.sql.ProcessRepository;
import dev.ericksuarez.mail.sender.service.repository.sql.RecipientRepository;
import dev.ericksuarez.mail.sender.service.repository.sql.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Profile("relational")
public class ReaderController {

    private ModuleRepository moduleRepository;

    private SellerRepository sellerRepository;

    private ProcessRepository processRepository;

    private RecipientRepository recipientRepository;

    @Autowired
    public ReaderController(ModuleRepository moduleRepository, SellerRepository sellerRepository,
                            ProcessRepository processRepository, RecipientRepository recipientRepository) {
        this.moduleRepository = moduleRepository;
        this.sellerRepository = sellerRepository;
        this.processRepository = processRepository;
        this.recipientRepository = recipientRepository;
    }

    @GetMapping("/getModules")
    public List<Module> getModule(){
        return moduleRepository.findAll();
    }

    @GetMapping("/getModule/{id}")
    public Module getModule(@PathVariable Integer id) {
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
    public Seller getSeller(@PathVariable Long id) {
        return sellerRepository.findById(id).get();
    }

    @PostMapping("/saveSeller")
    public Seller saveSeller(@RequestBody Seller module) {
        return sellerRepository.save(module);
    }

    @GetMapping("/getProcesses")
    public List<Process> getProcesses(){
        return processRepository.findAll();
    }

    @GetMapping("/getProcess/{processId}")
    public Process getProcess(@PathVariable Long processId) {
        return processRepository.findById(processId).get();
    }

    @PostMapping("/saveProcess/{moduleId}")
    public Process saveProcess(@PathVariable String moduleId, @RequestBody Process process) {
        return processRepository.save(process);
    }

    @GetMapping("/getRecipient")
    public List<Recipient> getRecipients(){
        return recipientRepository.findAll();
    }

    @GetMapping("/getRecipient/{recipientId}")
    public Recipient getRecipient(@PathVariable Long recipientId) {
        return recipientRepository.findById(recipientId).get();
    }

    @PostMapping("/saveRecipient/{mailingListId}")
    public Recipient saveRecipient(@PathVariable Integer mailingListId, @RequestBody Recipient recipient) {
        recipient.setMailingList(MailingList.builder().id(mailingListId).build());
        return recipientRepository.save(recipient);
    }
}
