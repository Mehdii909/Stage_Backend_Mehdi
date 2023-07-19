package com.example.stage_backend.services;

import com.example.stage_backend.dao.AgenceRepository;
import com.example.stage_backend.entities.Agence;
import com.example.stage_backend.interfaces.IAgence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenceService implements IAgence {

    private final AgenceRepository agenceRepository;

    @Autowired
    public AgenceService(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    @Override
    public List<Agence> getAll() {
        return agenceRepository.findAll();
    }

    @Override
    public Optional<Agence> getAgenceById(Long id) {
        return agenceRepository.findById(id);
    }

    @Override
    public void saveAgence(Agence agence) {
        agenceRepository.save(agence);
    }

    @Override
    public void updateAgence(Long id, Agence agence) {
        Agence existingAgence = agenceRepository.findById(id).orElse(null);

        if (existingAgence != null) {
            existingAgence.setNom(agence.getNom());
            existingAgence.setAdresseSiege(agence.getAdresseSiege());
            existingAgence.setResponsable(agence.getResponsable());
            existingAgence.setNumTels(agence.getNumTels());
            existingAgence.setInfoSupp(agence.getInfoSupp());

            agenceRepository.save(existingAgence);
        } else {
            throw new IllegalArgumentException("Agence with ID " + id + " does not exist.");
        }
    }

    @Override
    public void archiverAgence(Long agenceId) {
        Agence agence = agenceRepository.findById(agenceId).orElse(null);

        if (agence != null) {
            agence.setEtat("archiver");
            agenceRepository.save(agence);
        } else {
            throw new IllegalArgumentException("La agence avec l'ID " + agenceId + " n'existe pas.");
        }
    }

    @Override
    public void activerAgence(Long agenceId) {
        Agence agence = agenceRepository.findById(agenceId).orElse(null);

        if (agence != null) {
            agence.setEtat("activer");
            agenceRepository.save(agence);
        } else {
            throw new IllegalArgumentException("La agence avec l'ID " + agenceId + " n'existe pas.");
        }
    }


    @Override
    public void deleteAgence(Long id) {
        Agence agence = agenceRepository.findById(id).orElse(null);

        if (agence != null) {
            agenceRepository.delete(agence);
        } else {
            throw new IllegalArgumentException("Agence with ID " + id + " does not exist.");
        }
    }

    @Override
    public List<Agence> getAllAgencesEtatActiver() {
        return agenceRepository.findByEtat("activer");
    }

    @Override
    public List<Agence> getAllAgencesEtatArchiver() {
        return agenceRepository.findByEtat("archiver");
    }
}
