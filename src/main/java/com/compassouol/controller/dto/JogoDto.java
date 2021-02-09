package com.compassouol.controller.dto;

import org.springframework.data.domain.Page;

import com.compassouol.model.Jogo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class JogoDto {

	private Integer appIdSteam;
	private String nomeJogo;
	private String desenvolvedor;
	private String distribuidora;
	private Float tempoJogado;

	public JogoDto(Jogo jogo) {
		this.appIdSteam = jogo.getAppIdSteam();
		this.nomeJogo = jogo.getNomeJogo();
		this.desenvolvedor = jogo.getDesenvolvedor();
		this.distribuidora = jogo.getDistribuidora();
		this.tempoJogado = jogo.tempoTotalJogado();
	}
	
	public Page<JogoDto> retornaListaJogos(Page<Jogo> jogos) {
		return jogos.map(JogoDto::new);
	}
}