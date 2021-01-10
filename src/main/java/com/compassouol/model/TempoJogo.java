package com.compassouol.model;

import java.time.Duration;
import java.time.LocalDateTime;

import com.compassouol.exceptions.DataInicioMaiorQueDataFimException;

import lombok.Getter;

@Getter
public class TempoJogo {

	private LocalDateTime dataInicio;
	
	private LocalDateTime dataFim;
	
	public TempoJogo(LocalDateTime dataInicio, LocalDateTime dataFim) {
		if(dataInicio.isAfter(dataFim))
			throw new DataInicioMaiorQueDataFimException("A data inicial nao pode ser menor que a data final");
		
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public Float totalTempoJogado() {
		
		Duration dur = Duration.between(this.dataInicio, this.dataFim);
		
		Float horas = (float) dur.toMinutes()/60;
		
		return horas;
	}

	@Override
	public String toString() {
		return "TempoJogo [Data de inicio de jogo = " + dataInicio + ", Data de fim de jogo = " + dataFim + "]";
	}
}