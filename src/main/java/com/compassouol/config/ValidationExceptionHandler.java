package com.compassouol.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.compassouol.controller.dto.ErroFormDto;
import com.compassouol.controller.dto.JogosEmMesmoHorarioExceptionDto;
import com.compassouol.exceptions.DataInicioMaiorQueDataFimException;
import com.compassouol.exceptions.JogoDuplicadoException;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.exceptions.JogosEmMesmoHorarioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class ValidationExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidFormatException.class)
	public List<ErroFormDto> InvalidFormatExceptionHandler(InvalidFormatException exception) {
		
		List<ErroFormDto> erros = new ArrayList<ErroFormDto>();	
		List<Reference> references = exception.getPath();
		
		references.forEach(e -> {
			ErroFormDto erro = new ErroFormDto(e.getFieldName(), exception.getCause().getMessage());
			erros.add(erro);
		});

		return erros;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataInicioMaiorQueDataFimException.class)
	public ErroFormDto DataInicioMaiorQueDataFimException(DataInicioMaiorQueDataFimException exception) {		
		ErroFormDto erro = new ErroFormDto("Data", exception.getMessage());
		return erro;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(JogoInvalidoException.class)
	public ErroFormDto JogoInvalidoException(JogoInvalidoException exception) {	
		ErroFormDto erro = null;
		
		if(exception.getId() != null)
			erro = new ErroFormDto("AppID", exception.getMessage());
		else
			erro = new ErroFormDto("Nome Jogo", exception.getMessage());
		
		return erro;
	}

	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(JogosEmMesmoHorarioException.class)
	public JogosEmMesmoHorarioExceptionDto JogosEmMesmoHorarioExceptionHandler(JogosEmMesmoHorarioException exception) {	
		JogosEmMesmoHorarioExceptionDto erroJogosEmMesmoHorarioExceptionDto = new JogosEmMesmoHorarioExceptionDto(exception.getDataInicio(), 
				exception.getDataFim(), exception.getDataInicioToAdd(), exception.getDataFimToAdd(),exception.getMessage());
		return erroJogosEmMesmoHorarioExceptionDto;
	}		
	
	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(JogoDuplicadoException.class)
	public ErroFormDto JogoDuplicado(JogoDuplicadoException exception) {	
		ErroFormDto erro = null;
		
		if(exception.getId() != null)
			erro = new ErroFormDto("AppID", exception.getMessage());
		else
			erro = new ErroFormDto("Nome Jogo", exception.getMessage());
		
		return erro;
	}
}