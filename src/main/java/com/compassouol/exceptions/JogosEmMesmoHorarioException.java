package com.compassouol.exceptions;

import java.time.LocalDateTime;

import lombok.Getter;
@Getter
public class JogosEmMesmoHorarioException extends RuntimeException{

	private static final long serialVersionUID = -372958309125941431L;
	
	private LocalDateTime dataInicio;
	
	private LocalDateTime dataFim;
	
	private LocalDateTime dataInicioToAdd;
	
	private LocalDateTime dataFimToAdd;
	
	public JogosEmMesmoHorarioException(String msg){
        super(msg);
    }

    public JogosEmMesmoHorarioException(String msg, Throwable cause){
        super(msg, cause);
    }
    
    public JogosEmMesmoHorarioException(String msg, LocalDateTime dataInicio, LocalDateTime dataFim, LocalDateTime dataInicioToAdd, LocalDateTime dataFimToAdd){
        super(msg);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataInicioToAdd = dataInicioToAdd;
        this.dataFimToAdd = dataFimToAdd;
    }

}
