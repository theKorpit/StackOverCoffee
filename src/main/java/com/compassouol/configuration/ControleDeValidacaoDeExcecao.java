package com.compassouol.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.compassouol.controller.dto.ErroValidacaoCampoDto;
import com.compassouol.controller.dto.JogosEmMesmoHorarioExceptionDto;
import com.compassouol.exceptions.CamposNulosException;
import com.compassouol.exceptions.DataInicioMaiorQueDataFimException;
import com.compassouol.exceptions.JogoDuplicadoException;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.exceptions.JogosEmMesmoHorarioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class ControleDeValidacaoDeExcecao {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidFormatException.class)
	public List<ErroValidacaoCampoDto> InvalidFormatExceptionHandler(InvalidFormatException exception) {
		List<ErroValidacaoCampoDto> erros = new ArrayList<ErroValidacaoCampoDto>();	
		List<Reference> references = exception.getPath();
		
		references.forEach(e -> {
			ErroValidacaoCampoDto erro = new ErroValidacaoCampoDto(e.getFieldName(), exception.getCause().getMessage());
			erros.add(erro);
		});

		return erros;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataInicioMaiorQueDataFimException.class)
	public ErroValidacaoCampoDto DataInicioMaiorQueDataFimException(DataInicioMaiorQueDataFimException exception) {		
		ErroValidacaoCampoDto erro = new ErroValidacaoCampoDto("Data", exception.getMessage());
		return erro;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(JogoInvalidoException.class)
	public ErroValidacaoCampoDto JogoInvalidoException(JogoInvalidoException exception) {	
		ErroValidacaoCampoDto erro = null;
		
		if(exception.getIdSteam() != null)
			erro = new ErroValidacaoCampoDto("AppID", exception.getMessage());
		else
			erro = new ErroValidacaoCampoDto("Nome Jogo", exception.getMessage());
		
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
	public ErroValidacaoCampoDto JogoDuplicado(JogoDuplicadoException exception) {	
		ErroValidacaoCampoDto erro = null;
		
		if(exception.getId() != null)
			erro = new ErroValidacaoCampoDto("AppID", exception.getMessage());
		else
			erro = new ErroValidacaoCampoDto("Nome Jogo", exception.getMessage());
		
		return erro;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErroValidacaoCampoDto JogoDuplicado(MethodArgumentNotValidException exception) {	
		return new ErroValidacaoCampoDto(exception.getFieldError().getField(), exception.getFieldError().getDefaultMessage());
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CamposNulosException.class)
	public ErroValidacaoCampoDto CamposNulosExceptionHandler (CamposNulosException exception) {	
		return new ErroValidacaoCampoDto(exception.getCampoErro(), exception.getMessage());
	}		
}