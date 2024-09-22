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
    private String codepostal;
    private String number;
    private String complement;
    private String city;
    private String province;

    public Address(AddressData address) {
        this.street = address.city();
        this.neighborhood = address.neighborhood();
        this.codepostal = address.codepostal();
        this.number = address.number();
        this.complement = address.complement();
        this.city = address.city();
        this.province = address.province();

    }
}
