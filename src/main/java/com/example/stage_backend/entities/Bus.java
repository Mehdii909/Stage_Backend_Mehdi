package com.example.stage_backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String immatriculation;
    private String marqueModele;
    private int nombrePlaces;
    private String etat;


    @ManyToOne
    @JoinColumn(name = "agence_id")
    private Agence agence;

}
