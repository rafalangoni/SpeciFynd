package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByDoctorIdAndDate(Long idDoctor, LocalDateTime dateTime);

    Boolean existsByPatientIdAndDateBetween(@NotNull Long idPatient, LocalDateTime openingTime, LocalDateTime closeTime);
}
