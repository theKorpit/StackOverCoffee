package com.compassouol.exceptions;

import java.time.LocalDateTime;

public class CamposNulosException extends RuntimeException{

	private static final long serialVersionUID = -7451043874592209114L;
	
	private Integer idSteam;
	private String nomeJogo;
	private LocalDateTime dataInicial;
	private LocalDateTime dataFinal;
	
	public CamposNulosException(Integer idSteam, String nomeJogo) {
		super("Entrada invalida! Pelo menos um dos campos deve estrar preenchido: ");
		this.idSteam = idSteam;
		this.nomeJogo = nomeJogo;
	}
	
	
	public CamposNulosException(Integer idSteam, LocalDateTime dataInicial, LocalDateTime dataFinal) {
		super("Entrada invalida! Todos os campos devem estrar preenchidos: ");
		this.idSteam = idSteam;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}
	
}