package com.cinemareservation.repository;

import com.cinemareservation.model.Miejsca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaSeatRepository extends JpaRepository<Miejsca, Integer> {

    List<Miejsca> findAllBySeansId(int seansId);
}
