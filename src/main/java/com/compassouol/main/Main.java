package com.compassouol.main;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import com.compassouol.model.Biblioteca;
import com.compassouol.model.Jogo;

public class Main {

	public static Scanner sc = new Scanner(System.in);
	public static int opcao;
	public static Biblioteca biblioteDeJogos = new Biblioteca();

	public static void main(String[] args) throws IOException {

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
				acessaMenuJogo();
				break;
			case 2:
				AcessaJogo();
				break;
			case 3:
				System.out.print("== BIBLIOTECA ENCERRADA! ==\n\n");
				return;
			default:
				System.out.print("\nOpção inválida! Insira uma entrada válida.\n");
			}
		}
	}

	private static void acessaMenuJogo() throws IOException {

		while (true) {

			System.out.print("\n\n======= MENU DE JOGO =======\n" + "1 - Cadastrar jogo\n" + "2 - Pesquisar jogo\n"
					+ "3 - Exibir todos os jogos\n" + "4 - Menu anterior\n" + "Digite a opção:");
			try {
				opcao = sc.nextInt();
			} catch (Exception e) {
				opcao = 0;
				sc.next();
			}

			switch (opcao) {

			case 1:
				cadastraJogo();
				break;
			case 2:
				//pesquisaJogo();
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

	private static void cadastraJogo() throws IOException {

		int codigoJogo;
		double valorDeVenda;
		String dataLancamento;

		try {
			System.out.print("\nDigite o codigo do jogo: ");
			codigoJogo = sc.nextInt();
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
			System.out.println("\nOpção negada! Insira apenas números e o separador decimar ','.\n");
			return;
		}

		biblioteDeJogos.cadastraJogo(codigoJogo, nomeJogo, desenvolvedor, distribuidora, dataLancamento, categoria,
				valorDeVenda);

	}

	private static void AcessaJogo()
	{
		
		
	}
}