package com.cinemareservation.services;

import com.cinemareservation.exceptions.ResourceNotFoundException;
import com.cinemareservation.model.Sala;
import com.cinemareservation.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public Sala save(Sala sala) {
        return salaRepository.save(sala);
    }

    public Sala update(Integer id, Sala salaDetails) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala not found for this id :: " + id));

        sala.setAdresBudynku(salaDetails.getAdresBudynku());
        sala.setIlMiejsc(salaDetails.getIlMiejsc());
        sala.setIlRzedow(salaDetails.getIlRzedow());
        sala.setIlMiejscWRzedzie(salaDetails.getIlMiejscWRzedzie());
        sala.setTypSali(salaDetails.getTypSali());

        return salaRepository.save(sala);
    }

    public void delete(Integer id) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala not found for this id :: " + id));

        salaRepository.delete(sala);
    }
}
