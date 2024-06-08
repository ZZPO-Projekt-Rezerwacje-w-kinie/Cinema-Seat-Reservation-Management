package com.cinemareservation.repository;

import com.cinemareservation.model.NiedostepneMiejsca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TakenSeatsRepository extends JpaRepository<NiedostepneMiejsca, Integer> {
    List<NiedostepneMiejsca> findAllBySeansId(int seansId);
}
