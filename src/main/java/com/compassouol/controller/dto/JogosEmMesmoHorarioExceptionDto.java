package com.compassouol.controller.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class JogosEmMesmoHorarioExceptionDto {

	private LocalDateTime dataInicio;
	
	private LocalDateTime dataFim;
	
	private LocalDateTime dataInicioToAdd;
	
	private LocalDateTime dataFimToAdd;
	
	private String mensagem;
}