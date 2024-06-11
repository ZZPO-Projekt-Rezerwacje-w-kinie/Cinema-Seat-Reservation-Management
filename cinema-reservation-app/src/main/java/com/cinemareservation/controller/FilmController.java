package com.cinemareservation.controller;

import com.cinemareservation.model.Film;
import com.cinemareservation.repository.CinemaSeatRepository;
import com.cinemareservation.repository.FilmRepository;
import com.cinemareservation.repository.SeansRepository;
import com.cinemareservation.repository.TakenSeatsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller

public class FilmController {

    private final FilmRepository filmRepository;
    private final SeansRepository seansRepository;
    private final CinemaSeatRepository cinemaSeatRepository;
    private final TakenSeatsRepository takenSeatsRepository;

    public FilmController(FilmRepository filmRepository, SeansRepository seansRepository, CinemaSeatRepository cinemaSeatRepository, TakenSeatsRepository takenSeatsRepository) {
        this.filmRepository = filmRepository;
        this.seansRepository = seansRepository;
        this.cinemaSeatRepository = cinemaSeatRepository;
        this.takenSeatsRepository = takenSeatsRepository;
    }

    @GetMapping("/films")
    public String showFilmList(Model model) {
        model.addAttribute("films", filmRepository.findAll());
        return "films";
    }

    @GetMapping("/addFilmForm")
    public String showFilmForm(Film film) {
        return "add_film";
    }

    @PostMapping("/addFilm")
    public String addFilm(@Valid Film film, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_film";
        }
        filmRepository.save(film);
        return "redirect:/films";
    }

    @GetMapping("/editFilm/{id}")
    public String showEditFilmForm(@PathVariable("id") Long id, Model model) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isPresent()) {
            model.addAttribute("film", film.get());
            return "edit_film";
        } else {
            return "redirect:/films";
        }
    }

    @PostMapping("/updateFilm/{id}")
    public String updateFilm(@PathVariable("id") Long id, @Valid Film film, BindingResult result) {
        if (result.hasErrors()) {
            film.setId(id);
            return "edit_film";
        }
        Film existingFilm = filmRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid film Id:" + id));
        existingFilm.setTytul(film.getTytul());
        existingFilm.setRokProdukcji(film.getRokProdukcji());
        existingFilm.setCzasTrwania(film.getCzasTrwania());
        existingFilm.setKategoria(film.getKategoria());
        filmRepository.save(existingFilm);
        return "redirect:/films";
    }


    @PostMapping("/deleteFilm/{id}")
    @Transactional
    public String deleteFilm(@PathVariable("id") Long id) {
        seansRepository.findByFilmId(id).forEach(seans -> {
            cinemaSeatRepository.findBySeansId(seans.getId()).forEach(miejsce -> {
                takenSeatsRepository.deleteByMiejsca_Id(miejsce.getId());
                cinemaSeatRepository.deleteById(miejsce.getId());
            });
            seansRepository.deleteById(seans.getId());
        });
        filmRepository.deleteById(id);
        return "redirect:/films";
    }
}
