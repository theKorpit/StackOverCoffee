package com.compassouol.dao;

import com.compassouol.model.Jogo;

public class JogoDao {
	
	
	public void save(Jogo jogo) {
		
		if(jogo.getCodigoJogo() == null) {
			BibliotecaDao.biblioteca.getJogos().add(jogo);
			return;
		}
		
		
		for(Jogo jogoSearch : BibliotecaDao.biblioteca.getJogos()) {
			if(jogoSearch.getCodigoJogo() == jogo.getCodigoJogo()) {
				BibliotecaDao.biblioteca.getJogos().remove(jogoSearch);
				BibliotecaDao.biblioteca.getJogos().add(jogo);
			}
		}
		
	}
	
	
	public Jogo FindById(Integer id) {
		for(Jogo jogo : BibliotecaDao.biblioteca.getJogos()) {
			if(jogo.getCodigoJogo().equals(id))
				return jogo;
		}
		return null;
	}
}
