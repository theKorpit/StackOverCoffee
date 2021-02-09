package com.compassouol.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter @Entity @Setter 
public class Jogo {
	@Id
	private Integer appIdSteam;
	private String nomeJogo;
	private String desenvolvedor;
	private String distribuidora;
	private String dataLancamento;
	private String categoria;
	private Double valorDeVenda;
	@Length(max = 1000)
	private String descricao;
	@OneToMany(mappedBy = "jogo")
	private Collection<TempoJogo> tempoJogado = new HashSet<TempoJogo>();;
	@OneToOne(mappedBy= "jogo",cascade = CascadeType.PERSIST)
	private Avaliacao avaliacao;

	public void adicionaTempoJogo(LocalDateTime dataInicio, LocalDateTime dataFim) {
		tempoJogado.add(new TempoJogo(dataInicio, dataFim));
	}

	public float tempoTotalJogado() {
		float tempo = 0.0f;
		for (TempoJogo tempojogo : tempoJogado) {
			tempo += tempojogo.totalTempoJogado();
		}
		return tempo;
	}
}