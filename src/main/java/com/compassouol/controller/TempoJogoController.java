package com.compassouol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.controller.dto.GenericDto;
import com.compassouol.controller.dto.TempoJogoDto;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.services.JogoService;
import com.compassouol.services.TempoJogoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiOperation(value = "Lista todas as Avaliações", notes = "Lista todas as Avaliações", response = TempoJogoDto.class, responseContainer = "List" )
@RestController
@RequestMapping(value = "/tempojogado")
public class TempoJogoController {
	
	@Autowired
	private JogoService jogoService;
	@Autowired
	private TempoJogoService tempoJogoService;
	
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retornado total de tempo de jogado") })
	@GetMapping
	public ResponseEntity<GenericDto> getAllTempo(){
		Float tempoTotal = tempoJogoService.calculaTempoTotalJogado();
		return ResponseEntity.status(200).body(new GenericDto(tempoTotal.toString(), "Tempo total gasto jogando."));
	}
	
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retornado lista de tempo de jogo com sucesso"),
							@ApiResponse(code = 404, message = "Jogo não encontrado na Steam") })
	@GetMapping("/{id}")
	public ResponseEntity<Page<TempoJogoDto>> GetAllTempoJogoByJogo(@PathVariable("id") Integer jogoId,@PageableDefault(size = 10) Pageable paginacao){
		Jogo jogo = jogoService.retornaJogoPorId(jogoId);
		if(jogo == null)
			throw new JogoInvalidoException(jogoId);
		return ResponseEntity.status(200).body(new TempoJogoDto().retornaListaTempoJogo(tempoJogoService.buscaTodosTempoJogos(jogoId, paginacao), jogoId));	
	}	
}