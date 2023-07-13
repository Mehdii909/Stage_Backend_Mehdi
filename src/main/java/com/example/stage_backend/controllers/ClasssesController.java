package com.example.stage_backend.controllers;

import com.example.stage_backend.entities.Classe;
import com.example.stage_backend.entities.Eleve;
import com.example.stage_backend.services.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/classes", produces = "application/json")
public class ClasssesController {

    private final ClasseService classeService;

    @Autowired
    public ClasssesController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @GetMapping
    public ResponseEntity<List<Classe>> getAllClasses() {
        List<Classe> classes = classeService.getAll();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classe> getClasseById(@PathVariable("id") Long id) {
        Optional<Classe> classe = classeService.getClasseById(id);
        return classe.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/activer")
    public List<Classe> getAllClasseEtatActiver() {
        return classeService.getAllClasseEtatActiver();
    }

    @GetMapping("/archiver")
    public List<Classe> getAllClasseEtatArchiver() {
        return classeService.getAllClasseEtatArchiver();
    }


    @PostMapping
    public ResponseEntity<Classe> saveClasse(@RequestBody Classe classe) {
        classeService.saveClasse(classe);
        return new ResponseEntity<>(classe, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classe> updateClasse(@PathVariable("id") Long id, @RequestBody Classe classe) {
        classeService.updateClasse(id, classe);
        return new ResponseEntity<>(classe, HttpStatus.OK);
    }

    @PutMapping("/{id}/archiver")
    public void archiverClasse(@PathVariable("id") Long id) {
        classeService.archiverClasse(id);
    }

    @PutMapping("/{id}/activer")
    public void activerClasse(@PathVariable("id") Long id) {
        classeService.activerClasse(id);
    }

    @DeleteMapping("/{id}")
    public void deleteClasse(@PathVariable("id") Long id) {
        classeService.deleteClasse(id);
    }
}
