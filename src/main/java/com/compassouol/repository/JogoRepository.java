package com.compassouol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.compassouol.model.Jogo;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer>{

	public Jogo findByNomeJogo(String nome);
	
	@Query("SELECT t FROM Jogo t WHERE t.categoria like %:cat%")
	public List<Jogo> findByCategoria(String cat);
}