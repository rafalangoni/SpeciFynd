package med.voll.api.domain.appointment;

import med.voll.api.domain.AppointmentValidationException;
import med.voll.api.domain.appointment.validations.ValidateAppointment;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentScheduleService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ValidateAppointment> validateAppointments;

    public DataDetailAppointment schedule(DataScheduleAppointment data) {
        if (!patientRepository.existsById(data.idPatient())) {
            throw new AppointmentValidationException("Patient ID on request does not exist.");
        }

        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
            throw new AppointmentValidationException("Doctor ID on request does not exist.");
        }

        validateAppointments.forEach(v -> v.validate(data));

        var patient = patientRepository.getReferenceById(data.idPatient());
        var doctor = chooseDoctor(data);
        if(doctor == null){
            throw new AppointmentValidationException("Does not exist doctor available.");
        }
        var appointment = new Appointment(null, doctor, patient, data.date(), null);
        appointmentRepository.save(appointment);

        return new DataDetailAppointment(appointment);
    }

    public void cancelAppointment(DataCancelAppointment data){
        if(!appointmentRepository.existsById(data.idAppointment())){
            throw new AppointmentValidationException("Appointment ID does not exist.");
        }

        var appointment = appointmentRepository.getReferenceById(data.idAppointment());
        appointment.cancel(data.reason());
    }


    private Doctor chooseDoctor(DataScheduleAppointment data) {
        if(data.idDoctor() != null){
            return doctorRepository.getReferenceById(data.idDoctor());
        }
        if(data.specialty() == null){
            throw new AppointmentValidationException("Specialty is mandatory when doctor not chosen");
        }

        return doctorRepository.chooseRandomDoctorWithFreeSchedule(data.specialty(), data.date());
    }
}
