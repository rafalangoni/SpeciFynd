package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String neighborhood;
    private String postalCode;
    private String number;
    private String complement;
    private String city;
    private String province;

    public Address(AdressData address) {
        this.street = address.city();
        this.neighborhood = address.neighborhood();
        this.postalCode = address.postalCode();
        this.number = address.number();
        this.complement = address.complement();
        this.city = address.city();
        this.province = address.province();

    }
}
