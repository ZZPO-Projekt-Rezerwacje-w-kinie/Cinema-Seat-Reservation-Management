package com.cinemareservation.controller;

import com.cinemareservation.model.Sala;
import com.cinemareservation.model.TypSali;
import com.cinemareservation.repository.CinemaHallTypeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cinemareservation.repository.CinemaHallRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CinemaHallController  {

    private final CinemaHallRepository cinemaHallRepository;
    private final CinemaHallTypeRepository cinemaHallTypeRepository;

    @Autowired
    public CinemaHallController(CinemaHallRepository cinemaHallRepository, CinemaHallTypeRepository cinemaHallTypeRepository) {
        this.cinemaHallRepository = cinemaHallRepository;
        this.cinemaHallTypeRepository = cinemaHallTypeRepository;
    }

    @GetMapping("/cinemaHalls")
    public String showFilmList(Model model) {
        model.addAttribute("halls", cinemaHallRepository.findAll());
        return "cinema_halls";
    }

    @GetMapping("/addCinemaHallForm")
    public String showCinemaHallForm(Model model) {
        List<TypSali> typySali = cinemaHallTypeRepository.findAll();
        model.addAttribute("typySal", typySali);
        model.addAttribute("Sala", new Sala());
        return "add_cinema_hall";
    }

    @PostMapping("/addCinemaHall")
    public String addFilm(@Valid Sala sala, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_cinema_hall";
        }
        cinemaHallRepository.save(sala);
        return "redirect:/cinemaHalls";
    }

}
