package com.cinemareservation.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Setter
@Getter

@Entity
@Table(name = "Niedostepne_Miejsca")
public class NiedostepneMiejsca {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_seansu", nullable = false)
    private Seans seans;

    @ManyToOne
    @JoinColumn(name = "id_miejsca", nullable = false)
    private Miejsca miejsce;

}
