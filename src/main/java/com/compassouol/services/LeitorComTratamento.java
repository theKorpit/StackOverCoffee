package com.compassouol.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LeitorComTratamento {

	public enum TiposDeDados {

		Inteiro(1), Data(2), Double(3), String(4);
		
		private final int valor;
		
	    private TiposDeDados(int v) {
	        this.valor = v;
	    }
	}
	
	public TiposDeDados tipo;
	private String variavelInteira; // 1
	private String variavelData; // 2
	private String variavelDouble; // 3
	private String variavelString; // 4
	

	public String lacoLeitura(String pergunta, TiposDeDados tipo) {

		while (true) {

			System.out.print(pergunta);

			switch (tipo) {

			case Inteiro:
				if (recebeInteiro())
					return this.variavelInteira;
				break;
			case Data:
				if (recebeData())
					return this.variavelData;
				break;
			case Double:
				if (recebeDouble())
					return this.variavelDouble;
				break;
			case String:
				if (recebeString())
					return this.variavelString;
				break;
			}
		}
	}
 
	
	 
	
	private boolean recebeInteiro() { /// Le int com tratamento
		Scanner sc = new Scanner(System.in);
		try {
			int valor = sc.nextInt();
			if (valor <= 0)
				throw new Exception();
			this.variavelInteira = Integer.toString(valor);
		} catch (Exception erroInt) {
			System.err.println("\nEntrada invalida! Insira apenas numeros.");
			return false;
		}
		return true;
	}

	private boolean recebeDouble() { /// Le double com tratamento
		Scanner sc = new Scanner(System.in);
		try {
			double valor = sc.nextDouble();
			if (valor <= 0)
				throw new Exception();
			this.variavelDouble = Double.toString(valor);
		} catch (Exception erroDouble) {
			System.err.print("\nEntrada invalida! Insira apenas numeros positivos e utilize o separador ','");
			return false;
		}
		return true;
	}

	private boolean recebeString() { /// Le string com tratamento
		Scanner sc = new Scanner(System.in);
		try {
			this.variavelString = sc.nextLine();
			if (this.variavelString.trim().isEmpty())
				throw new IllegalArgumentException();

		} catch (Exception erroString) {

			System.err.print("\nEntrada invalida! Insira um dado valido");
			return false;
		}
		return true;
	}

	private boolean recebeData() { /// Le data com tratamento
		Scanner sc = new Scanner(System.in);
		try {
			this.variavelData = sc.nextLine();
			if (this.variavelData.trim().isBlank())
				throw new IllegalArgumentException();
			else {
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate.parse(variavelData, formato);
			}
		} catch (Exception erroString) {
			System.err.print("\nData invalida! Digite no formato DD/MM/YYYY.\n");
			return false;
		}
		return true;
	}
}