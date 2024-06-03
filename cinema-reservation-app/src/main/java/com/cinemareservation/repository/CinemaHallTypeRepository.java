package com.cinemareservation.repository;

import com.cinemareservation.model.TypSali;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;

@Repository
public interface CinemaHallTypeRepository extends JpaRepository<TypSali, Integer> {

}
