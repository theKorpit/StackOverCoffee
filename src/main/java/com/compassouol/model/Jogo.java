package com.compassouol.model;

import java.time.LocalDateTime;
import java.util.Collection;

public class Jogo {
	private int codigoJogo;
	private String nomeJogo;
	private String desenvolvedor;
	private String distribuidora;
	private int appIdSteam;
	private LocalDateTime dataLancamento;//REVER QUAL BIBLIOTECA
	private Enum categoria;
	private double valorDeVenda;
	private Collection<TempoJogo> tempoJogado;
	private Collection<Avaliacao> avaliacaoJogo;
	
	
	public Jogo(int codigoJogo, String nomeJogo, String desenvolvedor, String distribuidora,
			LocalDateTime dataLancamento, Enum categoria, double valorDeVenda) {
		super();
		this.codigoJogo = codigoJogo;
		this.nomeJogo = nomeJogo;
		this.desenvolvedor = desenvolvedor;
		this.distribuidora = distribuidora;
		this.dataLancamento = dataLancamento;
		this.categoria = categoria;
		this.valorDeVenda = valorDeVenda;
	}
	
	
	public int getCodigoJogo() {
		return codigoJogo;
	}
	public String getNomeJogo() {
		return nomeJogo;
	}
	public String getDesenvolvedor() {
		return desenvolvedor;
	}
	public String getDistribuidora() {
		return distribuidora;
	}
	public int getAppIdSteam() {
		return appIdSteam;
	}
	public LocalDateTime getDataLancamento() {
		return dataLancamento;
	}
	public Enum getCategoria() {
		return categoria;
	}
	public double getValorDeVenda() {
		return valorDeVenda;
	}
	public Collection<TempoJogo> getTempoJogado() {
		return tempoJogado;
	}
	public Collection<Avaliacao> getAvaliacaoJogo() {
		return avaliacaoJogo;
	}
	
	
	
	
}
