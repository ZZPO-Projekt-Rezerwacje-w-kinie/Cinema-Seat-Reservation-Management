package com.cinemareservation.controller;

import com.cinemareservation.model.Sala;
import com.cinemareservation.model.Seans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cinemareservation.repository.SeansRepository;
import com.cinemareservation.repository.CinemaSeatRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CinemaSeatController {

    private final SeansRepository seansRepository;
    private final CinemaSeatRepository cinemaSeatRepository;

    @Autowired
    public CinemaSeatController(SeansRepository seansRepository, CinemaSeatRepository cinemaSeatRepository) {
        this.seansRepository = seansRepository;
        this.cinemaSeatRepository = cinemaSeatRepository;
    }

    @GetMapping("/reservationForm")
    public String showReservationForm(Model model) {
        List<Seans> seanse = seansRepository.findAll();
        model.addAttribute("seanse", seanse);
        return "seat_reservation_form";
    }

    @PostMapping("/reservationSeat")
    public String reservationSeat(@RequestParam("seansId") int seansId, Model model) {
        Seans seans = seansRepository.findById(seansId).get();
        Sala sala = seans.getSala();
        int rowsNumber = sala.getRowsNumber();
        int seatInRow = sala.getColumnsNumber();
        model.addAttribute("rowsNumber", rowsNumber);
        model.addAttribute("seatInRow", seatInRow);
        return "cinema_hall";
    }


}
