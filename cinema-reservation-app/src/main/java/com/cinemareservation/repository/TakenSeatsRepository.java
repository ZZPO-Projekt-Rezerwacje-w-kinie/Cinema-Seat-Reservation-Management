package com.cinemareservation.repository;

import com.cinemareservation.model.NiedostepneMiejsca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TakenSeatsRepository extends JpaRepository<NiedostepneMiejsca, Integer> {
    List<NiedostepneMiejsca> findAllBySeansId(int seansId);
    void deleteByMiejsca_Id(Integer id);
}
