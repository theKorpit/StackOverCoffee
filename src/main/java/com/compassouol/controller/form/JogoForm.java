package com.compassouol.controller.form;

import com.compassouol.exceptions.CamposNulosException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JogoForm {
	
	private Integer idSteam;
	private String nomeJogo;

	public void aplicaValidacoes() {
		if(this.idSteam == null && this.nomeJogo.trim().isEmpty())
			throw new CamposNulosException("idSteam ou nomeJogo");
	}
}