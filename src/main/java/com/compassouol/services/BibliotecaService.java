package com.compassouol.services;

import java.time.LocalDateTime;

import com.compassouol.model.Biblioteca;
import com.compassouol.model.Jogo;
import com.compassouol.services.LeitorComTratamento.TiposDeDados;
import com.compassouol.views.Menus;

public class BibliotecaService {

	private Biblioteca biblioteca = new Biblioteca();
	private static LeitorComTratamento leitor = new LeitorComTratamento();

	public void cadastraJogo() {

		String nomeJogo = leitor.lacoLeitura("\nDigite o nome do jogo: ", TiposDeDados.String);

		String desenvolvedor = leitor.lacoLeitura("\nDigite o nome do desenvolvedor: ", TiposDeDados.String);

		String distribuidora = leitor.lacoLeitura("\nDigite o nome da distribuidora: ", TiposDeDados.String);

		String dataLancamento = leitor.lacoLeitura("\nDigite a data de lancamento do jogo(DD/MM/YYYY): ", TiposDeDados.Data);

		String categoria = leitor.lacoLeitura("\nDigite a categoria: ", TiposDeDados.String);

		double valorDeVenda = Double.parseDouble(leitor.lacoLeitura("\nDigite o valor: ", TiposDeDados.Double));

		adicionaJogoLista(nomeJogo, desenvolvedor, distribuidora, dataLancamento, categoria, valorDeVenda);
		
	}
	
	public void adicionaJogoLista(String nomeJogo, String desenvolvedor, String distribuidora, String dataLancamento,
			String categoria, double valorDeVenda) {

		Jogo j = new Jogo(nomeJogo, desenvolvedor, distribuidora, dataLancamento, categoria, valorDeVenda);
		biblioteca.getJogos().add(j);
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
			String nomeJogo = leitor.lacoLeitura("\nDigite o novo nome do jogo: ", TiposDeDados.String);
			jogo.setNomeJogo(nomeJogo);
			break;
		case 2:
			String desenvolvedor = leitor.lacoLeitura("\nDigite o novo nome do desenvolvedor: ", TiposDeDados.String);
			jogo.setDesenvolvedor(desenvolvedor);
			break;
		case 3:
			String distribuidora = leitor.lacoLeitura("\nDigite o novo nome da distribuidora: ", TiposDeDados.String);
			jogo.setDistribuidora(distribuidora);
			break;
		case 4:
			String dataLancamento = leitor.lacoLeitura("\nDigite a nova data de lancamento do jogo(DD/MM/YYYY): ", TiposDeDados.Data);
			jogo.setDataLancamento(dataLancamento);
			break;
		case 5:
			String categoria = leitor.lacoLeitura("\nDigite a nova categoria: ", TiposDeDados.String);
			jogo.setCategoria(categoria);
			break;
		case 6: 
			double valorDeVenda = Double.parseDouble(leitor.lacoLeitura("\nDigite o novo valor: ", TiposDeDados.Double));
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
			biblioteca.getJogos().remove(j);
			System.out.print("\nJogo deletado com sucesso!");
		}
	}

	public void acessaJogo() {

		int idJogo = Integer.parseInt(leitor.lacoLeitura("\nDigite o codigo do jogo: ", TiposDeDados.Inteiro));

		if (buscaPorId(idJogo) == null)
			System.err.println("\nJogo nao encontrado!");
		else {
			System.out.print("\nJogo iniado!");

			LocalDateTime dataInicio = LocalDateTime.now();
			LocalDateTime dataFim = LocalDateTime.now()
					.plusHours(Integer.parseInt(leitor.lacoLeitura("\nQuantas horas voce jogou: ", TiposDeDados.Inteiro)));

			System.out.print("\nJogo finalizado!");

			Jogo j = buscaPorId(idJogo);
			j.adicionaTempoJogo(dataInicio, dataFim);
		}
	}

	public void pesquisaJogoNome(String nomeJogo) {
		boolean achou = false;
		for (Jogo j : biblioteca.getJogos()) {
			if (j.getNomeJogo().trim().equalsIgnoreCase(nomeJogo.trim())) {
				System.out.println("\n\n" + j);
				achou = true;
			}
		}
		if (!achou)
			System.err.println("\nJogo nao encontrado!");
	}

	public void pesquisaJogoCategoria(String categoriaJogo) {
		boolean achou = false;
		for (Jogo j : biblioteca.getJogos()) {
			if (j.getCategoria().trim().equalsIgnoreCase(categoriaJogo.trim())) {
				System.out.println("\n\n" + j);
				achou = true;
			}
		}
		if (!achou)
			System.err.println("\nJogo nao encontrado!");
	}

	public void exibeJogos() {
		float totalSessoes = 0;
		for (Jogo j : biblioteca.getJogos()) {
			System.out.println("\n\n" + j);
			totalSessoes += j.tempoTotalJogado();
		}
		System.out.println("\nNesta biblioteca voce ja jogou: " + totalSessoes + " Horas");
	}

	public Jogo buscaPorId(int id) {
		for (Jogo j : biblioteca.getJogos()) {
			if (j.getCodigoJogo() == id)
				return j;
		}
		return null;
	}

	public boolean listaVazia() {
		if (biblioteca.getJogos().isEmpty()) {
			System.err.println("\nEsta biblioteca nao possui nenhum jogo!");
			return true;
		}
		return false;
	}
}