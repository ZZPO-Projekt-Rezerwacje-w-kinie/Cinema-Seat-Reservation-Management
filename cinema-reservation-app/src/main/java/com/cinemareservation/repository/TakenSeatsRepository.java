package com.cinemareservation.repository;

import com.cinemareservation.model.NiedostepneMiejsca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakenSeatsRepository extends JpaRepository<NiedostepneMiejsca, Integer> {
}
