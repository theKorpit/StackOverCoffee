package com.compassouol.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.compassouol.model.Jogo;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer>{

	public Jogo findByNomeJogo(String nome);
	
	@Query("SELECT t FROM Jogo t WHERE t.categoria like %:cat%")
	public Page<Jogo> findByCategoria(String cat, Pageable paginacao);
	
	public Jogo findByAppIdSteam(Integer id);
	
	
}