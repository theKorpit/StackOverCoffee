package com.compassouol.exceptions;

public class EntradaInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static String mensagemErro = "Entrada invalida! Valor atribuido e maior que o permitido na variavel:";
	private String nomeJogo;
	
	
	public EntradaInvalidaException(String nomeJogo) {
		super(mensagemErro);
		this.nomeJogo = nomeJogo;
	}
}
