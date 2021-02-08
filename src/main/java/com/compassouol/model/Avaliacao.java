package com.compassouol.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Avaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer nota;
	private String comentario;
	@OneToOne
	private Jogo jogo;

	public Avaliacao(Integer nota, String comentario, Jogo jogo) {
		this.jogo = jogo;
		this.nota = nota;
		this.comentario = comentario;
		
	}
}