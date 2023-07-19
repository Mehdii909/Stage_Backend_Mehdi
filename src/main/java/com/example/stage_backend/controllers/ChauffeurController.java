package com.example.stage_backend.controllers;

import com.example.stage_backend.entities.Chauffeur;
import com.example.stage_backend.services.ChauffeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/chauffeurs", produces = "application/json")
public class ChauffeurController {

    private final ChauffeurService chauffeurService;

    @Autowired
    public ChauffeurController(ChauffeurService chauffeurService) {
        this.chauffeurService = chauffeurService;
    }

    @GetMapping
    public ResponseEntity<List<Chauffeur>> getAllChauffeurs() {
        List<Chauffeur> chauffeurs = chauffeurService.getAll();
        return ResponseEntity.ok(chauffeurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chauffeur> getChauffeurById(@PathVariable("id") Long id) {
        Optional<Chauffeur> chauffeur = chauffeurService.getChauffeurById(id);
        return chauffeur.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/actifs")
    public List<Chauffeur> getAllChauffeursEtatActif() {
        return chauffeurService.getAllChauffeursEtatActiver();
    }

    @GetMapping("/archives")
    public List<Chauffeur> getAllChauffeursEtatArchive() {
        return chauffeurService.getAllChauffeursEtatArchiver();
    }

    @PostMapping
    public ResponseEntity<Chauffeur> saveChauffeur(@RequestBody Chauffeur chauffeur) {
        chauffeurService.saveChauffeur(chauffeur);
        return new ResponseEntity<>(chauffeur, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chauffeur> updateChauffeur(@PathVariable("id") Long id, @RequestBody Chauffeur chauffeur) {
        chauffeurService.updateChauffeur(id, chauffeur);
        return new ResponseEntity<>(chauffeur, HttpStatus.OK);
    }

    @PutMapping("/{id}/archiver")
    public void archiverChauffeur(@PathVariable("id") Long id) {
        chauffeurService.archiverChauffeur(id);
    }

    @PutMapping("/{id}/activer")
    public void activerChauffeur(@PathVariable("id") Long id) {
        chauffeurService.activerChauffeur(id);
    }

    @DeleteMapping("/{id}")
    public void deleteChauffeur(@PathVariable("id") Long id) {
        chauffeurService.deleteChauffeur(id);
    }
}
