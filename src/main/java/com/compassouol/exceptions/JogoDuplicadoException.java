package com.compassouol.exceptions;

import lombok.Getter;
@Getter
public class JogoDuplicadoException extends RuntimeException {
	
	private static final long serialVersionUID = -7451043874592209114L;
	private Integer id;
	private String nomeJogo;

	public JogoDuplicadoException(String msg) {
		super(msg);
	}

	public JogoDuplicadoException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public JogoDuplicadoException(String msg, int id) {
		super(msg);
		this.id = id;
		this.nomeJogo = null;
	}

	public JogoDuplicadoException(String msg, String nomeJogo) {
		super(msg);
		this.nomeJogo = nomeJogo;
		this.id = null;
	}
}
