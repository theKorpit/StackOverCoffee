package com.compassouol.exceptions;

public class JogoInvalidoException extends RuntimeException{

	private static final long serialVersionUID = -372958309125941431L;
	
	public JogoInvalidoException(String msg){
        super(msg);
    }

    public JogoInvalidoException(String msg, Throwable cause){
        super(msg, cause);
    }

}

