package com.compassouol.exceptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LeitorDeDadosComTratamento {

	private String variavelInteira; // 1
	private String variavelData; // 2
	private String variavelDouble; // 3
	private String variavelString; // 4

	public String lacoLeitura(String pergunta, int tipoDeLeitura) {

		while (true) {

			System.out.print(pergunta);

			switch (tipoDeLeitura) {

			case 1:
				if (recebeInteger())
					return this.variavelInteira;
				break;
			case 2:
				if (recebeData())
					return this.variavelData;
				break;
			case 3:
				if (recebeDouble())
					return this.variavelDouble;
				break;
			case 4:
				if (recebeString())
					return this.variavelString;
				break;
			}
		}
	}

	private boolean recebeInteger() { /// Le Integer com tratamento
		Scanner sc = new Scanner(System.in);
		try {
			this.variavelInteira = Integer.toString(sc.nextInt());
		} catch (Exception erroInt) {
			sc.next();
			System.err.println("\nEntrada inválida! Insira apenas números.");
			return false;
		}
		return true;
	}

	private boolean recebeDouble() { /// Le Double com tratamento
		Scanner sc = new Scanner(System.in);
		try {
			double valor = sc.nextDouble();
			if(valor<=0)
				throw new Exception();
			this.variavelDouble = Double.toString(valor);
		} catch (Exception erroDouble) {
			System.err.println("\nEntrada inválida! Insira apenas números positivos e utilize o separador ','");
			return false;
		}
		return true;
	}

	private boolean recebeString() { /// Le String com tratamento
		Scanner sc = new Scanner(System.in);
		try {
			this.variavelString = sc.nextLine();
			if (this.variavelString.trim().isEmpty())
				throw new IllegalArgumentException();

		} catch (Exception erroString) {

			System.err.println("\nEntrada inválida! Insira um dado válido");
			return false;
		}
		return true;
	}
	
	private boolean recebeData() { /// Le Data com tratamento
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
			System.err.println("\nData inválida! Digite no formato DD/MM/YYYY.\n");
			return false;
		}
		return true;
	}
}
