package com.compassouol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compassouol.model.TempoJogo;

@Repository
public interface TempoJogoRepository extends JpaRepository<TempoJogo, Integer>{

}
