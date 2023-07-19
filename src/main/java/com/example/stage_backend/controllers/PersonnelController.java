package com.example.stage_backend.controllers;




import com.example.stage_backend.entities.Personnel;
import com.example.stage_backend.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import java.util.List;
import java.util.Optional;




@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/personnels", produces = "application/json")
public class PersonnelController {
    private final PersonnelService personnelService;

    @Autowired
    public PersonnelController(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @GetMapping
    public ResponseEntity<List<Personnel>> getAllPersonnel() {
        List<Personnel> personnelList = personnelService.getAll();
        return ResponseEntity.ok(personnelList);
    }

    @GetMapping("/activer")
    public List<Personnel> getAllPersonnelEtatActif() {
        return personnelService.getAllPersonnelEtatActiver();
    }

    @GetMapping("/archiver")
    public List<Personnel> getAllPersonnelEtatPassif() {
        return personnelService.getAllPersonnelEtatArchiver();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personnel> getPersonnelById(@PathVariable("id") Long id) {
        Optional<Personnel> personnel = personnelService.getPersonnelById(id);
        return personnel.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Personnel> savePersonnel(@RequestBody Personnel personnel) {
        personnelService.savePersonnel(personnel);
        return new ResponseEntity<Personnel>(personnel, HttpStatus.CREATED); }

    @PutMapping("/{id}/archiver")
    public void archiverPersonnel(@PathVariable("id") Long id) {
        personnelService.archiverPersonnel(id);
    }

    @PutMapping("/{id}/activer")
    public void activerPersonnel(@PathVariable("id") Long id) {
        personnelService.activerPersonnel(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personnel> updatePersonnel(@PathVariable("id") Long id, @RequestBody Personnel personnel) {
        personnelService.updatePersonnel(id, personnel);
        return new ResponseEntity<Personnel>(personnel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletePersonnel(@PathVariable("id") Long id) {
        personnelService.deletePersonnel(id);
    }
}


