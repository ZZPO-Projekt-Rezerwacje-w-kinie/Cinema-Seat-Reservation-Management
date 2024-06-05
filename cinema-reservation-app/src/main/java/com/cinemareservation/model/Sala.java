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

    @Column(nullable = false)
    private String adres_budynku;

    private int il_miejsc;
    private int il_rzedow;
    private int il_miejsc_w_rzedzie;


    //przemyslec czy dac na enum
    @ManyToOne()
    @JoinColumn(name = "id_typ_sali", nullable = false)
    private TypSali typSali;

    public int getRowsNumber() {
        return il_rzedow;
    }

    public int getColumnsNumber() {
        return il_miejsc_w_rzedzie;
    }
}
