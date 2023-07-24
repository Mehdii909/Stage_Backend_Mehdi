package com.example.stage_backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "agence")
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String adresseSiege;
    private String responsable;
    private String email;

    @ElementCollection
    @CollectionTable(name = "agence_numtel", joinColumns = @JoinColumn(name = "agence_id"))
    @Column(name = "numtel")
    private List<String> numTels;

    private String etat;
    private String infoSupp;

}

