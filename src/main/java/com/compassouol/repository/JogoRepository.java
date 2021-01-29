package com.compassouol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compassouol.model.Jogo;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer>{

}