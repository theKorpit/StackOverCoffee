package com.compassouol.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import com.compassouol.model.TempoJogo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor 
public class TempoJogoDto { ///Precisa dos setter? Nao da para usar apenas this.Variavel?
	
	private Integer jogoId;

	private LocalDateTime DataInicial;
	
	private LocalDateTime DataFinal;
	
	public static Collection<TempoJogoDto> converteListaParaDto(Collection<TempoJogo> tempoJogoList, Integer JogoId){
		
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
}