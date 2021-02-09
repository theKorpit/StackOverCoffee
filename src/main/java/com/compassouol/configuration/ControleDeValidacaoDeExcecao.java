package com.compassouol.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.compassouol.controller.dto.GenericDto;
import com.compassouol.controller.dto.JogosEmMesmoHorarioExceptionDto;
import com.compassouol.exceptions.AvaliacaoDuplicadaException;
import com.compassouol.exceptions.CamposNulosException;
import com.compassouol.exceptions.DataInicioMaiorQueDataFimException;
import com.compassouol.exceptions.EntradaInvalidaException;
import com.compassouol.exceptions.JogoDuplicadoException;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.exceptions.JogosEmMesmoHorarioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class ControleDeValidacaoDeExcecao {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidFormatException.class)
	public List<GenericDto> invalidFormatExceptionHandler(InvalidFormatException exception) {
		List<GenericDto> erros = new ArrayList<GenericDto>();
		List<Reference> references = exception.getPath();

		for (Reference referencia : references)
			erros.add(new GenericDto(referencia.getFieldName(),
					"Valor atribuido invalido: " + exception.getValue().toString()));
		return erros;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataInicioMaiorQueDataFimException.class)
	public GenericDto dataInicioMaiorQueDataFimExceptionHandler(DataInicioMaiorQueDataFimException exception) {
		return new GenericDto("Data", exception.getMessage());
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public GenericDto methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
		return new GenericDto(exception.getFieldError().getField(), exception.getFieldError().getDefaultMessage());
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CamposNulosException.class)
	public GenericDto camposNulosExceptionHandler(CamposNulosException exception) {
		return new GenericDto(exception.getCampoErro(), exception.getMessage());
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EntradaInvalidaException.class)
	public GenericDto entradaInvalidaExceptionHandler(EntradaInvalidaException exception) {
		return new GenericDto(exception.getCampoErro(), exception.getMessage());
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(JogoInvalidoException.class)
	public GenericDto jogoInvalidoExceptionHandler(JogoInvalidoException exception) {
		if (exception.getIdSteam() != null)
			return new GenericDto("AppID", exception.getMessage());
		else
			return new GenericDto("Nome Jogo", exception.getMessage());
	}

	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(JogosEmMesmoHorarioException.class)
	public JogosEmMesmoHorarioExceptionDto jogosEmMesmoHorarioExceptionHandler(JogosEmMesmoHorarioException exception) {
		return new JogosEmMesmoHorarioExceptionDto(exception.getDataInicio(), exception.getDataFim(),
				exception.getDataInicioToAdd(), exception.getDataFimToAdd(), exception.getMessage());
	}

	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(JogoDuplicadoException.class)
	public GenericDto jogoDuplicadoExceptionHandler(JogoDuplicadoException exception) {
		if (exception.getId() != null)
			return new GenericDto("AppID", exception.getMessage());
		else
			return new GenericDto("Nome Jogo", exception.getMessage());
	}

	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(AvaliacaoDuplicadaException.class)
	public String avaliacaoDuplicada(AvaliacaoDuplicadaException exception) {
		return exception.getMessage();
	}
}