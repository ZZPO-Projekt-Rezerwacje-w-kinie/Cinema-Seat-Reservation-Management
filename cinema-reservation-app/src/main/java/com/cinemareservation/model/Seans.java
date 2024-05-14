package com.cinemareservation.model;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Builder
@Setter
@Getter

@Entity
@Table(name = "Seans")
public class Seans {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_film", nullable = false)
    private Film film;

    private LocalDateTime dataCzas;

    @ManyToOne
    @JoinColumn(name = "id_sala", nullable = false)
    private Sala sala;

}
