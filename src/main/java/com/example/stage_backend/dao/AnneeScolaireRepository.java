package com.example.stage_backend.dao;

import com.example.stage_backend.entities.AnneeScolaire;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnneeScolaireRepository extends JpaRepository<AnneeScolaire, Long> {

    List<AnneeScolaire> findByEtat(String etat, Sort sort);

}
