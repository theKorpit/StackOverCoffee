package com.compassouol.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import com.compassouol.exceptions.ValorDeVendaNegativoException;

public class Jogo {
	
	private static int counter=0;
	
	private int codigoJogo;
	private String nomeJogo;
	private String desenvolvedor;
	private String distribuidora;
	private int appIdSteam;
	private String dataLancamento;
	private String categoria;
	private double valorDeVenda;
	private Collection<TempoJogo> tempoJogado;

	public Jogo(String nomeJogo, String desenvolvedor, String distribuidora, String dataLancamento,
			String categoria, double valorDeVenda){
		this.counter+=1;
		this.codigoJogo = this.counter;
		this.nomeJogo = nomeJogo;
		this.desenvolvedor = desenvolvedor;
		this.distribuidora = distribuidora;
		this.dataLancamento = dataLancamento;
		this.categoria = categoria;
		this.tempoJogado = new HashSet<TempoJogo>();
		validaValorVenda(valorDeVenda);
	}

	public int getCodigoJogo() {
		return codigoJogo;
	}

	public String getNomeJogo() {
		return nomeJogo;
	}

	public void setNomeJogo(String nomeJogo) {
		this.nomeJogo = nomeJogo;
	}

	public void setDesenvolvedor(String desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}

	public void setDistribuidora(String distribuidora) {
		this.distribuidora = distribuidora;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setValorDeVenda(double valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
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

	public String getDataLancamento() {
		return this.dataLancamento;
	}

	public String getCategoria() {
		return categoria;
	}

	public double getValorDeVenda() {
		return valorDeVenda;
	}

	public Collection<TempoJogo> getTempoJogado() {
		return tempoJogado;
	}

	public void adicionaTempoJogo(LocalDateTime dataInicio, LocalDateTime dataFim) {
		TempoJogo tempo = new TempoJogo(dataInicio, dataFim);
		tempoJogado.add(tempo);
	}

	private void validaValorVenda(double valor){
		if (valor < 0) {
			throw new ValorDeVendaNegativoException("O valor inserido e negativo");
		}
		this.valorDeVenda = valor;
	}

	public float tempoTotalJogado() {
		float tempo = 0.0f;
		for (TempoJogo tempojogo : tempoJogado) {
			tempo += tempojogo.totalTempoJogado();
		}
		return tempo;
	}

	@Override
	public String toString() {

		return ( "ID do jogo: "+ this.codigoJogo+ "\nNome do jogo: " + this.getNomeJogo() + "\nValor: R$" + this.valorDeVenda + "\nDesenvolvedor: "
				+ this.getDesenvolvedor() + "\nDistribuidora: " + this.getDistribuidora() + "\nData de lancamento: "
				+ this.getDataLancamento() + "\nCategoria : " + this.getCategoria() + "\nTempo total jogado: "
				+ this.tempoTotalJogado() + " horas");
	}
}
