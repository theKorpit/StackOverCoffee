package com.compassouol.dto.saida;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.compassouol.model.Jogo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Getter @NoArgsConstructor
public class JogoDtoSaida {

	private Integer appIdSteam;
	private String nomeJogo;
	private String desenvolvedor;
	private String distribuidora;
	private Float tempoJogado;

	public JogoDtoSaida(Jogo jogo) {
		this.appIdSteam = jogo.getAppIdSteam();
		this.nomeJogo = jogo.getNomeJogo();
		this.desenvolvedor = jogo.getDesenvolvedor();
		this.distribuidora = jogo.getDistribuidora();
		this.tempoJogado = jogo.tempoTotalJogado();
	}
	
	public List<JogoDtoSaida> retornaListaJogos(Page<Jogo> jogos) {

		List<JogoDtoSaida> listaJogoDtoSaida = new ArrayList<JogoDtoSaida>();

		for (Jogo jogo : jogos) {
			JogoDtoSaida jogoDtoSaida = new JogoDtoSaida(jogo);
			listaJogoDtoSaida.add(jogoDtoSaida);
		}
		return listaJogoDtoSaida;
	}
	
	public List<JogoDtoSaida> retornaListaJogos(List<Jogo> jogos) {

		List<JogoDtoSaida> listaJogoDtoSaida = new ArrayList<JogoDtoSaida>();

		for (Jogo jogo : jogos) {
			JogoDtoSaida jogoDtoSaida = new JogoDtoSaida(jogo);
			listaJogoDtoSaida.add(jogoDtoSaida);
		}
		return listaJogoDtoSaida;
	}
}