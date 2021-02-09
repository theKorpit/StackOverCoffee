package com.compassouol.controller.dto;

import com.compassouol.model.Jogo;

import lombok.Getter;
import lombok.NoArgsConstructor;

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
	private String comentario;
	private String nota;

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
		if(jogo.getAvaliacao() != null) {
			this.comentario = jogo.getAvaliacao().getComentario() != null ? jogo.getAvaliacao().getComentario() : ""; 
			this.nota = jogo.getAvaliacao().getNota() != null ? jogo.getAvaliacao().getNota().toString() : "";
		}else {
			this.comentario = "";
			this.nota = "";
		}	
	}
}