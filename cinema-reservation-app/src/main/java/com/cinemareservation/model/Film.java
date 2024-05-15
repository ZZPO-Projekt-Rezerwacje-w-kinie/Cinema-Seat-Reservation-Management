package com.cinemareservation.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter

@Entity
@Table(name = "Film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String tytul;

    private String opis;
    private Integer rokProdukcji;
    private Integer czasTrwania;
    private String kategoria;

    // getters and setters
}
