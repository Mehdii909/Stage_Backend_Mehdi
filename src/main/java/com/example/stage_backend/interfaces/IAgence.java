package com.example.stage_backend.interfaces;

import com.example.stage_backend.entities.Agence;

import java.util.List;

public interface IAgence {

    List<Agence> getAll();
    Object getAgenceById(Long id);
    List<Agence> getAllAgencesEtatActiver();
    List<Agence> getAllAgencesEtatArchiver();
    void saveAgence(Agence agence);
    void updateAgence(Long id, Agence agence);
    void archiverAgence(Long agenceId);
    void activerAgence(Long agenceId);
    void deleteAgence(Long AgenceId);
}
