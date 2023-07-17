
package com.example.stage_backend.entities;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name="personnel", schema = "public")
public class Personnel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nom;
    private String prenom;
    private String email;
    private String fonction;
    private String etat;


    @ElementCollection
    @CollectionTable(name = "personnel_numtel", joinColumns = @JoinColumn(name = "personnel_id"))
    @Column(name = "num")
    private List<String> num;
    // Ajoutez ici une liste pour les numéros de parents si vous souhaitez en ajouter plusieurs


    @OneToOne //(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
