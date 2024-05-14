package com.cinemareservation.services;

import com.cinemareservation.exceptions.ResourceNotFoundException;
import com.cinemareservation.model.Seans;
import com.cinemareservation.repository.SeansRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeansService {

    private final SeansRepository seansRepository;

    public SeansService(SeansRepository seansRepository) {
        this.seansRepository = seansRepository;
    }

    public List<Seans> findAll() {
        return seansRepository.findAll();
    }

    public Seans save(Seans seans) {
        return seansRepository.save(seans);
    }

    public Seans update(Integer id, Seans seansDetails) {
        Seans seans = seansRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seans not found for this id :: " + id));

        seans.setFilm(seansDetails.getFilm());
        seans.setDataCzas(seansDetails.getDataCzas());
        seans.setSala(seansDetails.getSala());

        return seansRepository.save(seans);
    }

    public void delete(Integer id) {
        Seans seans = seansRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seans not found for this id :: " + id));

        seansRepository.delete(seans);
    }
}
