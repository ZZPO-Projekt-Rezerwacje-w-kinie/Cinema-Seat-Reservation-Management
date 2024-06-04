package com.cinemareservation.controller;

import com.cinemareservation.model.Seans;
import com.cinemareservation.repository.SeansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SeansController {

    private final SeansRepository seansRepository;

    @Autowired
    public SeansController(SeansRepository seansRepository) {
        this.seansRepository = seansRepository;
    }

    @GetMapping("/seanse/{seansId}")
    public String showSeansMiejsca(@PathVariable("seansId") Integer seansId, Model model) {
        Seans seans = seansRepository.findById(seansId).orElseThrow(() -> new IllegalArgumentException("Invalid seans Id:" + seansId));
        model.addAttribute("seans", seans);
        return "seans_miejsca";
    }
}
