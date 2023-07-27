package com.example.stage_backend.services;

import com.example.stage_backend.dao.ClasseRepository;
import com.example.stage_backend.entities.Classe;
import com.example.stage_backend.interfaces.IClasse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseService implements IClasse {

    @Autowired
    ClasseRepository classeRepository;

    @Override
    public List<Classe> getAll() {
        return classeRepository.findAll();
    }

    @Override
    public Optional<Classe> getClasseById(Long id) {
        return classeRepository.findById(id);
    }

    @Override
    public List<Classe> getAllClasseEtatActiver() {
        return classeRepository.findByEtat("activer", Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Classe> getAllClasseEtatArchiver() {
        return classeRepository.findByEtat("archiver", Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public void saveClasse(Classe classe) {
        classeRepository.save(classe);
    }

    @Override
    public void updateClasse(Long id, Classe classe) {
        Classe classeExistante = classeRepository.findById(classe.getId()).orElse(null);

        if (classeExistante != null) {
            classeExistante.setCycle(classe.getCycle());
            classeExistante.setNiveau(classe.getNiveau());
            classeExistante.setNumClasse(classe.getNumClasse());
            classeExistante.setAnneeScolaire(classe.getAnneeScolaire());

            classeRepository.save(classeExistante);
        } else {
            throw new IllegalArgumentException("La classe avec l'ID " + classe.getId() + " n'existe pas.");
        }
    }

    @Override
    public void archiverClasse(Long classeId) {
        Classe classe = classeRepository.findById(classeId).orElse(null);

        if (classe != null) {
            classe.setEtat("archiver");
            classeRepository.save(classe);
        } else {
            throw new IllegalArgumentException("La classe avec l'ID " + classeId + " n'existe pas.");
        }
    }

    @Override
    public void activerClasse(Long classeId) {
        Classe classe = classeRepository.findById(classeId).orElse(null);

        if (classe != null) {
            classe.setEtat("activer");
            classeRepository.save(classe);
        } else {
            throw new IllegalArgumentException("La classe avec l'ID " + classeId + " n'existe pas.");
        }
    }

    @Override
    public void deleteClasse(Long classeId) {
        Classe classe = classeRepository.findById(classeId).orElse(null);

        if (classe != null) {
            classeRepository.delete(classe);
        } else {
            throw new IllegalArgumentException("La classe avec l'ID " + classeId + " n'existe pas.");
        }
    }
}
