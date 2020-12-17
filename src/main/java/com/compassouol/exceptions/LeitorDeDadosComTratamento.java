package com.compassouol.exceptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LeitorDeDadosComTratamento {

	private static Scanner sc = new Scanner(System.in);

	private String variavelInteira; // 1
	private String variavelFloat; // 2
	private String variavelDouble; // 3
	private String variavelString; // 4
	private String variavelData; //5

	public String lacoLeitura(String pergunta, int tipoDeLeitura) {

		while (true) {

			System.out.print(pergunta);

			switch (tipoDeLeitura) {

			case 1:
				if (recebeInteger())
					return this.variavelInteira;
				break;
			case 2:
				if (recebeFloat())
					return this.variavelFloat;
				break;
			case 3:
				if (recebeDouble())
					return this.variavelDouble;
				break;
			case 4:
				if (recebeString())
					return this.variavelString;
				break;
			case 5:
				if (recebeData())
					return this.variavelData;
				break;
				
			}
		}
	}

	private boolean recebeInteger() { /// Le Integer com tratamento
		try {
			this.variavelInteira = Integer.toString(sc.nextInt());
			sc.next();
		} catch (Exception erroInt) {
			sc.next();
			System.err.println("\nEntrada inválida! Insira apenas números.");
			return false;
		}
		return true;
	}

	private boolean recebeFloat() { /// Le Float com tratamento
		try {
			this.variavelFloat = Float.toString(sc.nextFloat());
			sc.next();
		} catch (Exception erroFloat) {
			sc.next();
			System.err.println("\nEntrada inválida! Insira apenas números e utilize o separador ','");
			return false;
		}
		return true;
	}

	private boolean recebeDouble() { /// Le Double com tratamento
		try {
			this.variavelDouble = Double.toString(sc.nextDouble());
			sc.next();
		} catch (Exception erroDouble) {
			sc.next();
			System.err.println("\nEntrada inválida! Insira apenas números e utilize o separador ','");
			return false;
		}
		return true;
	}

	private boolean recebeString() { /// Le String com tratamento
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
		try {
			this.variavelData = sc.nextLine();
			if (this.variavelString.trim().isEmpty())
				throw new IllegalArgumentException();
			else {
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dl = LocalDate.parse(variavelData, formato);
			}	
		} catch (Exception erroString) {

			System.err.println("\nData inválida! Digite no formato DD/MM/YYYY.\n");
			return false;
		}
		return true;
	}
}
