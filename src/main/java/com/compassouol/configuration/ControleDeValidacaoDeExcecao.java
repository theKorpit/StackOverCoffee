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
	public List<GenericDto> InvalidFormatExceptionHandler(InvalidFormatException exception) {
		List<GenericDto> erros = new ArrayList<GenericDto>();	
		List<Reference> references = exception.getPath();
		
		references.forEach(e -> {
			GenericDto erro = new GenericDto(e.getFieldName(), exception.getCause().getMessage());
			erros.add(erro);
		});

		return erros;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataInicioMaiorQueDataFimException.class)
	public GenericDto DataInicioMaiorQueDataFimException(DataInicioMaiorQueDataFimException exception) {		
		GenericDto erro = new GenericDto("Data", exception.getMessage());
		return erro;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(JogoInvalidoException.class)
	public GenericDto JogoInvalidoException(JogoInvalidoException exception) {	
		GenericDto erro = null;
		
		if(exception.getIdSteam() != null)
			erro = new GenericDto("AppID", exception.getMessage());
		else
			erro = new GenericDto("Nome Jogo", exception.getMessage());
		
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
	public GenericDto JogoDuplicado(JogoDuplicadoException exception) {	
		GenericDto erro = null;
		
		if(exception.getId() != null)
			erro = new GenericDto("AppID", exception.getMessage());
		else
			erro = new GenericDto("Nome Jogo", exception.getMessage());
		
		return erro;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public GenericDto MethodArgumentNotValidException(MethodArgumentNotValidException exception) {	
		return new GenericDto(exception.getFieldError().getField(), exception.getFieldError().getDefaultMessage());
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CamposNulosException.class)
	public GenericDto CamposNulosExceptionHandler (CamposNulosException exception) {	
		return new GenericDto(exception.getCampoErro(), exception.getMessage());
	}		
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntradaInvalidaException.class)
    public GenericDto entradaInvalidaException(EntradaInvalidaException exception) {
        return new GenericDto(exception.getCampoErro(), exception.getMessage());
    }
	
	@ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(AvaliacaoDuplicadaException.class)
    public String avaliacaoDuplicada(AvaliacaoDuplicadaException exception) {
        return exception.getMessage();
    }
}