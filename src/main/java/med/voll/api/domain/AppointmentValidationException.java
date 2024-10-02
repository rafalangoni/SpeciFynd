package med.voll.api.domain;

public class AppointmentValidationException extends RuntimeException {
    public AppointmentValidationException(String message) {
        super(message);
    }
}
