package com.example.stage_backend.dao;

import com.example.stage_backend.entities.Chauffeur;
import com.example.stage_backend.entities.Classe;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChauffeurRepository extends JpaRepository<Chauffeur, Long> {
    List<Chauffeur> findByEtat(String etat, Sort sort);
    // Ajoutez des méthodes supplémentaires de requête personnalisées si nécessaire
}
