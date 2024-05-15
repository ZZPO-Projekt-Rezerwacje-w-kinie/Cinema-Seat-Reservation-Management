package com.cinemareservation.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter

@Entity
@Table(name = "Pracownik")
public class Pracownik {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Integer id;

    @Column(nullable = false)
    private String imie;

    @Column(nullable = false)
    private String haslo;

    private LocalDateTime dataZatrudnienia;

    @Column(nullable = false, unique = true)
    private String email;

}
