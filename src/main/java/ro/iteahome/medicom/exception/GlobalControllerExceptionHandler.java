package ro.iteahome.nhs.adminui.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.iteahome.nhs.adminui.exception.business.UserAlreadyExistsException;
import ro.iteahome.nhs.adminui.exception.business.UserNotFoundException;
import ro.iteahome.nhs.adminui.exception.error.UserError;

public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

//  USER EXCEPTIONS: ---------------------------------------------------------------------------------------------------

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserError> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new UserError("USR-01", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<UserError> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(new UserError("USR-02", ex.getMessage()), HttpStatus.CONFLICT);
    }
}
