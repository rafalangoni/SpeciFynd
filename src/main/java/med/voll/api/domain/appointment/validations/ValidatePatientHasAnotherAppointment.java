package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.AppointmentValidationException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientHasAnotherAppointment implements ValidateAppointment {

    @Autowired
    private AppointmentRepository repository;

    public void validate(DataScheduleAppointment data){
        var openingTime = data.date().withHour(7);
        var closeTime = data.date().withHour(18);
        var patientHasAnotherAppointmentSameDay = repository.existsByPatientIdAndDateBetween(data.idPatient(), openingTime, closeTime);
        if(patientHasAnotherAppointmentSameDay){
            throw new AppointmentValidationException("Patient already has another appointment at same day");
        }
    }
}
