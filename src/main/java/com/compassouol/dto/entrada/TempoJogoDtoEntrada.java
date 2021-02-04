package com.compassouol.dto.entrada;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TempoJogoDtoEntrada {

	@NotNull
	private LocalDateTime dataInicial;
	@NotNull
	private LocalDateTime dataFinal;

}