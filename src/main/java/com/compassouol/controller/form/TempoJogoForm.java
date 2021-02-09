package com.compassouol.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.compassouol.exceptions.DataInicioMaiorQueDataFimException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TempoJogoForm {

	@NotNull
	private LocalDateTime dataInicial;
	@NotNull
	private LocalDateTime dataFinal;

	public void aplicaValidacoes() {
		if (this.dataInicial.isAfter(this.dataFinal))
			throw new DataInicioMaiorQueDataFimException();
	}
}