package dev.ericksuarez.mail.sender.service.service.sql;

import dev.ericksuarez.mail.sender.service.error.NotFoundException;
import dev.ericksuarez.mail.sender.service.model.entity.Module;
import dev.ericksuarez.mail.sender.service.model.entity.Process;
import dev.ericksuarez.mail.sender.service.repository.sql.ProcessRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("relational")
public class ProcessService {

    private ProcessRepository processRepository;

    @Autowired
    public ProcessService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public Process getProcessById(Long id) {
        log.info("event=getProcessByIdInvoked id={}", id);
        return processRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("event=processNotFund id={}", id);
                    throw new NotFoundException("Process with id " + id);
                });
    }

    public Process save(Process process) {
        process.setModule(Module.builder().id(1).build());
        return processRepository.save(process);
    }

    // TODO create Process

    // TODO update Process

    // TODO delete Process
}
