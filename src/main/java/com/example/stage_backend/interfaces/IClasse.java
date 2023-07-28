package com.example.stage_backend.interfaces;

import com.example.stage_backend.entities.Bus;
import com.example.stage_backend.entities.Classe;
import com.example.stage_backend.entities.Classe;

import java.util.List;

public interface IClasse {
    List<Classe> getAll();
    Object getClasseById(Long id);
    List<Classe> getAllClasseEtatActiver();
    List<Classe> getAllClasseEtatArchiver();
    void saveClasse(Classe Classe);
    void updateClasse(Long id, Classe classe);
    void archiverClasse(Long classeId);
    void activerClasse(Long classeId);
    void deleteClasse(Long classeId);

    List<Classe> getAllClasseEtatActiverByAnsId(Long ansId);

}
