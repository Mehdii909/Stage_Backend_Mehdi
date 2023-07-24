package com.example.stage_backend.services;

import com.example.stage_backend.dao.ChauffeurRepository;
import com.example.stage_backend.entities.Chauffeur;
import com.example.stage_backend.interfaces.IChauffeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChauffeurService implements IChauffeur {

    private final ChauffeurRepository chauffeurRepository;

    @Autowired
    public ChauffeurService(ChauffeurRepository chauffeurRepository) {
        this.chauffeurRepository = chauffeurRepository;
    }

    @Override
    public List<Chauffeur> getAll() {
        return chauffeurRepository.findAll();
    }

    @Override
    public Optional<Chauffeur> getChauffeurById(Long id) {
        return chauffeurRepository.findById(id);
    }

    @Override
    public void saveChauffeur(Chauffeur chauffeur) {
        chauffeurRepository.save(chauffeur);
    }

    @Override
    public void updateChauffeur(Long id, Chauffeur chauffeur) {
        Chauffeur existingChauffeur = chauffeurRepository.findById(id).orElse(null);

        if (existingChauffeur != null) {
            existingChauffeur.setNom(chauffeur.getNom());
            existingChauffeur.setPrenom(chauffeur.getPrenom());
            existingChauffeur.setEmail(chauffeur.getEmail());
            existingChauffeur.setNumTels(chauffeur.getNumTels());
            existingChauffeur.setEtat(chauffeur.getEtat());

            chauffeurRepository.save(existingChauffeur);
        } else {
            throw new IllegalArgumentException("Chauffeur with ID " + id + " does not exist.");
        }
    }

    @Override
    public void archiverChauffeur(Long chauffeurId) {
        Chauffeur chauffeur = chauffeurRepository.findById(chauffeurId).orElse(null);

        if (chauffeur != null) {
            chauffeur.setEtat("archiver");
            chauffeurRepository.save(chauffeur);
        } else {
            throw new IllegalArgumentException("Chauffeur with ID " + chauffeurId + " does not exist.");
        }
    }

    @Override
    public void activerChauffeur(Long chauffeurId) {
        Chauffeur chauffeur = chauffeurRepository.findById(chauffeurId).orElse(null);

        if (chauffeur != null) {
            chauffeur.setEtat("activer");
            chauffeurRepository.save(chauffeur);
        } else {
            throw new IllegalArgumentException("Chauffeur with ID " + chauffeurId + " does not exist.");
        }
    }

    @Override
    public void deleteChauffeur(Long id) {
        Chauffeur chauffeur = chauffeurRepository.findById(id).orElse(null);

        if (chauffeur != null) {
            chauffeurRepository.delete(chauffeur);
        } else {
            throw new IllegalArgumentException("Chauffeur with ID " + id + " does not exist.");
        }
    }

    @Override
    public List<Chauffeur> getAllChauffeursEtatActiver() {
        return chauffeurRepository.findByEtat("activer", Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Chauffeur> getAllChauffeursEtatArchiver() {
        return chauffeurRepository.findByEtat("archiver", Sort.by(Sort.Direction.ASC, "id"));
    }
}
