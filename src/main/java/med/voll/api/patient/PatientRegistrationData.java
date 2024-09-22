package med.voll.api.patient;

import med.voll.api.address.AddressData;

public record PatientRegistrationData(String name,
                                      String email,
                                      String phone,
                                      String cpf,
                                      AddressData address) {
}
