package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.AppointmentValidationException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorHasAnotherAppointment implements ValidateAppointment {

    @Autowired
    private AppointmentRepository repository;

    public void validate(DataScheduleAppointment data){
        var doctorHasAnotherAppointmentSameTime = repository.existsByDoctorIdAndDate(data.idDoctor(), data.date());
        if(doctorHasAnotherAppointmentSameTime){
            throw new AppointmentValidationException("Doctor has another appointment at same time");
        }
    }
}
