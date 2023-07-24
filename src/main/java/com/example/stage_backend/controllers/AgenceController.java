package com.example.stage_backend.controllers;

import com.example.stage_backend.entities.Agence;
import com.example.stage_backend.services.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/agences", produces = "application/json")
public class AgenceController {

    private final AgenceService agenceService;

    @Autowired
    public AgenceController(AgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @GetMapping
    public ResponseEntity<List<Agence>> getAllAgences() {
        List<Agence> agences = agenceService.getAll();
        return ResponseEntity.ok(agences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> getAgenceById(@PathVariable("id") Long id) {
        Optional<Agence> agence = agenceService.getAgenceById(id);
        return agence.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activer")
    public List<Agence> getAllAgencesEtatActiver() {
        return agenceService.getAllAgencesEtatActiver();
    }

    @GetMapping("/archiver")
    public List<Agence> getAllAgencesEtatArchiver() {
        return agenceService.getAllAgencesEtatArchiver();
    }

    @PostMapping
    public ResponseEntity<Agence> saveAgence(@RequestBody Agence agence) {
        agenceService.saveAgence(agence);
        return new ResponseEntity<>(agence, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agence> updateAgence(@PathVariable("id") Long id, @RequestBody Agence agence) {
        agenceService.updateAgence(id, agence);
        return new ResponseEntity<>(agence, HttpStatus.OK);
    }

    @PutMapping("/{id}/archiver")
    public void archiverAgence(@PathVariable("id") Long id) {
        agenceService.archiverAgence(id);
    }

    @PutMapping("/{id}/activer")
    public void activerAgence(@PathVariable("id") Long id) {
        agenceService.activerAgence(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAgence(@PathVariable("id") Long id) {
        agenceService.deleteAgence(id);
    }
}
