package com.compassouol.model;

import java.io.IOException;
import java.util.Scanner;

public class Menus {
	public static Scanner sc = new Scanner(System.in);
	public static int opcao;
	public static Biblioteca biblioteDeJogos = new Biblioteca();

	public static void menuPrincipal() throws IOException {

		while (true) {

			System.out.print("\n\n=== BIBLIOTECA DE JOGOS ===\n" + "1 - Menu jogo\n" + "2 - Jogar\n"
					+ "3 - Sair do programa\n" + "Digite a opção:");
			try {
				opcao = sc.nextInt();
			} catch (Exception e) {
				opcao = 0;
				sc.next();
			}

			switch (opcao) {

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
				System.out.print("\nOpção inválida! Insira uma entrada válida.\n");
			}
		}
	}

	private static void MenuJogo() throws IOException { /// PRONTO

		while (true) {

			System.out.print("\n\n======= MENU DE JOGO =======\n" + "1 - Cadastrar jogo\n" + "2 - Pesquisar jogo\n"
					+ "3 - Exibir todos os jogos\n" + "4 - Menu anterior\n" + "Digite a opção: ");
			try {
				opcao = sc.nextInt();
			} catch (Exception e) {
				opcao = 0;
				sc.next();
			}

			switch (opcao) {

			case 1:
				biblioteDeJogos.cadastraJogo();
				break;
			case 2:
				System.out.print("\nInforme o nome do jogo: ");
				String nomeJogo = sc.next();
				biblioteDeJogos.pesquisaJogo(nomeJogo);
				break;
			case 3:
				biblioteDeJogos.exibeJogos();
				break;
			case 4:
				System.out.print("== VOLTANDO A BIBLIOTECA! ==\n\n");
				return;
			default:
				System.out.print("\nOpção inválida! Insira uma das opções acima.\n");
			}
		}
	}
}
