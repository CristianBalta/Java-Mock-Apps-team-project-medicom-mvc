package ro.iteahome.nhs.adminui.exception.business;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("USER NOT FOUND.");
    }
}
