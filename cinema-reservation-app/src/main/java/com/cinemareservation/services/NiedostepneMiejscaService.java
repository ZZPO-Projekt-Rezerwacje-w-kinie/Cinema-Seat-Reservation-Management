package com.cinemareservation.services;

import com.cinemareservation.exceptions.ResourceNotFoundException;
import com.cinemareservation.model.NiedostepneMiejsca;
import com.cinemareservation.repository.NiedostepneMiejscaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NiedostepneMiejscaService {

    private final NiedostepneMiejscaRepository niedostepneMiejscaRepository;

    public NiedostepneMiejscaService(NiedostepneMiejscaRepository niedostepneMiejscaRepository) {
        this.niedostepneMiejscaRepository = niedostepneMiejscaRepository;

    }

    public List<NiedostepneMiejsca> findAll() {
        return niedostepneMiejscaRepository.findAll();
    }

    public NiedostepneMiejsca save(NiedostepneMiejsca niedostepneMiejsca) {
        return niedostepneMiejscaRepository.save(niedostepneMiejsca);
    }

    public NiedostepneMiejsca update(Integer id, NiedostepneMiejsca niedostepneMiejscaDetails) {
        NiedostepneMiejsca niedostepneMiejsca = niedostepneMiejscaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NiedostepneMiejsca not found for this id :: " + id));

        niedostepneMiejsca.setSeans(niedostepneMiejscaDetails.getSeans());
        niedostepneMiejsca.setMiejsce(niedostepneMiejscaDetails.getMiejsce());

        return niedostepneMiejscaRepository.save(niedostepneMiejsca);
    }

    public void delete(Integer id) {
        NiedostepneMiejsca niedostepneMiejsca = niedostepneMiejscaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NiedostepneMiejsca not found for this id :: " + id));

        niedostepneMiejscaRepository.delete(niedostepneMiejsca);
    }
}
