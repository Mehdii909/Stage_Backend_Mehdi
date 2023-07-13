package com.example.stage_backend.controllers;

import com.example.stage_backend.entities.Eleve;
import com.example.stage_backend.services.EleveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/eleves", produces = "application/json")
public class EleveController {

    private final EleveService eleveService;

    @Autowired
    public EleveController(EleveService eleveService) {
        this.eleveService = eleveService;
    }

    @GetMapping
    public ResponseEntity<List<Eleve>> getAllEleves() {
        List<Eleve> eleves = eleveService.getAll();
        return ResponseEntity.ok(eleves);
    }

    @GetMapping("/activer")
    public List<Eleve> getAllEleveEtatActif() {
        return eleveService.getAllEleveEtatActiver();
    }

    @GetMapping("/archiver")
    public List<Eleve> getAllEleveEtatPassif() {
        return eleveService.getAllEleveEtatArchiver();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Eleve> getEleveById(@PathVariable("id") Long id) {
        Optional<Eleve> eleve = eleveService.getEleveById(id);
        return eleve.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Eleve> saveEleve(@RequestBody Eleve eleve) {
        eleveService.saveEleve(eleve);
        return new ResponseEntity<Eleve>(eleve, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Eleve> updateEleve(@PathVariable("id") Long id, @RequestBody Eleve eleve) {
        eleveService.updateEleve(id, eleve);
        return new ResponseEntity<Eleve>(eleve, HttpStatus.OK);
    }

    @PutMapping("/{id}/archiver")
    public void archiverEleve(@PathVariable("id") Long id) {
        eleveService.archiverEleve(id);
    }

    @PutMapping("/{id}/activer")
    public void activerEleve(@PathVariable("id") Long id) {
        eleveService.activerEleve(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEleve(@PathVariable("id") Long id) {
        eleveService.deleteEleve(id);
    }
}
