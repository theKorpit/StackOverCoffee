package com.compassouol.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.compassouol.model.Avaliacao;
@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer>{
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Avaliacao a WHERE a.jogo.appIdSteam = :id")
	public void deleteByJogo_appIdSteam(Integer id);

}