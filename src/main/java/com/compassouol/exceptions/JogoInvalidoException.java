package com.compassouol.exceptions;

import lombok.Getter;

@Getter
public class JogoInvalidoException extends RuntimeException {

	private Integer id;
	private String nomeJogo;
	private static final long serialVersionUID = -372958309125941431L;

	public JogoInvalidoException(String msg) {
		super(msg);
	}

	public JogoInvalidoException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public JogoInvalidoException(String msg, int id) {
		super(msg);
		this.id = id;
		this.nomeJogo = null;
	}

	public JogoInvalidoException(String msg, String nomeJogo) {
		super(msg);
		this.nomeJogo = nomeJogo;
		this.id = null;
	}
}