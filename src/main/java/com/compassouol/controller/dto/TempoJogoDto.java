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
	
	public  Page<TempoJogoDto> retornaListaTempoJogo(Page<TempoJogo> listaTempoJogo, Integer jogoId){
		
		List<TempoJogoDto> listaTempoJogoDto = new ArrayList<TempoJogoDto>();
		for(TempoJogo tempoJogo : listaTempoJogo) 
			listaTempoJogoDto.add(new TempoJogoDto(jogoId, tempoJogo.getDataInicio(), tempoJogo.getDataFim()));
			
		return new PageImpl<TempoJogoDto>(listaTempoJogoDto);	
	}	
}