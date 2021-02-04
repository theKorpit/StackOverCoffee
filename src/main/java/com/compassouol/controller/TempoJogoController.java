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

import com.compassouol.controller.dto.TempoJogoDto;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.model.TempoJogo;
import com.compassouol.repository.TempoJogoRepository;
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
	private TempoJogoRepository tempoJogoRepository;
	
	@Autowired
	private JogoService jogoService;
	
	
	@Autowired
	private TempoJogoService tempoJogoService;
	
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Adicionado tempo de jogo com sucesso"),
			@ApiResponse(code = 400, message = "Entrada invalida"),
		    @ApiResponse(code = 404, message = "Jogo não encontrado na Steam") })
	
	@GetMapping("/{id}")
	public ResponseEntity<Page<TempoJogoDto>> GetAllTempoJogoByJogo(@PathVariable("id") Integer jogoId,@PageableDefault(size = 10) Pageable paginacao){
		Jogo jogo = jogoService.retornaJogoPorId(jogoId);
		
		if(jogo == null)
			throw new JogoInvalidoException("Jogo nao encontrado!");
		
		Page<TempoJogo> pageList = tempoJogoRepository.FindByJogo_appIdSteam(jogoId, paginacao);

		return ResponseEntity.ok(TempoJogoDto.converteListaParaDto(pageList, jogoId));	
	}	
	
	@GetMapping
	public ResponseEntity<Float> getAllTempo(){
		return ResponseEntity.ok(tempoJogoService.calculaTempoTotalJogado());	
	}
}