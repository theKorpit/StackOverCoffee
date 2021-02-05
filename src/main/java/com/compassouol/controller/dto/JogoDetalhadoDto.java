package com.compassouol.controller.dto;

import org.springframework.stereotype.Component;

import com.compassouol.model.Jogo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Getter
@NoArgsConstructor
public class JogoDetalhadoDto {
	private Integer appIdSteam;
	private String nomeJogo;
	private String desenvolvedor;
	private String distribuidora;
	private String dataLancamento;
	private String categoria;
	private Double valorDeVenda;
	private String descricao;
	private Float tempoJogado;

	public JogoDetalhadoDto(Jogo jogo) {
		this.appIdSteam = jogo.getAppIdSteam();
		this.nomeJogo = jogo.getNomeJogo();
		this.desenvolvedor = jogo.getDesenvolvedor();
		this.distribuidora = jogo.getDistribuidora();
		this.dataLancamento = jogo.getDataLancamento();
		this.categoria = jogo.getCategoria();
		this.valorDeVenda = jogo.getValorDeVenda();
		this.descricao = jogo.getDescricao();
		this.tempoJogado = jogo.tempoTotalJogado();
	}
}