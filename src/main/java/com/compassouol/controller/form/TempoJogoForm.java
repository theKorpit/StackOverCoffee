package com.compassouol.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TempoJogoForm {

	@NotNull
	private LocalDateTime dataInicial;
	@NotNull
	private LocalDateTime dataFinal;

}