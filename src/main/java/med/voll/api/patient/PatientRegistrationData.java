package med.voll.api.patient;

import med.voll.api.address.AdressData;

public record PatientRegistrationData(String name,
                                      String email,
                                      String phone,
                                      String cpf,
                                      AdressData address) {
}
