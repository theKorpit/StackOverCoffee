package com.compassouol.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

import com.compassouol.exceptions.LeitorDeDadosComTratamento;

public class Biblioteca {

	private Collection<Jogo> jogos;

	public Biblioteca() {
		jogos = new LinkedList<Jogo>();
	}

	public void cadastraJogo() {

		LeitorDeDadosComTratamento leitor = new LeitorDeDadosComTratamento();

		String nomeJogo = leitor.lacoLeitura("\nDigite o nome do jogo: ", 4);

		String desenvolvedor = leitor.lacoLeitura("\nDigite o nome do desenvolvedor: ", 4);

		String distribuidora = leitor.lacoLeitura("\nDigite o nome da distribuidora: ", 4);

		String dataLancamento = leitor.lacoLeitura("\nDigite a data de lancamento do jogo(DD/MM/YYYY): ", 2);

		String categoria = leitor.lacoLeitura("\nDigite a categoria: ", 4);

		double valorDeVenda = Double.parseDouble(leitor.lacoLeitura("\nDigite o valor: ", 3));

		adicionaJogoLista(nomeJogo, desenvolvedor, distribuidora, dataLancamento, categoria, valorDeVenda);
	}

	public void adicionaJogoLista(String nomeJogo, String desenvolvedor, String distribuidora, String dataLancamento,
			String categoria, double valorDeVenda) {

		Jogo j = new Jogo(nomeJogo, desenvolvedor, distribuidora, dataLancamento, categoria, valorDeVenda);
		jogos.add(j);
		System.out.print("\nJogo incluido com sucesso na biblioteca!\n");
	}


	public void excluiJogo(int idJogo) {
		Jogo j = buscaPorId(idJogo);

		if (j == null)
			System.out.print("\nJogo nao encontrado");

		else {
			jogos.remove(j);
			System.out.print("\nJogo deletado com sucesso!");
		}
	}

	public void acessaJogo() {

		LeitorDeDadosComTratamento leitor = new LeitorDeDadosComTratamento();

		int idJogo = Integer.parseInt(leitor.lacoLeitura("\nDigite o codigo do jogo: ", 1));

		if (buscaPorId(idJogo) == null)
			System.out.println("\nJogo nao encontrado!");
		else {
			System.out.print("\nJogo iniado!");

			LocalDateTime dataInicio = LocalDateTime.now();
			LocalDateTime dataFim = LocalDateTime.now()
					.plusHours(Integer.parseInt(leitor.lacoLeitura("\nQuantas horas voce jogou: ", 1)));

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
			System.out.println("\nJogo nao encontrado!");
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
			System.out.println("\nJogo nao encontrado!");
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
			System.out.println("\nEsta biblioteca nao possui nenhum jogo!");
			return true;
		}
		return false;
	}
}
