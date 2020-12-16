package com.compassouol.model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public class Jogo {
	private int codigoJogo;
	private String nomeJogo;
	private String desenvolvedor;
	private String distribuidora;
	private int appIdSteam;
	private String dataLancamento;
	private String categoria;
	private double valorDeVenda;
	private Collection<TempoJogo> tempoJogado;
	
	
	public Jogo(int codigoJogo, String nomeJogo, String desenvolvedor, String distribuidora,
			String dataLancamento, String categoria, double valorDeVenda) throws IOException {
		super();
		this.codigoJogo = codigoJogo;
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
	
	private void validaValorVenda(double valor) throws IOException {
		if(valor<0) {
			throw new IOException("O valor inserido é negativo");
		}
		this.valorDeVenda=valor;
	}
		
	public float tempoTotalJogado() {
		float tempo=0.0f;
		for (TempoJogo tempojogo : tempoJogado) {
			tempo+=tempojogo.totalTempoJogado();
		}
		return tempo;
	}
	
	@Override
	public String toString() {
		
		return ("Nome do jogo: " + this.getNomeJogo() +"\nValor:"+ this.valorDeVenda+ "R$" +  "\nDesenvolvedor: " + this.getDesenvolvedor() +
				"\nDistribuidora: " + this.getDistribuidora() + "\nData de lançamento: " + this.getDataLancamento() 
				+ "\nCategoria : " + this.getCategoria());
	}
}
