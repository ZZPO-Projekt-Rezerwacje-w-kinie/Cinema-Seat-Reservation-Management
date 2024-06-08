package com.cinemareservation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Film")  // Ensure this matches the actual table name in your database
public class Film {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_generator")
//    @SequenceGenerator(name = "film_generator", sequenceName = "seq_film_id", allocationSize = 1)
//    private Integer id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Use the database's auto-incrementing mechanism.
    private Long id;


    @NotBlank(message = "Tytuł jest obowiązkowy")
    @Column(name = "tytul", nullable = false)
    private String tytul;

    @NotBlank(message = "Opis jest obowiązkowy")
    @Column(name = "opis")
    private String opis;

    @NotNull(message = "Rok produkcji jest obowiązkowy")
    @Positive(message = "Rok produkcji musi być dodatnią liczbą")
    @Column(name = "rok_produkcji")
    private Integer rokProdukcji;


    @NotNull(message = "Czas trwania jest obowiązkowy")
    @Positive(message = "Czas trwania musi być dodatnią liczbą")
    @Column(name = "czas_trwania")
    private Integer czasTrwania;

    @NotBlank(message = "Kategoria jest obowiązkowa")
    @Column(name = "kategoria")
    private String kategoria;

    // Constructors
    public Film() {
    }

    public Film(String tytul, String opis, Integer rokProdukcji, Integer czasTrwania, String kategoria) {
        this.tytul = tytul;
        this.opis = opis;
        this.rokProdukcji = rokProdukcji;
        this.czasTrwania = czasTrwania;
        this.kategoria = kategoria;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Integer getRokProdukcji() {
        return rokProdukcji;
    }

    public void setRokProdukcji(Integer rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    public Integer getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(Integer czasTrwania) {
        this.czasTrwania = czasTrwania;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }
}
