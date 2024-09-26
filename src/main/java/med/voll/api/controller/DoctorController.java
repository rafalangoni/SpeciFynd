package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registration(@RequestBody @Valid DoctorRegistrationData data, UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(data);
        repository.save(doctor);

        var uri = uriBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailsDoctor(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DataListingDoctor>> listActive(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var page = repository.findAllByActiveTrue(pagination).map(DataListingDoctor::new);
        return ResponseEntity.ok(page);

    }


    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateDoctor data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.updateDoctorData(data);
        return ResponseEntity.ok(new DataDetailsDoctor(doctor));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity logicalExclusion(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.exclude();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity details(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailsDoctor(doctor));
    }


}
