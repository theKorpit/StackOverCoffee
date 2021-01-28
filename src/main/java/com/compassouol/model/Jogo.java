package com.compassouol.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import lombok.Getter;

@Getter
public class Jogo {

	private Integer appIdSteam;
	private String nomeJogo;
	private String desenvolvedor;
	private String distribuidora;
	private String dataLancamento;
	private String categoria;
	private Double valorDeVenda;
	private String descricao;
	private Collection<TempoJogo> tempoJogado;

	public Jogo(Integer appIdSteam, String nomeJogo, String desenvolvedor, String distribuidora, String dataLancamento,
			String categoria, Double valorDeVenda, String descricao) {

		this.appIdSteam = appIdSteam;
		this.nomeJogo = nomeJogo;
		this.desenvolvedor = desenvolvedor;
		this.distribuidora = distribuidora;
		this.dataLancamento = dataLancamento;
		this.categoria = categoria;
		this.valorDeVenda = valorDeVenda;
		this.descricao = descricao;
		this.tempoJogado = new HashSet<TempoJogo>();
	}

	public void adicionaTempoJogo(LocalDateTime dataInicio, LocalDateTime dataFim) {
		TempoJogo tempo = new TempoJogo(dataInicio, dataFim);
		tempoJogado.add(tempo);
	}

	public float tempoTotalJogado() {
		float tempo = 0.0f;
		for (TempoJogo tempojogo : tempoJogado) {
			tempo += tempojogo.totalTempoJogado();
		}
		return tempo;
	}
}