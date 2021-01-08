package com.compassouol.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Biblioteca {

	private List<Jogo> jogos;
	
	public Biblioteca() {
		jogos = Collections.synchronizedList(new ArrayList());
	}

	public List<Jogo> getJogos() {
		return jogos;
	}
	
}
