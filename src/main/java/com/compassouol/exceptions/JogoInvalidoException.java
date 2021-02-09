package com.compassouol.exceptions;

import lombok.Getter;

@Getter
public class JogoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = -372958309125941431L;

	private static String mensagemErro = "Nao foi localizado nenhum jogo com os dados informados";

	private Integer idSteam;
	private String nomeJogo;

	public JogoInvalidoException(Integer idSteam) {
		super(mensagemErro);
		this.idSteam = idSteam;
	}

	public JogoInvalidoException(String nomeJogo) {
		super(mensagemErro);
		this.nomeJogo = nomeJogo;
	}
}