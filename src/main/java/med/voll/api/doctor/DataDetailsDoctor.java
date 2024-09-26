package med.voll.api.doctor;

import med.voll.api.address.Address;

public record DataDetailsDoctor(Long id, String name, String email, String crm, Specialty specialty, Address address,
                                String phone) {

    public DataDetailsDoctor(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress(), doctor.getPhone());
    }
}
