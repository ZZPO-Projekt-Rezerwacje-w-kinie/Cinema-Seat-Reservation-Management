package com.cinemareservation.repository;

import com.cinemareservation.model.Miejsce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiejsceRepository extends JpaRepository<Miejsce, Integer> {
    List<Miejsce> findBySeansId(Integer seansId);
    void deleteBySeansId(Integer seansId);
}
