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

	public void pesquisaJogo(String nomeJogo) {
		if (jogos.isEmpty())
			System.out.println("\nEsta biblioteca não possui nenhum jogo!");
		else{ 
			boolean achou = false;
			for (Jogo j : jogos) {
				if (j.getNomeJogo().equals(nomeJogo)) {
					System.out.println("\n\n" + j);
					achou = true;
				}	
			}
			if(!achou)
				System.out.println("\nJogo não encontrado!");
		}
	}

	public void exibeJogos() {
		
		float totalSessoes = 0; 
		if (jogos.isEmpty())
			System.out.println("\nEsta biblioteca não possui nenhum jogo!");
		else {
			for (Jogo j : jogos) {
				System.out.println("\n\n" + j);
				totalSessoes += j.tempoTotalJogado();
			}
		System.out.println("\nNesta biblioteca você ja jogou: " + totalSessoes + "Horas");
		}
	}

	public Jogo buscaPorId(int id) {
		for (Jogo j : jogos) {
			if (j.getCodigoJogo() == id)
				return j;
		}

		return null;
	}
}