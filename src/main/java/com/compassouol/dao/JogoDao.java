package com.compassouol.dao;

import com.compassouol.model.Jogo;

public class JogoDao {
	
	
	public void update(Jogo jogo) {
		
		Jogo old = FindById(jogo.getCodigoJogo());
		
		BibliotecaDao.biblioteca.getJogos().remove(old);
		BibliotecaDao.biblioteca.getJogos().add(jogo);
		
	}
	
	public void save(Jogo jogo) {
		for(Jogo jogoSearch : BibliotecaDao.biblioteca.getJogos()) {
			if(jogoSearch.getCodigoJogo() == jogo.getCodigoJogo()) {
				return;
			}
		}
		BibliotecaDao.biblioteca.getJogos().add(jogo);
	}
	
	
	public Jogo FindById(Integer id) {
		for(Jogo jogo : BibliotecaDao.biblioteca.getJogos()) {
			if(jogo.getCodigoJogo().equals(id))
				return jogo;
		}
		return null;
	}
}
