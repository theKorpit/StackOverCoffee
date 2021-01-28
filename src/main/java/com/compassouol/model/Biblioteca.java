package com.compassouol.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Biblioteca {

	private List<Jogo> jogos;
	
	public Biblioteca() {
		jogos = new ArrayList<>();
	}
}