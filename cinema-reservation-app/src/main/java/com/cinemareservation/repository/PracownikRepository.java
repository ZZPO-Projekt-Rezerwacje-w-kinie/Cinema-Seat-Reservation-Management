package com.cinemareservation.repository;

import com.cinemareservation.model.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracownikRepository extends JpaRepository<Pracownik, Integer> {
    Pracownik findByEmail(String email);
}
