package com.compassouol.services;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

import com.compassouol.model.Jogo;
import com.compassouol.views.Menus;

public class Biblioteca {

	private Collection<Jogo> jogos;
	private static LeitorComTratamento leitor = new LeitorComTratamento();

	public Biblioteca() {
		jogos = new LinkedList<Jogo>();
	}

	public void cadastraJogo() {

		String nomeJogo = leitor.lacoLeitura("\nDigite o nome do jogo: ", leitor.tipo.String);

		String desenvolvedor = leitor.lacoLeitura("\nDigite o nome do desenvolvedor: ", leitor.tipo.String);

		String distribuidora = leitor.lacoLeitura("\nDigite o nome da distribuidora: ", leitor.tipo.String);

		String dataLancamento = leitor.lacoLeitura("\nDigite a data de lancamento do jogo(DD/MM/YYYY): ", leitor.tipo.Data);

		String categoria = leitor.lacoLeitura("\nDigite a categoria: ", leitor.tipo.String);

		double valorDeVenda = Double.parseDouble(leitor.lacoLeitura("\nDigite o valor: ", leitor.tipo.Double));

		adicionaJogoLista(nomeJogo, desenvolvedor, distribuidora, dataLancamento, categoria, valorDeVenda);
		
	}
	
	public void adicionaJogoLista(String nomeJogo, String desenvolvedor, String distribuidora, String dataLancamento,
			String categoria, double valorDeVenda) {

		Jogo j = new Jogo(nomeJogo, desenvolvedor, distribuidora, dataLancamento, categoria, valorDeVenda);
		jogos.add(j);
		System.out.print("\nJogo incluido com sucesso na biblioteca!\n");
	}
	
	public void alteraJogo(int idJogo) {
		
		Jogo jogo = buscaPorId(idJogo);
		
		if(jogo == null) {
			System.err.print("\nJogo nao encontrado");
			return;
		}
		
		switch (Menus.menuAltera()) {
		case 1:
			String nomeJogo = leitor.lacoLeitura("\nDigite o novo nome do jogo: ", leitor.tipo.String);
			jogo.setNomeJogo(nomeJogo);
			break;
		case 2:
			String desenvolvedor = leitor.lacoLeitura("\nDigite o novo nome do desenvolvedor: ", leitor.tipo.String);
			jogo.setDesenvolvedor(desenvolvedor);
			break;
		case 3:
			String distribuidora = leitor.lacoLeitura("\nDigite o novo nome da distribuidora: ", leitor.tipo.String);
			jogo.setDistribuidora(distribuidora);
			break;
		case 4:
			String dataLancamento = leitor.lacoLeitura("\nDigite a nova data de lancamento do jogo(DD/MM/YYYY): ", leitor.tipo.Data);
			jogo.setDataLancamento(dataLancamento);
			break;
		case 5:
			String categoria = leitor.lacoLeitura("\nDigite a nova categoria: ", leitor.tipo.String);
			jogo.setCategoria(categoria);
			break;
		case 6: 
			double valorDeVenda = Double.parseDouble(leitor.lacoLeitura("\nDigite o novo valor: ", leitor.tipo.Double));
			jogo.setValorDeVenda(valorDeVenda);
			break;
		case 7:
			System.out.print("==== VOLTANDO A MENU DE JOGO! ====\n\n");
			return;
		default:
			System.err.print("\nEntrada invalida! Insira uma opcao valida.");
			return;
		}
		
		System.out.print("\nJogo alterado com sucesso");
	}

	public void excluiJogo(int idJogo) {
		Jogo j = buscaPorId(idJogo);

		if (j == null)
			System.err.print("\nJogo nao encontrado");

		else {
			jogos.remove(j);
			System.out.print("\nJogo deletado com sucesso!");
		}
	}

	public void acessaJogo() {

		int idJogo = Integer.parseInt(leitor.lacoLeitura("\nDigite o codigo do jogo: ", leitor.tipo.Inteiro));

		if (buscaPorId(idJogo) == null)
			System.err.println("\nJogo nao encontrado!");
		else {
			System.out.print("\nJogo iniado!");

			LocalDateTime dataInicio = LocalDateTime.now();
			LocalDateTime dataFim = LocalDateTime.now()
					.plusHours(Integer.parseInt(leitor.lacoLeitura("\nQuantas horas voce jogou: ", leitor.tipo.Inteiro)));

			System.out.print("\nJogo finalizado!");

			Jogo j = buscaPorId(idJogo);
			j.adicionaTempoJogo(dataInicio, dataFim);
		}
	}

	public void pesquisaJogoNome(String nomeJogo) {
		boolean achou = false;
		for (Jogo j : jogos) {
			if (j.getNomeJogo().equals(nomeJogo)) {
				System.out.println("\n\n" + j);
				achou = true;
			}
		}
		if (!achou)
			System.err.println("\nJogo nao encontrado!");
	}

	public void pesquisaJogoCategoria(String categoriaJogo) {
		boolean achou = false;
		for (Jogo j : jogos) {
			if (j.getCategoria().equals(categoriaJogo)) {
				System.out.println("\n\n" + j);
				achou = true;
			}
		}
		if (!achou)
			System.err.println("\nJogo nao encontrado!");
	}

	public void exibeJogos() {
		float totalSessoes = 0;
		for (Jogo j : jogos) {
			System.out.println("\n\n" + j);
			totalSessoes += j.tempoTotalJogado();
		}
		System.out.println("\nNesta biblioteca voce ja jogou: " + totalSessoes + " Horas");
	}

	public Jogo buscaPorId(int id) {
		for (Jogo j : jogos) {
			if (j.getCodigoJogo() == id)
				return j;
		}
		return null;
	}

	public boolean listaVazia() {
		if (jogos.isEmpty()) {
			System.err.println("\nEsta biblioteca nao possui nenhum jogo!");
			return true;
		}
		return false;
	}
}