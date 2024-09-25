package med.voll.api.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.address.AddressData;

public record PatientRegistrationData(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        @Pattern(regexp = "/\n^((\\d{11})|((\\d{3}[-.\\/]){3}\\d{2}))$\n")
        String cpf,

        @NotNull
        @Valid
        AddressData address) {
}
