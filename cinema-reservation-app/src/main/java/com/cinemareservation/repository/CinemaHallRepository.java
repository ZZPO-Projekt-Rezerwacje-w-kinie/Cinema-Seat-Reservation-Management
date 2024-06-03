package com.cinemareservation.repository;

import com.cinemareservation.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CinemaHallRepository extends JpaRepository<Sala, Integer> {

}
