package com.example.stage_backend.controllers;

import com.example.stage_backend.entities.AnneeScolaire;
import com.example.stage_backend.services.AnneeScolaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/annees-scolaires", produces = "application/json")
public class AnneeScolaireController {

    private final AnneeScolaireService anneeScolaireService;

    @Autowired
    public AnneeScolaireController(AnneeScolaireService anneeScolaireService) {
        this.anneeScolaireService = anneeScolaireService;
    }

    @GetMapping
    public ResponseEntity<List<AnneeScolaire>> getAllAnneesScolaires() {
        List<AnneeScolaire> anneesScolaires = anneeScolaireService.getAll();
        return ResponseEntity.ok(anneesScolaires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnneeScolaire> getAnneeScolaireById(@PathVariable("id") Long id) {
        Optional<AnneeScolaire> anneeScolaire = anneeScolaireService.getAnneeScolaireById(id);
        return anneeScolaire.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AnneeScolaire> saveAnneeScolaire(@RequestBody AnneeScolaire anneeScolaire) {
        anneeScolaireService.saveAnneeScolaire(anneeScolaire);
        return new ResponseEntity<>(anneeScolaire, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnneeScolaire> updateAnneeScolaire(@PathVariable("id") Long id, @RequestBody AnneeScolaire anneeScolaire) {
        anneeScolaireService.updateAnneeScolaire(id, anneeScolaire);
        return new ResponseEntity<>(anneeScolaire, HttpStatus.OK);
    }

    @PutMapping("/{id}/archiver")
    public void archiverAnneeScolaire(@PathVariable("id") Long id) {
        anneeScolaireService.archiverAnneeScolaire(id);
    }

    @PutMapping("/{id}/activer")
    public void activerAnneeScolaire(@PathVariable("id") Long id) {
        anneeScolaireService.activerAnneeScolaire(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAnneeScolaire(@PathVariable("id") Long id) {
        anneeScolaireService.deleteAnneeScolaire(id);
    }

    @GetMapping("/activer")
    public List<AnneeScolaire> getAllAnneeScolairesEtatActiver() {
        return anneeScolaireService.getAllAnneeScolairesEtatActiver();
    }

    @GetMapping("/archiver")
    public List<AnneeScolaire> getAllAnneeScolairesEtatArchiver() {
        return anneeScolaireService.getAllAnneeScolairesEtatArchiver();
    }
}
