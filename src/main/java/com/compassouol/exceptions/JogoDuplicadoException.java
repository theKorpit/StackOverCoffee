package com.compassouol.exceptions;

import lombok.Getter;
@Getter
public class JogoDuplicadoException extends RuntimeException {
	
	private static final long serialVersionUID = -7451043874592209114L;
	private static String msg = "Jogo já adicionado"; 
	private Integer id;
	private String nomeJogo;

	public JogoDuplicadoException(Integer id) {
		super(msg);
		this.id = id;
		this.nomeJogo = null;
	}

	public JogoDuplicadoException(String nomeJogo) {
		super(msg);
		this.nomeJogo = nomeJogo;
		this.id = null;
	}
}