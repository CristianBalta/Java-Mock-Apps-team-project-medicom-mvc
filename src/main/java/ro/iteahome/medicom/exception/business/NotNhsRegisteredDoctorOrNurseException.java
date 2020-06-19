package ro.iteahome.medicom.exception.business;

public class NotNhsRegisteredDoctorOrNurseException extends RuntimeException {

    public NotNhsRegisteredDoctorOrNurseException() {
        super("USER IS NOT AN NHS-REGISTERED DOCTOR OR NURSE.");
    }
}
