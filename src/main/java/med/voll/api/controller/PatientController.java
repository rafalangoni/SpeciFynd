package med.voll.api.controller;

import med.voll.api.patient.PatientRegistrationData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @PostMapping
    public void registration(@RequestBody PatientRegistrationData data){

    }

}
