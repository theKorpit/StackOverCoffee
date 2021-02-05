package com.compassouol.controller.form;

import com.compassouol.configuration.ValidacaoDadosDeEntrada;
import com.compassouol.exceptions.CamposNulosException;
import com.compassouol.exceptions.EntradaInvalidaException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JogoForm implements ValidacaoDadosDeEntrada {
	
	private Integer idSteam;
	private String nomeJogo;

	@Override
	public void aplicaValidacoes() {
		this.entradaVazia();
		this.campoInvalido();
	}

	@Override
	public void entradaVazia() {
		if (this.idSteam == null && this.nomeJogo.trim().isEmpty())
			throw new CamposNulosException("idSteam ou nomeJogo");
	}

	@Override
	public void campoInvalido() {
		if (this.nomeJogo.length() > 255)
			throw new EntradaInvalidaException(this.nomeJogo);
		
	}
}