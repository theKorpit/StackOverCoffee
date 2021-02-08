package com.compassouol.exceptions;

import lombok.Getter;

@Getter
public class EntradaInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static String mensagemErro = "Entrada invalida!";
	private String campoErro;
	
	
	public EntradaInvalidaException(String campoErro) {
		super(mensagemErro);
		this.campoErro = campoErro;
	}
}
