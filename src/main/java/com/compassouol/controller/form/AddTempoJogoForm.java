package com.compassouol.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class AddTempoJogoForm {

	@NotNull
	private Integer idJogo;
	@NotNull 
	private LocalDateTime dataInicial;
	@NotNull 
	private LocalDateTime dataFinal;
}