package dev.ericksuarez.mail.sender.service.error;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entity) {
        super(entity + " not found.");
    }
}
