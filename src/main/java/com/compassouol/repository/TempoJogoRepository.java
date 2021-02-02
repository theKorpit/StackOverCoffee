package com.compassouol.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.compassouol.model.TempoJogo;

@Repository
public interface TempoJogoRepository extends JpaRepository<TempoJogo, Integer>{
	@Transactional
	@Modifying
	@Query("DELETE FROM TempoJogo t WHERE t.jogo.appIdSteam = :id")
	public void deleteByJogo_appIdSteam(Integer id);
}
