package com.cinemareservation;


import com.cinemareservation.model.Miejsca;
import com.cinemareservation.model.Seans;
import com.cinemareservation.model.Sala;
import com.cinemareservation.repository.CinemaSeatRepository;
import com.cinemareservation.repository.SeansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WelcomeController {

    @GetMapping("/index")
    public String welcome(){
        return "index";
    }
}