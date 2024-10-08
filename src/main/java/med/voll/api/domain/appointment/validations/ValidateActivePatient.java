package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.AppointmentValidationException;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateActivePatient implements ValidateAppointment {

    @Autowired
    private PatientRepository repository;

    public void validate(DataScheduleAppointment data){
        var patientActive = repository.findActiveById(data.idPatient());
        if(!patientActive){
            throw new AppointmentValidationException("Patient is not active.");
        }
    }
}
