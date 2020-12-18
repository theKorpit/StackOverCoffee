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

			switch (Integer.parseInt(leitor.lacoLeitura("\nDigite a op��o: ", 1))) {

			case 1:
				MenuJogo();
				break;
			case 2:
				biblioteDeJogos.acessaJogo();
				break;
			case 3:
				System.out.print("== BIBLIOTECA ENCERRADA! ==\n\n");
				return;
			default:
				System.err.print("\nEntrada inv�lida! Insira uma op��o v�lida.");
			}
		}
	}

	private static void MenuJogo() throws IOException { /// PRONTO

		while (true) {

			System.out.print("\n\n======= MENU DE JOGO =======");
			System.out.print("\n1 - Cadastrar jogo" + "\n2 - Excluir jogo" + "\n3 - Pesquisar jogo por nome"
					+ "\n4 - Pesquisar jogo por categoria" + "\n5 - Exibir todos os jogos" + "\n6 - Menu anterior");
			switch (Integer.parseInt(leitor.lacoLeitura("\nDigite a op��o: ", 1))) {

			case 1:
				biblioteDeJogos.cadastraJogo();
				break;
			case 2:
				biblioteDeJogos.excluiJogo(Integer.parseInt(leitor.lacoLeitura("Informe o ID do jogo: ", 1)));
				break;
			case 3:
				biblioteDeJogos.pesquisaJogoNome(leitor.lacoLeitura("\nInforme o nome do jogo: ", 4));
				break;
			case 4:
				biblioteDeJogos.pesquisaJogoCategoria(leitor.lacoLeitura("\nInforme a categoria do jogo: ", 4));
				break;
			case 5:
				biblioteDeJogos.exibeJogos();
				break;
			case 6:
				System.out.print("== VOLTANDO A BIBLIOTECA! ==\n\n");
				return;
			default:
				System.err.print("\nEntrada inv�lida! Insira uma op��o v�lida.");
			}
		}
	}
}