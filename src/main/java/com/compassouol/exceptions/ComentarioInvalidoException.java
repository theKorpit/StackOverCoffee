package com.compassouol.exceptions;

public class ComentarioInvalidoException extends RuntimeException{

	private static final long serialVersionUID = -3172700132374005561L;

	public ComentarioInvalidoException(String msg){
        super(msg);
    }

    public ComentarioInvalidoException(String msg, Throwable cause){
        super(msg, cause);
    }
	
	
}
