package ru.eltech.sapr.web.contactsapp.exception;

public class ContactServiceException extends RuntimeException {
    public ContactServiceException(Throwable cause) {
        super(cause);
    }

    public ContactServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactServiceException(String message) {
        super(message);
    }
}
