package com.example.stage_backend.entities;
import com.example.stage_backend.enums.ERole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="user", schema = "public")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private ERole userRole;

    @JsonIgnore // Ignorer la sérialisation de cette propriété
    @OneToOne(mappedBy = "user")
    private Eleve eleve;
}