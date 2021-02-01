package com.compassouol.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.compassouol.model.Jogo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JogoDto {

	private Integer appIdSteam;
	private String nomeJogo;
	private String desenvolvedor;
	private String distribuidora;
	private Float tempoJogado;
	
	public List<JogoDto> converteJogoParaJogoDto(List<Jogo> jogos){
		
		List<JogoDto> jogoDtos = new ArrayList<JogoDto>();
		
		for(Jogo jogo : jogos) {
			JogoDto jogoDto = new JogoDto();
			jogoDto.setAppIdSteam(jogo.getAppIdSteam());
			jogoDto.setNomeJogo(jogo.getNomeJogo());
			jogoDto.setDesenvolvedor(jogo.getDesenvolvedor());
			jogoDto.setDistribuidora(jogo.getDistribuidora());
			jogoDto.setTempoJogado(jogo.tempoTotalJogado());
			jogoDtos.add(jogoDto);
		}
		return jogoDtos;	
		
		
		
	}
	
}
