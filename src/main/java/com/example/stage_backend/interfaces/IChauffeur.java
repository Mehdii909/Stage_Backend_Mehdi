package com.example.stage_backend.interfaces;

import com.example.stage_backend.entities.Chauffeur;

import java.util.List;

public interface IChauffeur {

    List<Chauffeur> getAll();
    Object getChauffeurById(Long id);
    List<Chauffeur> getAllChauffeursEtatActiver();
    List<Chauffeur> getAllChauffeursEtatArchiver();
    void saveChauffeur(Chauffeur chauffeur);
    void updateChauffeur(Long id, Chauffeur chauffeur);
    void archiverChauffeur(Long chauffeurId);
    void activerChauffeur(Long chauffeurId);
    void deleteChauffeur(Long chauffeurId);
}
