package com.example.stage_backend.dao;

import com.example.stage_backend.entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
    List<Classe> findByEtat(String etat);
    // Ajoutez des méthodes supplémentaires de requête personnalisées si nécessaire
        }
