package com.compassouol.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import com.compassouol.model.TempoJogo;

public class TempoJogoDto {
	
	private Integer jogoId;

	private LocalDateTime DataInicial;
	
	private LocalDateTime DataFinal;

	public TempoJogoDto(Integer jogoId, LocalDateTime dataInicial, LocalDateTime dataFinal) {
		this.jogoId = jogoId;
		this.DataInicial = dataInicial;
		this.DataFinal = dataFinal;
	}

	public TempoJogoDto() {
	}

	public LocalDateTime getDataInicial() {
		return DataInicial;
	}

	public void setDataInicial(LocalDateTime dataInicial) {
		DataInicial = dataInicial;
	}

	public LocalDateTime getDataFinal() {
		return DataFinal;
	}

	public void setDataFinal(LocalDateTime dataFinal) {
		DataFinal = dataFinal;
	}
	
	public static Collection<TempoJogoDto> convertListToDto(Collection<TempoJogo> tempoJogoList, Integer JogoId){
		
		Collection<TempoJogoDto> tempoJogoDtos = new ArrayList<TempoJogoDto>();
		
		for(TempoJogo tempoJogo : tempoJogoList) {
			TempoJogoDto tempoJogoDto = new TempoJogoDto();
			tempoJogoDto.setJogoId(JogoId);
			tempoJogoDto.setDataInicial(tempoJogo.getDataInicio());
			tempoJogoDto.setDataFinal(tempoJogo.getDataFim());
			tempoJogoDtos.add(tempoJogoDto);
		}
		
		return tempoJogoDtos;
		
	}

	public Integer getJogoId() {
		return jogoId;
	}

	public void setJogoId(Integer jogoId) {
		this.jogoId = jogoId;
	}
	
}
