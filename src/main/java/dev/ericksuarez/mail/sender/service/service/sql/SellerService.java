package dev.ericksuarez.mail.sender.service.service.sql;

import dev.ericksuarez.mail.sender.service.error.NotFoundException;
import dev.ericksuarez.mail.sender.service.model.entity.Module;
import dev.ericksuarez.mail.sender.service.model.entity.Seller;
import dev.ericksuarez.mail.sender.service.repository.sql.SellerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Profile("relational")
public class SellerService {

    private SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<Seller> getAllSellers() {
        log.info("event=getAllSellersInvoked");
        return sellerRepository.findAll();
    }

    public Seller getSellerById(Long id) {
        log.info("event=getSellerByIdInvoked id={}", id);
        return sellerRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("event=getSellerNotFound id={}", id);
                    return new NotFoundException("Seller with id: " + id);
                });
    }

    public Seller createSeller(Seller seller) {
        log.info("event=createSellerInvoked seller={}", seller);
        return sellerRepository.save(seller);
    }

    public Seller updateSeller(Long id, Seller seller) {
        log.info("event=updateSellerInvoked id={}, seller={}", id, seller);
        sellerRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("event=sellerToUpdateNotFound id={}", id);
                    throw new NotFoundException("Seller list with id: " + id);
                });
        seller.setId(id);
        return  sellerRepository.save(seller);
    }

    public void addModule(Long id, Module module) {
        log.info("event=addModuleInvoked id={}, module={}", id, module);
        sellerRepository.findById(id)
                .ifPresentOrElse(seller -> {
                    seller.getModules().add(module);
                    var sellerUpdated = sellerRepository.save(seller);
                    log.info("event=moduleAdded sellerUpdated={}, module={}", sellerUpdated, module);
                },() -> {
                    log.error("event=sellerToAddAddresseeNotFound id={}", id);
                    throw new NotFoundException("Seller with id: " + id);
                });
    }

    public void removeModule(Long id, Module module) {
        log.info("event=removeModuleInvoked id={}, module={}", id, module);
        sellerRepository.findById(id)
                .ifPresentOrElse(seller -> {
                    seller.getModules().remove(module);
                    var sellerUpdated = sellerRepository.save(seller);
                    log.info("event=addresseeRemoved sellerUpdated={}", sellerUpdated);
                },() -> {
                    log.error("event=sellerToRemoveAddresseeNotFound id={}", id);
                    throw new NotFoundException("Seller with id: " + id);
                });
    }
}
