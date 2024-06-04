package com.cinemareservation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter

@Entity
@Table(name = "seans")
public class Seans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Correct usage for auto-increment
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)  // Using lazy loading for associated entities
    @JoinColumn(name = "id_film", nullable = false)
    private Film film;

    @Column(name = "data_czas")  // Ensures the column name matches the database
    private LocalDateTime dataCzas;

    @ManyToOne(fetch = FetchType.LAZY)  // Using lazy loading here as well
    @JoinColumn(name = "id_sala", nullable = false)
    private Sala sala;

    @OneToMany(mappedBy = "seans", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Miejsce> miejsca;

    public Film getFilm() {
        return film;
    }
}
