package med.voll.api.doctor;

import med.voll.api.address.AdressData;

public record DoctorRegistrationData(String name,
                                     String email,
                                     String crm,
                                     Specialty specialty,
                                     AdressData adress) {
}
