package com.compassouol.exceptions;

public class DataInicioMaiorQueDataFimException extends RuntimeException{

	private static final long serialVersionUID = -372958309125941431L;
	
	public DataInicioMaiorQueDataFimException(String msg){
        super(msg);
    }

    public DataInicioMaiorQueDataFimException(String msg, Throwable cause){
        super(msg, cause);
    }
}