package com.compassouol.model;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.compassouol.exceptions.DataInicioMaiorQueDataFimException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TempoJogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;

	@OneToOne
	private Jogo jogo;

	public TempoJogo(LocalDateTime dataInicio, LocalDateTime dataFim) {
		if(dataInicio.isAfter(dataFim))
			throw new DataInicioMaiorQueDataFimException();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public Float totalTempoJogado() {

		Duration dur = Duration.between(this.dataInicio, this.dataFim);

		Float horas = (float) dur.toMinutes() / 60;

		return horas;
	}

	@Override
	public String toString() {
		return "TempoJogo [Data de inicio de jogo = " + dataInicio + ", Data de fim de jogo = " + dataFim + "]";
	}
}