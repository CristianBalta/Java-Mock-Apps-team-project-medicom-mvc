package ro.iteahome.medicom.exception.business;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("USER ALREADY EXISTS.");
    }
}
