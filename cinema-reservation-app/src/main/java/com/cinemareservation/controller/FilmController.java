package com.cinemareservation.controller;

import com.cinemareservation.model.Film;
import com.cinemareservation.repository.FilmRepository;
import com.cinemareservation.repository.MiejsceRepository;
import com.cinemareservation.repository.NiedostepneMiejsceRepository;
import com.cinemareservation.repository.SeansRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@Controller

public class FilmController {

    private final FilmRepository filmRepository;
    private final SeansRepository seansRepository;
    private final MiejsceRepository miejsceRepository;
    private final NiedostepneMiejsceRepository niedostepneMiejsceRepository;

    @Autowired
    public FilmController(FilmRepository filmRepository,
                          SeansRepository seansRepository,
                          MiejsceRepository miejsceRepository, NiedostepneMiejsceRepository niedostepneMiejsceRepository) {
        this.filmRepository = filmRepository;
        this.seansRepository=seansRepository;
        this.miejsceRepository=miejsceRepository;
        this.niedostepneMiejsceRepository = niedostepneMiejsceRepository;
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

    @PostMapping("/delete/{id}")
    @Transactional
    public String deleteFilm(@PathVariable("id") Long id) {
        seansRepository.findByFilmId(id).forEach(seans -> {
            miejsceRepository.findBySeansId(seans.getId()).forEach(miejsce -> {
                niedostepneMiejsceRepository.deleteByMiejsceId(miejsce.getId());
                miejsceRepository.deleteById(miejsce.getId());
            });
            seansRepository.deleteById(seans.getId());
        });
        filmRepository.deleteById(id);
        return "redirect:/films";
    }

}
