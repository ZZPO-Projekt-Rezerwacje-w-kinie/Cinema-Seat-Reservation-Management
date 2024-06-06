package com.cinemareservation.controller;

import com.cinemareservation.model.Film;
import com.cinemareservation.model.Miejsca;
import com.cinemareservation.model.Sala;
import com.cinemareservation.model.Seans;
import com.cinemareservation.repository.*;
import jakarta.validation.Valid;
import org.hibernate.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CinemaScreeningController {

    private final SeansRepository seansRepository;
    private final FilmRepository filmRepository;
    private final CinemaHallRepository cinemaHallRepository;
    private final CinemaSeatRepository ciemaSeatRepository;

    @Autowired
    public CinemaScreeningController(SeansRepository seansRepository, FilmRepository filmRepository, CinemaHallRepository cinemaHallRepository, CinemaSeatRepository cinemaSeatRepository) {
        this.seansRepository = seansRepository;
        this.filmRepository = filmRepository;
        this.cinemaHallRepository = cinemaHallRepository;
        this.ciemaSeatRepository = cinemaSeatRepository;
    }

    @GetMapping("/seans")
    public String showSeansList(Model model) {
        List<Seans> seanse = seansRepository.findAll();
        if (!seanse.isEmpty()) {
            Seans pierwszySeans = seanse.get(0);
            Film pierwszyFilm = pierwszySeans.getFilm();
            System.out.println(pierwszyFilm.getTytul());
        }
        System.out.println(seanse.size());
        model.addAttribute("seans", seansRepository.findAll());
        return "seans";
    }

    @GetMapping("/addSeansForm")
    public String showSeansForm(Model model) {
        List<Film> films = filmRepository.findAll();
        List<Sala> sale = cinemaHallRepository.findAll();
        model.addAttribute("films", films);
        model.addAttribute("sale", sale);
        model.addAttribute("Seans", new Seans());
        return "add_seans";
    }

    @PostMapping("/addSeans")
    public String addSeans(@Valid Seans seans, BindingResult result, Model model) {
        System.out.println("test 1");
        if (result.hasErrors()) {
            return "add_seans";
        }
        System.out.println("test 2");
        seansRepository.save(seans);
        Sala sala = seans.getSala();
        int rowsNumber = sala.getRowsNumber();
        Integer columnsNumber = sala.getColumnsNumber();
        System.out.println(rowsNumber+"liczba miejsc w rzedzie");
        System.out.println(columnsNumber+"liczba miejsc w kolumie");
        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columnsNumber; j++) {
                ciemaSeatRepository.save(new Miejsca(seans, i, j));
            }
        }
        return "redirect:/seans";
    }
    @GetMapping("/editSeansForm/{id}")
    public String showEditSeansForm(@PathVariable("id") Integer id, Model model) {
        Seans seans = seansRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid seans Id:" + id));
        List<Film> films = filmRepository.findAll();
        List<Sala> sale = cinemaHallRepository.findAll();
        model.addAttribute("films", films);
        model.addAttribute("sale", sale);
        model.addAttribute("seans", seans);
        return "edit_seans";
    }

    @PostMapping("/updateSeans/{id}")
    public String updateSeans(@PathVariable("id") Integer id, @Valid Seans seans, BindingResult result, Model model) {
        if (result.hasErrors()) {
            seans.setId(id);
            return "edit_seans";
        }

        Seans existingSeans = seansRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid seans Id:" + id));
        existingSeans.setFilm(seans.getFilm());
        existingSeans.setSala(seans.getSala());
        existingSeans.setDataCzas(seans.getDataCzas());


        if (existingSeans.getMiejsca() == null) {
            existingSeans.setMiejsca(new ArrayList<>());
        }

        existingSeans.getMiejsca().clear();
        if (seans.getMiejsca() != null) {
            existingSeans.getMiejsca().addAll(seans.getMiejsca());
        }

        seansRepository.save(existingSeans);
        return "redirect:/seans";
    }

    @GetMapping("/deleteSeans/{id}")
    public String deleteSeans(@PathVariable("id") Integer id, Model model) {
        Seans seans = seansRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid seans Id:" + id));
        seansRepository.delete(seans);
        return "redirect:/seans";
    }
}
