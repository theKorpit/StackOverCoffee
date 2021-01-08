package com.compassouol.dao;

import com.compassouol.model.Jogo;

public class JogoDao {

	public void save(Jogo jogo) {
		for (Jogo jogoSearch : BibliotecaDao.biblioteca.getJogos()) {
			if (jogoSearch.getAppIdSteam() == jogo.getAppIdSteam()) {
				
				return;
			}
		}
		BibliotecaDao.biblioteca.getJogos().add(jogo);
	}

	public Jogo FindById(Integer id) {
		for (Jogo jogo : BibliotecaDao.biblioteca.getJogos()) {
			if (jogo.getAppIdSteam().equals(id))
				return jogo;
		}
		return null;
	}

	public Jogo FindByName(String nome) {
		for (Jogo jogo : BibliotecaDao.biblioteca.getJogos()) {
			if (jogo.getNomeJogo().equalsIgnoreCase(nome))
				return jogo;
		}
		return null;
	}

}
