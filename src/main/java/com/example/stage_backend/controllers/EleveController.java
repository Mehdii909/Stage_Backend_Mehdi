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

    @GetMapping("/actifs")
    public List<Eleve> getAllEleveEtatActif() {
        return eleveService.getAllEleveEtatActif();
    }

    @GetMapping("/passifs")
    public List<Eleve> getAllEleveEtatPassif() {
        return eleveService.getAllEleveEtatPassif();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Eleve> getEleveById(@PathVariable("id") Long id) {
        Optional<Eleve> eleve = eleveService.getEleveById(id);
        return eleve.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> saveEleve(@RequestBody Eleve eleve) {
        eleveService.saveEleve(eleve);
        return ResponseEntity.status(HttpStatus.CREATED).body("Élève enregistré avec succès");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEleve(@PathVariable("id") Long id, @RequestBody Eleve eleve) {
        eleveService.updateEleve(id, eleve);
        return ResponseEntity.ok("Élève mis à jour avec succès");
    }

    @PutMapping("/{id}/archiver")
    public ResponseEntity<String> archiverEleve(@PathVariable("id") Long id) {
        eleveService.archiverEleve(id);
        return ResponseEntity.ok("Élève archivé avec succès");
    }

    @PutMapping("/{id}/activer")
    public ResponseEntity<String> activerEleve(@PathVariable("id") Long id) {
        eleveService.activerEleve(id);
        return ResponseEntity.ok("Élève activer avec succès");
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEleve(@PathVariable("id") Long id) {
        eleveService.deleteEleve(id);
        return ResponseEntity.ok("Élève supprimé avec succès");
    }
}
