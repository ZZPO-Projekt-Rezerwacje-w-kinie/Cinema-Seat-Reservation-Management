package com.cinemareservation.repository;

import com.cinemareservation.model.NiedostepneMiejsca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TakenSeatsRepository extends JpaRepository<NiedostepneMiejsca, Integer> {
    void deleteByMiejsca_Id(Integer id);
}
