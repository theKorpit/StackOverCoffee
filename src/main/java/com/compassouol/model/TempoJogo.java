package com.compassouol.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class TempoJogo {

	private LocalDateTime dataInicio;
	
	private LocalDateTime dataFim;
	
	public TempoJogo(LocalDateTime dataInicio, LocalDateTime dataFim) {
		if(dataInicio.isAfter(dataFim))
			throw new RuntimeException("A data de inicio não pode ser depois da data final");
		
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}
	
	public Float totalTempoJogado() {
		
		Duration dur = Duration.between(this.dataInicio, this.dataFim);
		
		Float horas = (float) dur.toMinutes()/60;
		
		return horas;
	}

	@Override
	public String toString() {
		return "TempoJogo [dataInicio=" + dataInicio + ", dataFim=" + dataFim + "]";
	}
}
