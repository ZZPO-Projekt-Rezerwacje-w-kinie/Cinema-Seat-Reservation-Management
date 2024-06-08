package com.cinemareservation.model;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter

@Entity
@Table(name = "Miejsca")
public class Miejsca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_seansu", nullable = false)
    private Seans seans;

    private Integer rzad;
    private Integer miejsce_w_rzedzie;

    public Miejsca(Seans seans, int i, int j) {
        this.seans = seans;
        this.rzad = i;
        this.miejsce_w_rzedzie = j;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRzad() {
        return rzad;
    }
    public Integer getMiejsce_w_rzedzie() {
        return miejsce_w_rzedzie;
    }
}
