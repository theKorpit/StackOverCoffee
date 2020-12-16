package com.compassouol.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Biblioteca { // Antiga classe AGENDA - Steam possui biblioteca de jogos e nao agenda de jogos

	private Collection<Jogo> jogos;

	public Biblioteca() {
		jogos = new ArrayList<Jogo>();
	}

	public void cadastraJogo(int codigoJogo, String nomeJogo, String desenvolvedor, String distribuidora,
			String dataLancamento, String categoria, double valorDeVenda) throws IOException {

		try {
			Jogo j = new Jogo(codigoJogo, nomeJogo, desenvolvedor, distribuidora, dataLancamento, categoria,
					valorDeVenda);
			jogos.add(j);
			System.out.print("\nJogo incluido com sucesso na biblioteca!\n");

		} catch (IOException e) {
			throw new IOException("\nDesculpe, nï¿½o foi possï¿½vel incluir o jogo na biblioteca!\n");
		}
	}

	public void pesquisaJogo() {
		// implementar metodo que recebe uma lista para buscar um jogo e assim que
		// achar, chamar o metodo ToString do objeto. Caso nao encontre algume erro
		// tratar
	}

	public void exibeJogos() {
		if(jogos.isEmpty())
			System.out.println("\nEsta biblioteca não possui nenhum jogo!");
		else {
			for (Jogo j : jogos) {
				System.out.println("\n\n" + j);
			}
		}
		
	}
	
	public Jogo buscaPorId(int id) {
		for(Jogo j : jogos) {
			if(j.getCodigoJogo() == id)
				return j;
		}
		return null;
	}
}