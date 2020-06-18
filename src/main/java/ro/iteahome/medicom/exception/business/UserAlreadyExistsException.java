package ro.iteahome.nhs.adminui.exception.business;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException() {
        super("USER ALREADY EXISTS.");
    }
}
