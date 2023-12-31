package com.example.stage_backend.dao;

import com.example.stage_backend.entities.Classe;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
    List<Classe> findByEtat(String etat, Sort sort);

    List<Classe> findByEtatAndAnneeScolaire_Id(String etat, Long anneeScolaire_id);

    // Ajoutez des méthodes supplémentaires de requête personnalisées si nécessaire
}
