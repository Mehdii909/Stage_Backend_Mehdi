package com.example.stage_backend.dao;

import com.example.stage_backend.entities.Station;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    List<Station> findByEtat(String etat, Sort sort);
    // Ajoutez ici des méthodes personnalisées si nécessaire
}

