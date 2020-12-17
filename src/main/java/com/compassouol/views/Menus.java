package com.compassouol.views;

import java.io.IOException;

import com.compassouol.exceptions.LeitorDeDadosComTratamento;
import com.compassouol.model.Biblioteca;

public class Menus {
	public static LeitorDeDadosComTratamento leitor = new LeitorDeDadosComTratamento();
	public static Biblioteca biblioteDeJogos = new Biblioteca();

	public static void menuPrincipal() throws IOException {

		while (true) {

			System.out.print("\n\n=== BIBLIOTECA DE JOGOS ===");
			System.out.print("\n1 - Menu jogo" + "\n2 - Jogar" + "\n3 - Sair do programa");

			switch (Integer.parseInt(leitor.lacoLeitura("\nDigite a opção: ", 1))) {

			case 1:
				MenuJogo();
				break;
			case 2:
				biblioteDeJogos.AcessaJogo();
				break;
			case 3:
				System.out.print("== BIBLIOTECA ENCERRADA! ==\n\n");
				return;
			default:
				System.err.print("\nEntrada inválida! Insira uma opção válida.");
			}
		}
	}

	private static void MenuJogo() throws IOException { /// PRONTO

		while (true) {

			System.out.print("\n\n======= MENU DE JOGO =======");
			System.out.print("\n1 - Cadastrar jogo" + "\n2 - Pesquisar jogo" + "\n3 - Exibir todos os jogos"
					+ "\n4 - Menu anterior");

			switch (Integer.parseInt(leitor.lacoLeitura("\nDigite a opção: ", 1))) {

			case 1:
				biblioteDeJogos.cadastraJogo();
				break;
			case 2:
				biblioteDeJogos.pesquisaJogo(leitor.lacoLeitura("\nInforme o nome do jogo: ", 4));
				break;
			case 3:
				biblioteDeJogos.exibeJogos();
				break;
			case 4:
				System.out.print("== VOLTANDO A BIBLIOTECA! ==\n\n");
				return;
			default:
				System.err.print("\nEntrada inválida! Insira uma opçõe válida.");
			}
		}
	}
}