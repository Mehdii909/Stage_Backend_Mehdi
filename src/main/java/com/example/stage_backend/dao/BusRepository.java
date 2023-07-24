package com.example.stage_backend.dao;

import com.example.stage_backend.entities.Bus;
import com.example.stage_backend.entities.Classe;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Long> {
    List<Bus> findByEtat(String etat, Sort sort);
    // Ajoutez des méthodes supplémentaires de requête personnalisées si nécessaire
}
