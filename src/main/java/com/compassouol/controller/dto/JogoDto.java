package com.compassouol.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.compassouol.model.Jogo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
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
	
	public List<JogoDto> retornaListaJogos(List<Jogo> jogos) {

		List<JogoDto> listaJogoDtoSaida = new ArrayList<JogoDto>();

		for (Jogo jogo : jogos) {
			JogoDto jogoDtoSaida = new JogoDto(jogo);
			listaJogoDtoSaida.add(jogoDtoSaida);
		}
		return listaJogoDtoSaida;
	}
}