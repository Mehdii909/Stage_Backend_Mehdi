package com.example.stage_backend.interfaces;

import com.example.stage_backend.entities.AnneeScolaire;

import java.util.List;

public interface IAnneeScolaire {

    List<AnneeScolaire> getAll();
    Object getAnneeScolaireById(Long id);
    List<AnneeScolaire> getAllAnneeScolairesEtatActiver();
    List<AnneeScolaire> getAllAnneeScolairesEtatArchiver();
    void saveAnneeScolaire(AnneeScolaire anneeScolaire);
    void updateAnneeScolaire(Long id, AnneeScolaire anneeScolaire);
    void archiverAnneeScolaire(Long anneeScolaireId);
    void activerAnneeScolaire(Long anneeScolaireId);
    void deleteAnneeScolaire(Long anneeScolaireId);
}
