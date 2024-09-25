package med.voll.api.controller;

import med.voll.api.doctor.DoctorRegistrationData;
import med.voll.api.patient.DataListingPatient;
import med.voll.api.patient.Patient;
import med.voll.api.patient.PatientRegistrationData;
import med.voll.api.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
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
