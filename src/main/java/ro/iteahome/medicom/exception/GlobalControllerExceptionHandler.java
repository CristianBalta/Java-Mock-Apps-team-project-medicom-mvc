package ro.iteahome.medicom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.iteahome.medicom.exception.business.NotNhsRegisteredDoctorOrNurseException;
import ro.iteahome.medicom.exception.business.UserAlreadyExistsException;
import ro.iteahome.medicom.exception.business.UserNotFoundException;
import ro.iteahome.medicom.exception.error.UserError;

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

    @ExceptionHandler(NotNhsRegisteredDoctorOrNurseException.class)
    public ResponseEntity<UserError> handleNotNhsRegisteredDoctorException(NotNhsRegisteredDoctorOrNurseException ex) {
        return new ResponseEntity<>(new UserError("DOC-01", ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
