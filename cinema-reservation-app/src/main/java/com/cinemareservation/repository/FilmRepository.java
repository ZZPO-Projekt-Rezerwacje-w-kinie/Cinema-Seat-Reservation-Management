package com.cinemareservation.repository;

import com.cinemareservation.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class FilmRepository  {
    public Optional<Film> findById(Integer id) {
        return null;
    }

    public Film save(Film film) {
        return null;
    }

    public void delete(Film film) {

    }

    public List<Film> findAll() {
        return null;
    }
}
