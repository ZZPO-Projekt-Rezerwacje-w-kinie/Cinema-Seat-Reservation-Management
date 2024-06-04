package com.cinemareservation.controller;

import com.cinemareservation.model.Miejsce;
import com.cinemareservation.repository.MiejsceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rezerwacje")
public class RezerwacjaController {

    private final MiejsceRepository miejsceRepository;

    @Autowired
    public RezerwacjaController(MiejsceRepository miejsceRepository) {
        this.miejsceRepository = miejsceRepository;
    }

    @GetMapping("/seans/{seansId}")
    public String showSeansMiejsca(@PathVariable("seansId") Integer seansId, Model model) {
        List<Miejsce> miejsca = miejsceRepository.findBySeansId(seansId);
        model.addAttribute("miejsca", miejsca);
        model.addAttribute("seansId", seansId);
        return "seans_miejsca";
    }

    @PostMapping("/rezerwuj")
    public String rezerwujMiejsce(@RequestParam("miejsceId") Integer miejsceId, @RequestParam("seansId") Integer seansId, Model model) {
        Miejsce miejsce = miejsceRepository.findById(miejsceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid miejsce Id:" + miejsceId));
        if (miejsce.isZarezerwowane()) {
            model.addAttribute("error", "To miejsce jest już zarezerwowane.");
            List<Miejsce> miejsca = miejsceRepository.findBySeansId(seansId);
            model.addAttribute("miejsca", miejsca);
            model.addAttribute("seansId", seansId);
            return "seans_miejsca";
        }

        miejsce.setZarezerwowane(true);
        miejsceRepository.save(miejsce);

        return "redirect:/rezerwacje/seans/" + seansId;
    }
}
