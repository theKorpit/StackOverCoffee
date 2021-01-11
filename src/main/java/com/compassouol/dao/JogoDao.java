package com.compassouol.dao;

import java.util.List;

import com.compassouol.model.Jogo;

public class JogoDao {

	public void salva(Jogo jogo) {
		BibliotecaDao.biblioteca.getJogos().add(jogo);
	}

	public Jogo buscaPorId(Integer id) {
		for (Jogo jogo : BibliotecaDao.biblioteca.getJogos()) {
			if (jogo.getAppIdSteam().equals(id))
				return jogo;
		}
		return null;
	}

	public Jogo buscaPorNome(String nome) { 
		for (Jogo jogo : BibliotecaDao.biblioteca.getJogos()) {
			if (jogo.getNomeJogo().equalsIgnoreCase(nome))
				return jogo;
		}
		return null;
	}
	
	public List<Jogo> findAll(){ //Sobreescreveu metodo?
		return BibliotecaDao.biblioteca.getJogos();
	}
}