package com.example.stage_backend.interfaces;


import com.example.stage_backend.entities.Eleve;
import com.example.stage_backend.entities.Personnel;




import java.util.List;




public interface IPersonnel {
    List<Personnel> getAll();
    Object getPersonnelById(Long id);
    List<Personnel> getAllPersonnelEtatActiver();
    List<Personnel> getAllPersonnelEtatArchiver();
    void savePersonnel(Personnel personnel);
    void updatePersonnel(Long id, Personnel personnel);
    void archiverPersonnel(Long personnelId);
    void activerPersonnel(Long personnelId);
    void deletePersonnel(Long personnelId);
}
