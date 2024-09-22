package med.voll.api.doctor;

import med.voll.api.address.AddressData;

public record DoctorRegistrationData(String name,
                                     String email,
                                     String crm,
                                     Specialty specialty,
                                     AddressData address) {
}
