package com.cinemareservation.services;

import com.cinemareservation.exceptions.ResourceNotFoundException;
import com.cinemareservation.model.Miejsca;
import com.cinemareservation.repository.MiejscaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RezerwacjaService {

    private final MiejscaRepository miejscaRepository;

    public RezerwacjaService(MiejscaRepository miejscaRepository) {
        this.miejscaRepository = miejscaRepository;
    }

    public List<Miejsca> findAll() {
        return miejscaRepository.findAll();
    }

    public Miejsca save(Miejsca miejsca) {
        return miejscaRepository.save(miejsca);
    }

    public void delete(Integer id) {
        Miejsca miejsca = miejscaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Miejsce not found for this id :: " + id));

        miejscaRepository.delete(miejsca);
    }
}
