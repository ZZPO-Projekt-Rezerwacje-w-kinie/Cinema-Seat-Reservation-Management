package com.cinemareservation.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter

@Entity
@Table(name = "miejsca")
public class Miejsce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seansu", nullable = false)
    private Seans seans;

    @NotNull
    private Integer rzad;

    @NotNull
    @Column(name = "miejsce_w_rzedzie")
    private Integer miejsceWRzedzie;

    boolean zarezerwowane;

    @OneToMany(mappedBy = "miejsce", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NiedostepneMiejsca> niedostepneMiejsca;
}
