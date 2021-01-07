package com.compassouol.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.compassouol.controller.dto.ErroFormDto;
import com.compassouol.exceptions.DataInicioMaiorQueDataFimException;
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
	
}
