package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    public Doctor(DoctorRegistrationData data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.crm = data.crm();
        this.address = new Address(data.address());
        this.specialty = data.specialty();
    }

    public void updateDoctorData(DataUpdateDoctor data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if(data.phone() != null){
            this.phone = data.phone();
        }
        if(data.addressData() != null){
            this.address.updateInformation(data.addressData());
        }
        if(data.active() == true){
            this.active = true;
        }
    }

    public void exclude() {
        this.active = false;
    }
}
