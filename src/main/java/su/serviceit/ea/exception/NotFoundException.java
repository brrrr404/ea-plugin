package su.serviceit.ea.exception;

import su.serviceit.ea.exception.component.DialogComponentException;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
        new DialogComponentException(message).show();
    }
}
