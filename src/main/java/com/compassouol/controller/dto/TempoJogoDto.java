package com.compassouol.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.compassouol.model.TempoJogo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor 
public class TempoJogoDto {
	
	private Integer jogoId;

	private LocalDateTime DataInicial;
	
	private LocalDateTime DataFinal;
	
	public static Page<TempoJogoDto> converteListaParaDto(Page<TempoJogo> tempoJogoList, Integer JogoId){
		
		List<TempoJogoDto> tempoJogoDtos = new ArrayList<TempoJogoDto>();
		
		for(TempoJogo tempoJogo : tempoJogoList) {
			TempoJogoDto tempoJogoDto = new TempoJogoDto();
			tempoJogoDto.setJogoId(JogoId);
			tempoJogoDto.setDataInicial(tempoJogo.getDataInicio());
			tempoJogoDto.setDataFinal(tempoJogo.getDataFim());
			tempoJogoDtos.add(tempoJogoDto);
		}
		return new PageImpl<TempoJogoDto>(tempoJogoDtos);	
	}	
}