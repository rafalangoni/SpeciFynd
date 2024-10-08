package med.voll.api.domain.appointment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.Specialty;
import med.voll.api.domain.patient.Patient;

import java.time.LocalDateTime;

@Table(name = "appointment")
@Entity(name = "Appointment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "appointment_date")
    private LocalDateTime date;

    @Column(name = "reason_cancellation")
    @Enumerated(EnumType.STRING)
    private ReasonCancellation reasonCancellation;

    public Appointment(Long id, Doctor doctor, Patient patient, LocalDateTime date) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.date =date;
    }


    public void cancel(ReasonCancellation reasonCancellation){
        this.reasonCancellation = reasonCancellation;
    }
}
