package com.example.stage_backend.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="eleve", schema = "public")
public class Eleve implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String nomPere;
    private String prenomPere;
    private String nomMere;
    private String prenomMere;
    private String nationalite;
    private String email;
    private String etat;

    @ElementCollection
    @CollectionTable(name = "eleve_numtel", joinColumns = @JoinColumn(name = "eleve_id"))
    @Column(name = "numtel")
    private List<String> numTels;
    // Ajoutez ici une liste pour les numéros de parents si vous souhaitez en ajouter plusieurs

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
