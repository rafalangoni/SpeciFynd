package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void registration(@RequestBody @Valid DoctorRegistrationData data) {
        repository.save(new Doctor(data));
    }

    @GetMapping
    public Page<DataListingDoctor> listActive(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(DataListingDoctor::new);

    }

//    @GetMapping
//    public Page<DataListingDoctor> listAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
//        return repository.findAll(pagination).map(DataListingDoctor::new);
//
//    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DataUpdateDoctor data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.updateDoctorData(data);
    }

//    @DeleteMapping("/doctors/physicalDelete")
//    @Transactional
//    public void physicalDelete(@RequestBody Long id){
//        repository.deleteById(id);
//    }

    @DeleteMapping("/{id}")
    @Transactional
    public void logicalExclusion(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        doctor.exclude();
    }


//    @GetMapping
//    public List<DataListingDoctor> list(Pageable pagination){
//        return repository.findAll(pagination)
//                .stream()
//                .map(doctor -> new DataListingDoctor(doctor))
//                .toList();
//        //This map above can be written two different ways like this:
////                .map(DataListingDoctor::new)
//    }
    // If I dont want to use Page and Pageable, I can return a list instead os pagination
//                .stream()
//                .map(doctor -> new DataListingDoctor(doctor))
//                .toList();
//                This map above can be written two different ways like this:
//                .map(DataListingDoctor::new)

}
