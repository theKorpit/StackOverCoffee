package com.compassouol.exceptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LeitorDeDadosComTratamento {

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
		Scanner sc = new Scanner(System.in);
		try {
			this.variavelInteira = Integer.toString(sc.nextInt());
		} catch (Exception erroInt) {
			sc.next();
			System.err.println("\nEntrada inv�lida! Insira apenas n�meros.");
			return false;
		}
		return true;
	}

	private boolean recebeFloat() { /// Le Float com tratamento
		Scanner sc = new Scanner(System.in);
		try {
			this.variavelFloat = Float.toString(sc.nextFloat());
		} catch (Exception erroFloat) {
			sc.next();
			System.err.println("\nEntrada inv�lida! Insira apenas n�meros e utilize o separador ','");
			return false;
		}
		return true;
	}

	private boolean recebeDouble() { /// Le Double com tratamento
		Scanner sc = new Scanner(System.in);
		try {
			this.variavelDouble = Double.toString(sc.nextDouble());	
		} catch (Exception erroDouble) {
			sc.next();
			System.err.println("\nEntrada inv�lida! Insira apenas n�meros e utilize o separador ','");
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

			System.err.println("\nEntrada inv�lida! Insira um dado v�lido");
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

			System.err.println("\nData inv�lida! Digite no formato DD/MM/YYYY.\n");
			return false;
		}
		return true;
	}
}
