package ro.iteahome.medicom.exception.business;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("USER NOT FOUND.");
    }
}
