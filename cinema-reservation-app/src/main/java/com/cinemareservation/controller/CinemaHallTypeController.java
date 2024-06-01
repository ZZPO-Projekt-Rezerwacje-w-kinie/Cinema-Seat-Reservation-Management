package com.cinemareservation.controller;

import com.cinemareservation.model.Film;
import com.cinemareservation.model.TypSali;
import com.cinemareservation.repository.CinemaHallTypeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CinemaHallTypeController {

    private final CinemaHallTypeRepository cinemaHallTypeRepository;

    @Autowired
    public CinemaHallTypeController(CinemaHallTypeRepository cinemaHallTypeRepository) {
        this.cinemaHallTypeRepository = cinemaHallTypeRepository;
    }

    @GetMapping("/cinemaHallTypes")
    public String showFilmList(Model model) {
        model.addAttribute("halltypes", cinemaHallTypeRepository.findAll());
        return "cinema_hall_types";
    }

    @GetMapping("/addCinemaHallTypeForm")
    public String showCinemaHallTypeForm(TypSali typSali) {
        return "add_cinema_hall_type";
    }

    @PostMapping("/addCinemaHallType")
    public String addFilm(@Valid TypSali typSali, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_cinema_hall_type";
        }
        cinemaHallTypeRepository.save(typSali);
        return "redirect:/cinemaHallTypes";
    }

}
