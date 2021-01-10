package com.compassouol.dao;

import java.util.ArrayList;
import java.util.List;

import com.compassouol.model.TempoJogo;

public class TempoJogoDao {

	 public List<TempoJogo> findAll(){ //Sobreescreveu metodo?
		 JogoDao jogoDao = new JogoDao();
		 List<TempoJogo> tempoJogo = new ArrayList<>();
		 
		 jogoDao.findAll().forEach(e -> {
			 tempoJogo.addAll(e.getTempoJogado());
		 });
		 
		 return tempoJogo;
	 }	
}