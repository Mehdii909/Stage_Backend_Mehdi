package com.example.stage_backend.services;


import com.example.stage_backend.dao.PersonnelRepository;
import com.example.stage_backend.dao.UserRepository;
import com.example.stage_backend.entities.Personnel;
import com.example.stage_backend.entities.User;
import com.example.stage_backend.interfaces.IPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import java.util.List;
import java.util.Optional;




@Service
public class PersonnelService implements IPersonnel {

    @Autowired
    PersonnelRepository personnelRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Personnel> getAll() {
        return personnelRepository.findAll();
    }

    @Override
    public Optional <Personnel> getPersonnelById(Long id){
        return personnelRepository.findById(id);
    }

    public List<Personnel> getAllPersonnelEtatActiver() {
        return personnelRepository.findPersonnelByEtat("activer");
    }

    public List<Personnel> getAllPersonnelEtatArchiver() {
        return personnelRepository.findPersonnelByEtat("archiver");
    }

    @Override
    public void savePersonnel(Personnel personnel) {
        User user = personnel.getUser();

        // Save the User entity
        userRepository.save(user);

        // Set the saved User entity on the Personnel entity
        personnel.setUser(user);

        // Ajouter personnel
        personnelRepository.save(personnel);

        // Envoi de l'e-mail au personnel
        String recipientEmail = personnel.getEmail();
        String login = personnel.getUser().getLogin();
        String password = personnel.getUser().getPassword();
        EmailSender.sendEmailPersonnel(recipientEmail, login, password);
    }




    @Override
    public void updatePersonnel(Long id, Personnel personnel) {
        Personnel existingPersonnel = personnelRepository.findById(id).orElse(null);
        if (existingPersonnel != null) {
// Effectuez les mises à jour nécessaires sur l'objet existingPersonnel
            existingPersonnel.setNom(personnel.getNom());
            existingPersonnel.setPrenom(personnel.getPrenom());
            existingPersonnel.setNum(personnel.getNum());
            existingPersonnel.setEmail(personnel.getEmail());
            existingPersonnel.setFonction(personnel.getFonction());
            existingPersonnel.setUser(personnel.getUser());




// Enregistrer les modifications dans la base de données
            personnelRepository.save(existingPersonnel);
        } else {
// Gérer le cas où le personnel n'existe pas dans la base de données
            throw new IllegalArgumentException("Le personnel avec l'Id " + personnel.getId() + " n'existe pas.");
        }
    }




    @Override
    public void archiverPersonnel(Long personnelId) {
// Archiver un personnel
        Personnel personnel = personnelRepository.findById(personnelId).orElse(null);




        if (personnel != null) {
// Vérifier si le personnel est déjà actif
            if (personnel.getEtat().equals("activer")) {
// Changer l'état de l'élève à "passif"
                personnel.setEtat("archiver");




// Enregistrer la modification dans la base de données
                personnelRepository.save(personnel);
            } else {
                throw new IllegalArgumentException("Le personnel avec l'Id " + personnelId + " est déjà dans l'état archiver.");
            }
        } else {
            throw new IllegalArgumentException("Le personnel avec l'Id " + personnelId + " n'existe pas.");
        }
    }




    @Override
    public void activerPersonnel(Long personnelId) {




        Personnel personnel = personnelRepository.findById(personnelId).orElse(null);




        if (personnel != null) {
// Vérifier si le personnel est déjà actif
            if (personnel.getEtat().equals("archiver")) {
// Changer l'état du personnel à "passif"
                personnel.setEtat("activer");




// Enregistrer la modification dans la base de données
                personnelRepository.save(personnel);
            } else {
                throw new IllegalArgumentException("Le personnel avec l'Id " + personnelId + " est déjà dans l'état activer.");
            }
        } else {
            throw new IllegalArgumentException("Le personnel avec l'Id " + personnelId + " n'existe pas.");
        }
    }




    @Override
    public void deletePersonnel(Long personnelId) {
// Logique pour supprimer un élève
        Personnel personnel = personnelRepository.findById(personnelId).orElse(null);




        if (personnel != null) {
            personnelRepository.delete(personnel);
        } else {
            throw new IllegalArgumentException("Le personnel avec l'Id " + personnelId + " n'existe pas.");
        }
    }
}
