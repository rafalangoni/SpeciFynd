package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.AppointmentValidationException;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidationTimeClinic implements ValidateAppointment {

    public void validate(DataScheduleAppointment data) {
        var dataAppointment = data.date();

        var sunday = dataAppointment.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpenClinic = dataAppointment.getHour() < 7;
        var afterCloseClinic = dataAppointment.getHour() > 18;
        if (sunday || beforeOpenClinic || afterCloseClinic) {
            throw new AppointmentValidationException("Appointment outside clinic opening hours.");
        }
    }
}
