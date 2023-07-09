package com.example.stage_backend.services;

import com.example.stage_backend.dao.EleveRepository;
import com.example.stage_backend.entities.Eleve;
import com.example.stage_backend.interfaces.IEleve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EleveService implements IEleve {

    @Autowired
    EleveRepository eleveRepository;

    @Override
    public List<Eleve> getAll() {
        return eleveRepository.findAll();
    }

    @Override
    public Optional<Eleve> getEleveById(Long id){
        return eleveRepository.findById(id);
    }

    public List<Eleve> getAllEleveEtatActif() {
        return eleveRepository.findByEtat("actif");
    }

    public List<Eleve> getAllEleveEtatPassif() {
        return eleveRepository.findByEtat("passif");
    }

    @Override
    public void saveEleve(Eleve eleve) {
        // Logique pour ajouter un élève
        eleveRepository.save(eleve);

        // Envoi de l'e-mail au nouvel élève
        String recipientEmail = eleve.getEmail();
        String login = eleve.getUser().getLogin();
        String password = eleve.getUser().getPassword();
        EmailSender.sendEmail(recipientEmail, login, password);
    }

    @Override
    public void updateEleve(Long id, Eleve eleve) {
        Eleve eleveExistant = eleveRepository.findById(eleve.getId()).orElse(null);

        if (eleveExistant != null) {
            // Mettre à jour les attributs de l'élève existant avec les nouvelles valeurs
            eleveExistant.setNom(eleve.getNom());
            eleveExistant.setPrenom(eleve.getPrenom());
            eleveExistant.setNomPere(eleve.getNomPere());
            eleveExistant.setPrenomPere(eleve.getPrenomPere());
            eleveExistant.setNomMere(eleve.getNomMere());
            eleveExistant.setPrenomMere(eleve.getPrenomMere());
            eleveExistant.setNationalite(eleve.getNationalite());
            eleveExistant.setEmail(eleve.getEmail());
            eleveExistant.setEtat(eleve.getEtat());
            eleveExistant.setNumTels(eleve.getNumTels());
            eleveExistant.setUser(eleve.getUser());
            // Mettre à jour les autres attributs selon les besoins

            // Enregistrer les modifications dans la base de données
            eleveRepository.save(eleveExistant);
        } else {
            // Gérer le cas où l'élève n'existe pas dans la base de données
            throw new IllegalArgumentException("L'élève avec l'ID " + eleve.getId() + " n'existe pas.");
        }
    }

    @Override
    public void archiverEleve(Long eleveId) {
        // Logique pour archiver un élève
        Eleve eleve = eleveRepository.findById(eleveId).orElse(null);

        if (eleve != null) {
            // Vérifier si l'élève est déjà actif
            if (eleve.getEtat().equals("actif")) {
                // Changer l'état de l'élève à "passif"
                eleve.setEtat("passif");

                // Enregistrer la modification dans la base de données
                eleveRepository.save(eleve);
            } else {
                throw new IllegalArgumentException("L'élève avec l'ID " + eleveId + " est déjà dans l'état passif.");
            }
        } else {
            throw new IllegalArgumentException("L'élève avec l'ID " + eleveId + " n'existe pas.");
        }
    }

    @Override
    public void activerEleve(Long eleveId) {
        // Logique pour archiver un élève
        Eleve eleve = eleveRepository.findById(eleveId).orElse(null);

        if (eleve != null) {
            // Vérifier si l'élève est déjà actif
            if (eleve.getEtat().equals("passif")) {
                // Changer l'état de l'élève à "passif"
                eleve.setEtat("actif");

                // Enregistrer la modification dans la base de données
                eleveRepository.save(eleve);
            } else {
                throw new IllegalArgumentException("L'élève avec l'ID " + eleveId + " est déjà dans l'état actif.");
            }
        } else {
            throw new IllegalArgumentException("L'élève avec l'ID " + eleveId + " n'existe pas.");
        }
    }

    @Override
    public void deleteEleve(Long eleveId) {
        // Logique pour supprimer un élève
        Eleve eleve = eleveRepository.findById(eleveId).orElse(null);

        if (eleve != null) {
            eleveRepository.delete(eleve);
        } else {
            throw new IllegalArgumentException("L'élève avec l'ID " + eleveId + " n'existe pas.");
        }
    }
}
