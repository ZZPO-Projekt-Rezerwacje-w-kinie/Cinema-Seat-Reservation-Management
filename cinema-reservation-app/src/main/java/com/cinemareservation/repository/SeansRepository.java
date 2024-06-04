package com.cinemareservation.repository;

import com.cinemareservation.model.Seans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SeansRepository extends JpaRepository<Seans, Integer> {

    void deleteByFilmId(Long id);

    List<Seans> findByFilmId(Long id);
}