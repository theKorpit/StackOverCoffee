package com.compassouol.dao;

import java.util.List;

import com.compassouol.model.Jogo;

public class JogoDao {

	public void save(Jogo jogo) {
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
	
	public List<Jogo> findAll(){
		return BibliotecaDao.biblioteca.getJogos();
	}

}
