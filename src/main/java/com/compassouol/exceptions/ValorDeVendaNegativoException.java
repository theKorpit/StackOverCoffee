package com.compassouol.exceptions;

public class ValorDeVendaNegativoException extends RuntimeException{

	private static final long serialVersionUID = 7130906901556585371L;

	public ValorDeVendaNegativoException(String msg){
        super(msg);
    }

    public ValorDeVendaNegativoException(String msg, Throwable cause){
        super(msg, cause);
    }	
}