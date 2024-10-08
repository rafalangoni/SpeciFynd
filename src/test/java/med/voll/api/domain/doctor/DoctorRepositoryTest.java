package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRegistrationData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test") //Came from application-test.properties
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Doctor has another appointment and not available; must return null")
    void chooseRandomDoctorWithFreeSchedule1() {
        //given or arrange
        var appointmentDate = "2024-10-08T10:00";

        //when or act
        var doctor = createDoctor("Doctor", "doctor@vollmed.com", "12456", Specialty.CARDIOLOGY);
        var patient = createPatient("Patient", "patient@vollmed.com", "12345678977");

        createAppointment(doctor, patient, LocalDateTime.parse(appointmentDate));
        //then or assert
        var freeDoctor = doctorRepository.chooseRandomDoctorWithFreeSchedule(Specialty.CARDIOLOGY, LocalDateTime.parse(appointmentDate));

        //then or assert
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Return doctor when available")
    void chooseRandomDoctorWithFreeSchedule2() {
        var nextMonday = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY));
       var doctor = createDoctor("Doctor", "doctor@vollmed.com", "12456", Specialty.CARDIOLOGY);

        var freeDoctor = doctorRepository.chooseRandomDoctorWithFreeSchedule(Specialty.CARDIOLOGY, nextMonday);
        assertThat(freeDoctor).isEqualTo(doctor);
    }



    private void createAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date));
    }

    private Doctor createDoctor(String nome, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(doctorData(nome, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient createPatient(String nome, String email, String cpf) {
        var patient = new Patient(patientData(nome, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DoctorRegistrationData doctorData(String nome, String email, String crm, Specialty specialty) {
        return new DoctorRegistrationData(
                nome,
                email,
                "61999999999",
                crm,
                specialty,
                AddressData()
        );
    }

    private PatientRegistrationData patientData(String nome, String email, String cpf) {
        return new PatientRegistrationData(
                nome,
                email,
                "61999999999",
                cpf,
                AddressData()
        );
    }

    private AddressData AddressData() {
        return new AddressData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}