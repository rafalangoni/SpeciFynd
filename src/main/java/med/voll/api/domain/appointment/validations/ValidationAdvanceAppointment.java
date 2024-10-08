package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.AppointmentValidationException;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidationAdvanceAppointment implements ValidateAppointment {

    public void validate (DataScheduleAppointment data){
//        var appointmentDate = data.date();
//        var now = LocalDateTime.now();
//        var differenceInMinutes = Duration.between(now, appointmentDate.toMinutes());
//
//        if(differenceInMinutes < 30){
//            throw new AppointmentValidationException("Appointment needs to be schedule 30 minutes in advance.");
//        }
    }


}
