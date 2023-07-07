package com.example.stage_backend.services;

import com.example.stage_backend.dao.UserRepository;
import com.example.stage_backend.entities.Eleve;
import com.example.stage_backend.entities.User;
import com.example.stage_backend.interfaces.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUser {

    // Injecter le repository userRepository si nécessaire
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id){
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("L'utilisateur avec l'ID " + id + " n'existe pas.");
        }

        return user;    }
    @Override
    public void saveUser(User user) {
        // Logique pour sauvegarder un utilisateur
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        // Logique pour mettre à jour un utilisateur
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            existingUser.setLogin(user.getLogin());
            existingUser.setPassword(user.getPassword());
            // Mettre à jour d'autres attributs selon les besoins

            userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("L'utilisateur avec l'ID " + id + " n'existe pas.");
        }
    }

    @Override
    public void deleteUser(Long userId) {
        // Logique pour supprimer un utilisateur
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            userRepository.deleteById(userId);
        } else {
            throw new IllegalArgumentException("L'utilisateur avec l'ID " + userId + " n'existe pas.");
        }
    }
}