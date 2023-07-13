package com.example.stage_backend.interfaces;

import com.example.stage_backend.entities.Eleve;

import java.util.List;

public interface IEleve {

    List<Eleve> getAll();
    Object getEleveById(Long id);
    List<Eleve> getAllEleveEtatActiver();
    List<Eleve> getAllEleveEtatArchiver();
    void saveEleve(Eleve eleve);
    void updateEleve(Long id, Eleve eleve);
    void archiverEleve(Long eleveId);
    void activerEleve(Long eleveId);
    void deleteEleve(Long eleveId);
}
