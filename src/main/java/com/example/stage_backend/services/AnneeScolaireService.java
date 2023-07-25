package com.example.stage_backend.services;

import com.example.stage_backend.dao.AnneeScolaireRepository;
import com.example.stage_backend.entities.AnneeScolaire;
import com.example.stage_backend.interfaces.IAnneeScolaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnneeScolaireService implements IAnneeScolaire {

    private final AnneeScolaireRepository anneeScolaireRepository;

    @Autowired
    public AnneeScolaireService(AnneeScolaireRepository anneeScolaireRepository) {
        this.anneeScolaireRepository = anneeScolaireRepository;
    }

    @Override
    public List<AnneeScolaire> getAll() {
        return anneeScolaireRepository.findAll();
    }

    @Override
    public Optional<AnneeScolaire> getAnneeScolaireById(Long id) {
        return anneeScolaireRepository.findById(id);
    }

    @Override
    public void saveAnneeScolaire(AnneeScolaire anneeScolaire) {
        anneeScolaireRepository.save(anneeScolaire);
    }

    @Override
    public void updateAnneeScolaire(Long id, AnneeScolaire anneeScolaire) {
        AnneeScolaire existingAnneeScolaire = anneeScolaireRepository.findById(id).orElse(null);

        if (existingAnneeScolaire != null) {
            existingAnneeScolaire.setAns(anneeScolaire.getAns());
            existingAnneeScolaire.setEtat(anneeScolaire.getEtat());

            anneeScolaireRepository.save(existingAnneeScolaire);
        } else {
            throw new IllegalArgumentException("AnneeScolaire with ID " + id + " does not exist.");
        }
    }

    @Override
    public void deleteAnneeScolaire(Long id) {
        AnneeScolaire anneeScolaire = anneeScolaireRepository.findById(id).orElse(null);

        if (anneeScolaire != null) {
            anneeScolaireRepository.delete(anneeScolaire);
        } else {
            throw new IllegalArgumentException("AnneeScolaire with ID " + id + " does not exist.");
        }
    }

    @Override
    public void archiverAnneeScolaire(Long anneeScolaireId) {
        AnneeScolaire anneeScolaire = anneeScolaireRepository.findById(anneeScolaireId).orElse(null);

        if (anneeScolaire != null) {
            anneeScolaire.setEtat("archiver");
            anneeScolaireRepository.save(anneeScolaire);
        } else {
            throw new IllegalArgumentException("AnneeScolaire with ID " + anneeScolaireId + " does not exist.");
        }
    }

    @Override
    public void activerAnneeScolaire(Long anneeScolaireId) {
        AnneeScolaire anneeScolaire = anneeScolaireRepository.findById(anneeScolaireId).orElse(null);

        if (anneeScolaire != null) {
            anneeScolaire.setEtat("activer");
            anneeScolaireRepository.save(anneeScolaire);
        } else {
            throw new IllegalArgumentException("AnneeScolaire with ID " + anneeScolaireId + " does not exist.");
        }
    }

    @Override
    public List<AnneeScolaire> getAllAnneeScolairesEtatActiver() {
        return anneeScolaireRepository.findByEtat("activer", Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<AnneeScolaire> getAllAnneeScolairesEtatArchiver() {
        return anneeScolaireRepository.findByEtat("archiver", Sort.by(Sort.Direction.ASC, "id"));
    }

}
