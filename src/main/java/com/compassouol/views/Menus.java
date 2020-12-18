package com.compassouol.views;

import java.io.IOException;

import com.compassouol.services.BibliotecaService;
import com.compassouol.services.LeitorComTratamento;
import com.compassouol.services.LeitorComTratamento.TiposDeDados;

public class Menus {
	public static LeitorComTratamento leitor = new LeitorComTratamento();
	public static BibliotecaService biblioteDeJogos = new BibliotecaService();

	public static void menuPrincipal() throws IOException {

		while (true) {

			System.out.print("\n\n=== BIBLIOTECA DE JOGOS ===");
			System.out.print("\n1 - Menu jogo" + "\n2 - Jogar" + "\n3 - Sair do programa");

			switch (Integer.parseInt(leitor.lacoLeitura("\nDigite a opcao: ", TiposDeDados.Inteiro))) {

			case 1:
				menuJogo();
				break;
			case 2:
				if (!biblioteDeJogos.listaVazia())
					biblioteDeJogos.acessaJogo();
				break;
			case 3:
				System.out.print("== BIBLIOTECA ENCERRADA! ==\n\n");
				return;
			default:
				System.err.print("\nEntrada invalida! Insira uma opcao valida.");
			}
		}
	}

	private static void menuJogo() throws IOException { /// PRONTO

		while (true) {

			System.out.print("\n\n======= MENU DE JOGO =======");
			System.out.print("\n1 - Cadastrar jogo" + "\n2 - Alterar jogo" + "\n3 - Excluir jogo"
					+ "\n4 - Pesquisar jogo por nome" + "\n5 - Pesquisar jogo por categoria"
					+ "\n6 - Exibir todos os jogos" + "\n7 - Menu anterior");

			switch (Integer.parseInt(leitor.lacoLeitura("\nDigite a opcao: ", TiposDeDados.Inteiro))) {

			case 1:
				biblioteDeJogos.cadastraJogo();
				break;
			case 2:
				if (!biblioteDeJogos.listaVazia())
					biblioteDeJogos.alteraJogo(Integer.parseInt(leitor.lacoLeitura("Informe o ID do jogo: ", TiposDeDados.Inteiro)));
				break;
			case 3:
				if (!biblioteDeJogos.listaVazia())
					biblioteDeJogos.excluiJogo(Integer.parseInt(leitor.lacoLeitura("Informe o ID do jogo: ", TiposDeDados.Inteiro)));
				break;
			case 4:
				if (!biblioteDeJogos.listaVazia())
					biblioteDeJogos.pesquisaJogoNome(leitor.lacoLeitura("\nInforme o nome do jogo: ", TiposDeDados.String));
				break;
			case 5:
				if (!biblioteDeJogos.listaVazia())
					biblioteDeJogos.pesquisaJogoCategoria(leitor.lacoLeitura("\nInforme a categoria do jogo: ", TiposDeDados.String));
				break;
			case 6:
				if (!biblioteDeJogos.listaVazia())
					biblioteDeJogos.exibeJogos();
				break;
			case 7:
				System.out.print("== VOLTANDO A BIBLIOTECA! ==\n\n");
				return;
			default:
				System.err.print("\nEntrada invalida! Insira uma opcao valida.");
			}
		}
	}

	public static int menuAltera() {
		System.out.println("\n\n======= MENU DE ALTERACAO =======");
		System.out.println("\n1 - Nome \n2 - Desenvolvedor \n3 - Distribuidora"
				+ "\n4 - Data de lancamento \n5 - Categoria \n6 - Valor de venda \n7 - Menu anterior");

		return Integer.parseInt(leitor.lacoLeitura("Selecione o que deseja alterar: ", TiposDeDados.Inteiro));
	}
}
