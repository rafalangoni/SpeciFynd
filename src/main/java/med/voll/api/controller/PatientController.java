package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import med.voll.api.domain.patient.DataListingPatient;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRegistrationData;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    public void registration(@RequestBody PatientRegistrationData data) {
        repository.save(new Patient(data));
    }

    @GetMapping
    public List<DataListingPatient> list() {
        return repository.findAll()
                .stream()
                .map(patient -> new DataListingPatient(patient))
                .toList();
    }

}
