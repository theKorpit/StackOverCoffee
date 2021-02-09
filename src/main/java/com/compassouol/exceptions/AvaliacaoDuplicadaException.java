package com.compassouol.exceptions;

import lombok.Getter;

@Getter
public class AvaliacaoDuplicadaException extends RuntimeException {

	private static final long serialVersionUID = -9033618365393895919L;
	private static String mensagem  = "Jogo ja avaliado";
	
	public AvaliacaoDuplicadaException() {
		super(mensagem);
	}
}