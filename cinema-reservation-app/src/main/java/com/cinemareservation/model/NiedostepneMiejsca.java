package com.cinemareservation.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter

@Entity
@Table(name = "Niedostepne_Miejsca")
public class NiedostepneMiejsca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_seansu", nullable = false)
    private Seans seans;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_miejsca", nullable = false)
    private Miejsce miejsce;

}
