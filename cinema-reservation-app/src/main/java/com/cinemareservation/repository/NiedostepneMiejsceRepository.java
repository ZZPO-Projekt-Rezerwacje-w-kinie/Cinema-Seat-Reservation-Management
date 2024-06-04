package com.cinemareservation.repository;

import com.cinemareservation.model.NiedostepneMiejsca;
import com.cinemareservation.model.NiedostepneMiejsca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NiedostepneMiejsceRepository extends JpaRepository<NiedostepneMiejsca, Integer> {
    void deleteByMiejsceId(Integer miejsceId);
    List<NiedostepneMiejsca> findByMiejsceId(Integer miejsceId);
}