package med.voll.api.address;

public record AdressData(String street,
                         String neighborhood,
                         String postalCode,
                         String city,
                         String province,
                         String complement,
                         String number) {
}
