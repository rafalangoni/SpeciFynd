package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataCancelAppointment(
        @NotNull
        Long idAppointment,

        @NotNull
        ReasonCancellation reason
) {
}
