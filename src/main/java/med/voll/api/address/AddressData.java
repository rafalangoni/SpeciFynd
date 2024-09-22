package med.voll.api.address;

public record AddressData(String street,
                          String neighborhood,
                          String codepostal,
                          String city,
                          String province,
                          String complement,
                          String number) {
}
