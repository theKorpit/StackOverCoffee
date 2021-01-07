package com.compassouol.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

public class AddTempoJogoForm {

	@NotNull
	private Integer idJogo;
	@NotNull 
	private LocalDateTime dataInicial;
	@NotNull 
	private LocalDateTime dataFinal;

	public Integer getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(Integer idJogo) {
		this.idJogo = idJogo;
	}

	public LocalDateTime getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDateTime dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDateTime getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDateTime dataFinal) {
		this.dataFinal = dataFinal;
	}
	
}
