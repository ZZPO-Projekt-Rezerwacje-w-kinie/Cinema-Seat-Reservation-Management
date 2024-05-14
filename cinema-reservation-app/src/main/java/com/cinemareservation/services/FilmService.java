package com.cinemareservation.services;

import com.cinemareservation.exceptions.ResourceNotFoundException;
import com.cinemareservation.model.Film;
import com.cinemareservation.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Film save(Film film) {
        return filmRepository.save(film);
    }

    public Film update(Integer id, Film filmDetails) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film not found for this id :: " + id));

        film.setTytul(filmDetails.getTytul());
        film.setOpis(filmDetails.getOpis());
        film.setRokProdukcji(filmDetails.getRokProdukcji());
        film.setCzasTrwania(filmDetails.getCzasTrwania());
        film.setKategoria(filmDetails.getKategoria());

        return filmRepository.save(film);
    }

    public void delete(Integer id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film not found for this id :: " + id));

        filmRepository.delete(film);
    }
}