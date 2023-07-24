package com.example.stage_backend.dao;


import com.example.stage_backend.entities.Personnel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




import java.util.List;
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    List<Personnel> findPersonnelByEtat(String etat, Sort sort);
}

