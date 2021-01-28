package com.compassouol.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.compassouol.model.TempoJogo;

@Component
public class TempoJogoDao {

	 public List<TempoJogo> findAll(){
		 JogoDao jogoDao = new JogoDao();
		 List<TempoJogo> tempoJogo = new ArrayList<>();
		 
		 jogoDao.findAll().forEach(e -> {
			 tempoJogo.addAll(e.getTempoJogado());
		 });
		 
		 return tempoJogo;
	 }	
}