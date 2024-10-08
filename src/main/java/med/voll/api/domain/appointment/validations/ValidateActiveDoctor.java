package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.AppointmentValidationException;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateActiveDoctor implements ValidateAppointment {

    @Autowired
    private DoctorRepository repository;

    public void validate(DataScheduleAppointment data){
        if(data.idDoctor() == null){
            return;
        }

        var activeDoctor = repository.findActiveById(data.idDoctor());
        if(!activeDoctor){
            throw new AppointmentValidationException("Doctor is not active. Please try another doctor.");
        }

    }
}
