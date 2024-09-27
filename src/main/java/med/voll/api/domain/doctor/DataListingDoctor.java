package med.voll.api.domain.doctor;

public record DataListingDoctor(Long id, String name, String email, String crm, Specialty specialty) {

    public DataListingDoctor(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
