package com.compassouol.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import com.compassouol.exceptions.LeitorDeDadosComTratamento;

public class Biblioteca {

	private Collection<Jogo> jogos;

	public Biblioteca() {
		jogos = new ArrayList<Jogo>();
	}
	
	public void cadastraJogo() throws IOException {
		
		LeitorDeDadosComTratamento leitor = new LeitorDeDadosComTratamento();
		
		int codigoJogo = Integer.parseInt(leitor.lacoLeitura("\nDigite o codigo do jogo: ", 1));
		
		String nomeJogo = leitor.lacoLeitura("\nDigite o nome do jogo: ", 4);
		
		String desenvolvedor = leitor.lacoLeitura("\nDigite o nome do desenvolvedor: ", 4);
		
		String distribuidora = leitor.lacoLeitura("\nDigite o nome da distribuidora: ", 4);
		
		String dataLancamento = leitor.lacoLeitura("\nDigite a data de lancamento do jogo(DD/MM/YYYY): ", 4);
		
		String categoria = leitor.lacoLeitura("\nDigite a categoria: ", 4);
				
		double valorDeVenda = Double.parseDouble(leitor.lacoLeitura("\nDigite o valor: ", 3));

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
			System.out.println("\nOp��o negada! Insira apenas n�meros.\n");
			return;
		}
		
		Jogo j = buscaPorId(idJogo);
		if( j.equals(null))
			System.out.println("\nJogo n�o encontrado!");
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
			throw new IOException("\nDesculpe, n�o foi poss�vel incluir o jogo na biblioteca!\n");
		}
	}

	public void pesquisaJogo(String nomeJogo) {
		if (jogos.isEmpty())
			System.out.println("\nEsta biblioteca n�o possui nenhum jogo!");
		else{ 
			boolean achou = false;
			for (Jogo j : jogos) {
				if (j.getNomeJogo().equals(nomeJogo)) {
					System.out.println("\n\n" + j);
					achou = true;
				}	
			}
			if(!achou)
				System.out.println("\nJogo n�o encontrado!");
		}
	}

	public void exibeJogos() {
		
		float totalSessoes = 0; 
		if (jogos.isEmpty())
			System.out.println("\nEsta biblioteca n�o possui nenhum jogo!");
		else {
			for (Jogo j : jogos) {
				System.out.println("\n\n" + j);
				totalSessoes += j.tempoTotalJogado();
			}
		System.out.println("\nNesta biblioteca voc� ja jogou: " + totalSessoes + "Horas");
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