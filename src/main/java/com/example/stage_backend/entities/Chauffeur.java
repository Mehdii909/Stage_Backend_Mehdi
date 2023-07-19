package com.example.stage_backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "chauffeur")
public class Chauffeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;

    @ElementCollection
    @CollectionTable(name = "chauffeur_numtel", joinColumns = @JoinColumn(name = "chauffeur_id"))
    @Column(name = "numtel")
    private List<String> numTels;
    private String etat;

    @ManyToOne
    @JoinColumn(name = "agence_id")
    private Agence agence;

}

