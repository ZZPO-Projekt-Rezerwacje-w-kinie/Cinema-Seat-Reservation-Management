package com.cinemareservation.repository;

import com.cinemareservation.model.Miejsca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaSeatRepository extends JpaRepository<Miejsca, Integer> {

}
