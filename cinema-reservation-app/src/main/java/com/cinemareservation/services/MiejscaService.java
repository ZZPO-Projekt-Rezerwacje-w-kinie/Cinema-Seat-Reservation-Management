package com.cinemareservation.services;

import com.cinemareservation.exceptions.ResourceNotFoundException;
import com.cinemareservation.model.Miejsca;
import com.cinemareservation.repository.MiejscaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiejscaService {

    private final MiejscaRepository miejscaRepository;

    public MiejscaService(MiejscaRepository miejscaRepository) {
        this.miejscaRepository = miejscaRepository;
    }

    public List<Miejsca> findAll() {
        return miejscaRepository.findAll();
    }

    public Miejsca save(Miejsca miejsca) {
        return miejscaRepository.save(miejsca);
    }

    public Miejsca update(Integer id, Miejsca miejscaDetails) {
        Miejsca miejsca = miejscaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Miejsca not found for this id :: " + id));

        miejsca.setSeans(miejscaDetails.getSeans());
        miejsca.setRzad(miejscaDetails.getRzad());
        miejsca.setMiejsceWRzedzie(miejscaDetails.getMiejsceWRzedzie());

        return miejscaRepository.save(miejsca);
    }

    public void delete(Integer id) {
        Miejsca miejsca = miejscaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Miejsca not found for this id :: " + id));

        miejscaRepository.delete(miejsca);
    }
}

