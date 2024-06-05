package com.cinemareservation.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter

@Entity
@Table(name = "Sala")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "adres_budynku", nullable = false)
    private String adresBudynku;

    @Column(name = "il_miejsc", nullable = false)
    private Integer ilMiejsc;

    @Column(name = "il_rzedow", nullable = false)
    private Integer ilRzedow;

    @Column(name = "il_miejsc_w_rzedzie", nullable = false)
    private Integer ilMiejscWRzedzie;

    @ManyToOne
    @JoinColumn(name = "id_typ_sali", nullable = false)
    private TypSali typSali;

    // Metoda getRowsNumber
    public Integer getRowsNumber() {
        return ilRzedow;
    }

    // Metoda getColumnsNumber
    public Integer getColumnsNumber() {
        return ilMiejscWRzedzie;
    }
}
