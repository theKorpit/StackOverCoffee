package com.compassouol.exceptions;

public class DataInicioMaiorQueDataFimException extends RuntimeException{

	private static final long serialVersionUID = -372958309125941431L;
	
    public DataInicioMaiorQueDataFimException(){
        super("Entrada invalida! A data inicial nao pode ser menor que a data final");
    }
}