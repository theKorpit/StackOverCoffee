package com.compassouol.exceptions;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class JogosEmMesmoHorarioException extends RuntimeException{

	private static final long serialVersionUID = -372958309125941431L;
	
	private static String mensagemErro = "Jogos não podem ser jogados no mesmo horario";
	private LocalDateTime dataInicio;	
	private LocalDateTime dataFim;	
	private LocalDateTime dataInicioToAdd;
	private LocalDateTime dataFimToAdd;
	
    public JogosEmMesmoHorarioException(LocalDateTime dataInicio, LocalDateTime dataFim, LocalDateTime dataInicioToAdd, LocalDateTime dataFimToAdd){
        super(mensagemErro);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataInicioToAdd = dataInicioToAdd;
        this.dataFimToAdd = dataFimToAdd;
    }
}