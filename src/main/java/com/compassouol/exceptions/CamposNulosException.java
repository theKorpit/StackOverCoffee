package com.compassouol.exceptions;

import lombok.Getter;

@Getter
public class CamposNulosException extends RuntimeException{

	private static final long serialVersionUID = -7451043874592209114L;
	private String campoErro;
	
	public CamposNulosException(String campo) {
		super("Entrada invalida! Algum campo obrigatório está em branco!");
		this.campoErro = campo;
	}
}