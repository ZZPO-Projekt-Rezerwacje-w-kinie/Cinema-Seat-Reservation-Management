package com.cinemareservation.services;

import com.cinemareservation.exceptions.ResourceNotFoundException;
import com.cinemareservation.model.Pracownik;
import com.cinemareservation.repository.PracownikRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PracownikService {

    private final PracownikRepository pracownikRepository;

    public PracownikService(PracownikRepository pracownikRepository) {
        this.pracownikRepository = pracownikRepository;
    }

    public List<Pracownik> findAll() {
        return pracownikRepository.findAll();
    }

    public Pracownik save(Pracownik pracownik) {
        return pracownikRepository.save(pracownik);
    }

    public Pracownik update(Integer id, Pracownik pracownikDetails) {
        Pracownik pracownik = pracownikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pracownik not found for this id :: " + id));

        pracownik.setImie(pracownikDetails.getImie());
        pracownik.setHaslo(pracownikDetails.getHaslo());
        pracownik.setDataZatrudnienia(pracownikDetails.getDataZatrudnienia());
        pracownik.setEmail(pracownikDetails.getEmail());

        return pracownikRepository.save(pracownik);
    }

    public void delete(Integer id) {
        Pracownik pracownik = pracownikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pracownik not found for this id :: " + id));

        pracownikRepository.delete(pracownik);
    }
}
