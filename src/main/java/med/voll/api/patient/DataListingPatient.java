package med.voll.api.patient;

public record DataListingPatient(String name, String email, String cpf) {

    public DataListingPatient(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getCpf());
    }

}
