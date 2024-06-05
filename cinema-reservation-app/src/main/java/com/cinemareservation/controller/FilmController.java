package com.cinemareservation.controller;

import com.cinemareservation.model.Film;
import com.cinemareservation.repository.FilmRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller

public class FilmController {

    private final FilmRepository filmRepository;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
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
}
