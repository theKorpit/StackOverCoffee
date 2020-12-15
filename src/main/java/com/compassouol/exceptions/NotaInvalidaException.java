package com.compassouol.exceptions;

public class NotaInvalidaException extends RuntimeException{

	private static final long serialVersionUID = 7345884737029481436L;

	public NotaInvalidaException(String msg){
        super(msg);
    }

    public NotaInvalidaException(String msg, Throwable cause){
        super(msg, cause);
    }
	
}
