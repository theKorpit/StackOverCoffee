package com.compassouol.model;

import java.util.Collection;
import java.util.LinkedList;

public class Biblioteca {

	private Collection<Jogo> jogos;
	
	public Biblioteca() {
		jogos = new LinkedList<Jogo>();
	}

	public Collection<Jogo> getJogos() {
		return jogos;
	}
	
}
