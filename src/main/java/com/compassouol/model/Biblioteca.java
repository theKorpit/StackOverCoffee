package com.compassouol.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Biblioteca {

	private Collection<Jogo> jogos;

	public Biblioteca() {
		jogos = new ArrayList<Jogo>();
	}
	
	public void cadastraJogo() throws IOException { //Veio da main
		Scanner sc = new Scanner(System.in);
		int codigoJogo;
		double valorDeVenda;
		String dataLancamento;

		try {
			System.out.print("\nDigite o codigo do jogo: ");
			codigoJogo = sc.nextInt();
			
			if(buscaPorId(codigoJogo) != null) {
				System.out.println("\nJogo ja cadastrado!");
				return;
			}
		} catch (Exception e) {
			sc.next();
			System.out.println("\nOpção negada! Insira apenas números.\n");
			return;
		}

		System.out.print("\nDigite o nome do jogo: ");
		String nomeJogo = sc.next();

		System.out.print("\nDigite o nome do desenvolvedor: ");
		String desenvolvedor = sc.next();

		System.out.print("\nDigite o nome da distribuidora:");
		String distribuidora = sc.next();

		try {
			System.out.print("\nDigite a data do lancamento(DD/MM/YYYY): ");
			dataLancamento = sc.next();

			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dl = LocalDate.parse(dataLancamento, formato);

		} catch (Exception e) {
			System.out.println("\nData inválida! Digite no formato DD/MM/YYYY.\n");
			return;
		}

		System.out.print("\nDigite a categoria: ");
		String categoria = sc.next();

		try {
			System.out.println("\nDigite o valor: ");
			valorDeVenda = sc.nextDouble();

		} catch (Exception e) {
			sc.next();
			System.out.println("\nOpção negada! Insira apenas números e o separador decimar ','\n");
			return;
		}

		cadastraJogo(codigoJogo, nomeJogo, desenvolvedor, distribuidora, dataLancamento, categoria,
				valorDeVenda);

	}
	
	public void AcessaJogo() //Veio da main
	{
		Scanner sc = new Scanner(System.in);
		int idJogo;
		try {
			System.out.print("\nDigite o codigo do jogo: ");
			idJogo = sc.nextInt();
		} catch (Exception e) {
			sc.next();
			System.out.println("\nOpção negada! Insira apenas números.\n");
			return;
		}
		
		Jogo j = buscaPorId(idJogo);
		if( j.equals(null))
			System.out.println("\nJogo não encontrado!");
		else
		{
			System.out.println("\nJogo iniado!");
			LocalDateTime dataInicio = LocalDateTime.now();
			System.out.print("\nObrigado por jogar, volte mais vezes! ");
			sc.nextLine();
			LocalDateTime dataFim = LocalDateTime.now().plusHours(2);
			j.adicionaTempoJogo(dataInicio, dataFim);	
		}
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