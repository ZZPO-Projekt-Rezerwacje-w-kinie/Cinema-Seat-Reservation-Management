package com.cinemareservation.controller;

import com.cinemareservation.model.Miejsca;
import com.cinemareservation.model.NiedostepneMiejsca;
import com.cinemareservation.model.Sala;
import com.cinemareservation.model.Seans;
import com.cinemareservation.repository.TakenSeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cinemareservation.repository.SeansRepository;
import com.cinemareservation.repository.CinemaSeatRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CinemaSeatController {

    private final SeansRepository seansRepository;
    private final CinemaSeatRepository cinemaSeatRepository;
    private final TakenSeatsRepository takenSeatsRepository;

    @Autowired
    public CinemaSeatController(SeansRepository seansRepository, CinemaSeatRepository cinemaSeatRepository, TakenSeatsRepository takenSeatsRepository) {
        this.seansRepository = seansRepository;
        this.cinemaSeatRepository = cinemaSeatRepository;
        this.takenSeatsRepository = takenSeatsRepository;
    }

    @GetMapping("/reservationForm")
    public String showReservationForm(Model model) {
        List<Seans> seanse = seansRepository.findAll();
        model.addAttribute("seanse", seanse);
        return "seat_reservation_form";
    }


    @PostMapping("/seatReservationForm")
    public String showReservationForm(@RequestParam("seansId") int seansId, Model model) {
        Seans seans = seansRepository.findById(seansId).get();

        Sala sala = seans.getSala();
        int rowsNumber = sala.getRowsNumber();
        int seatInRow = sala.getColumnsNumber();
        model.addAttribute("rowsNumber", rowsNumber);
        model.addAttribute("seatInRow", seatInRow);
        List<Integer> miejsca = cinemaSeatRepository.findAllBySeansId(seansId).stream()
                        .map(Miejsca::getId)
                        .collect(Collectors.toList());
        List<Miejsca> miejscaObj = cinemaSeatRepository.findAllBySeansId(seansId);
        model.addAttribute("miejscaObj", miejscaObj);
        model.addAttribute("miejsca", miejsca);
        List<Integer> takenSeats = takenSeatsRepository.findAllBySeansId(seansId).stream()
                .map(NiedostepneMiejsca::getId)
                .collect(Collectors.toList());
        System.out.println(takenSeats);
        model.addAttribute("takenSeats", takenSeats);
        model.addAttribute("seansId", seansId);
        System.out.println(takenSeats.size());

        return "cinema_hall";
    }

    @PostMapping("/submitReservation")
    public String submitReservation(@RequestParam("seansId") int seansId, @RequestParam(value = "selectedSeats", required = false) List<Integer> selectedSeats) {
        System.out.println("test");
        if(selectedSeats != null) {
            for (Integer seatId : selectedSeats) {
                Miejsca miejsca_reservation  = cinemaSeatRepository.getReferenceById(seatId);
                Seans seans_reservation = seansRepository.getReferenceById(seansId);
                NiedostepneMiejsca takenSeat = new NiedostepneMiejsca();
                takenSeat.setMiejsca(miejsca_reservation);
                takenSeat.setSeansu(seans_reservation);
                takenSeatsRepository.save(takenSeat);
                System.out.println(seatId);
            }
        }
        return "redirect:/reservationForm?seansId=" + seansId;
    }


}
