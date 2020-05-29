package dev.ericksuarez.mail.sender.service.service.non.sql;

import dev.ericksuarez.mail.sender.service.model.document.Process;
import dev.ericksuarez.mail.sender.service.repository.non.sql.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile("norelational")
public class ProcessService {

    private ModuleRepository moduleRepository;

    @Autowired
    public ProcessService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public Optional<Process> findProccessByModuleIdAndProccessId(String moduleId, String processId) {
        var module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module notFound"));

        return module.getProcesses().stream()
                .filter(process -> process.getId().equals(processId))
                .findFirst();
    }
}
