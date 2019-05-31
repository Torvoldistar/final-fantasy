package ru.eltech.sapr.web.storageapp.exception;


public class ServiceException extends RuntimeException {
    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }
}
