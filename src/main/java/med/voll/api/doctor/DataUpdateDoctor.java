package med.voll.api.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressData;

public record DataUpdateDoctor(
        @NotNull
        Long id,
        String name,
        String phone,
        boolean active,
        AddressData addressData
) {
}
