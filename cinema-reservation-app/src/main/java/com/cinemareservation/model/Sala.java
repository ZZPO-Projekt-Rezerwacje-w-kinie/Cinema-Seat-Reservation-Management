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

    private Integer il_miejsc;
    private Integer il_rzedow;
    private Integer il_miejsc_w_rzedzie;


    //przemyslec czy dac na enum
    @ManyToOne()
    @JoinColumn(name = "id_typ_sali", nullable = false)
    private TypSali typSali;

}
