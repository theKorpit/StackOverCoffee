package com.compassouol.dto.entrada;

import java.time.LocalDateTime;

import com.compassouol.configuration.ValidacaoDadosDeEntrada;
import com.compassouol.exceptions.CamposNulosException;
import com.compassouol.exceptions.DataInicioMaiorQueDataFimException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TempoJogoDtoEntrada implements ValidacaoDadosDeEntrada {

	private Integer idSteam;
	private LocalDateTime dataInicial;
	private LocalDateTime dataFinal;
	
	@Override
	public void aplicaValidacoes() {
		this.entradaVazia();
		this.campoInvalido();
	}
	
	@Override
	public void entradaVazia() {
		if (this.idSteam == null || this.dataInicial == null || this.dataFinal == null)
			throw new CamposNulosException(this.idSteam, this.dataInicial, this.dataFinal);
	}
	
	@Override
	public void campoInvalido() {
		if(dataInicial.isAfter(dataFinal))
			throw new DataInicioMaiorQueDataFimException();
	}
}