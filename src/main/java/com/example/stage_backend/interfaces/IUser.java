package com.example.stage_backend.interfaces;

import com.example.stage_backend.entities.Eleve;
import com.example.stage_backend.entities.User;

import java.util.List;

public interface IUser {
    List<User> getAll();
    Object getUserById(Long id);
    void saveUser(User user);
    void updateUser(Long id,User user);
    void deleteUser(Long UserId);
}
