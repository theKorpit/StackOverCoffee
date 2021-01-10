package com.compassouol.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class AddTempoJogoForm {

	@NotNull
	private Integer idJogo;
	@NotNull 
	private LocalDateTime dataInicial;
	@NotNull 
	private LocalDateTime dataFinal;
}