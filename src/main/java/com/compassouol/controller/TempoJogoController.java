package com.compassouol.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.controller.dto.TempoJogoDto;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.model.TempoJogo;
import com.compassouol.repository.TempoJogoRepository;
import com.compassouol.services.JogoService;
import com.compassouol.services.TempoJogoService;

@RestController
@RequestMapping(value = "/tempojogado")
public class TempoJogoController {

	@Autowired
	private TempoJogoRepository tempoJogoRep;
	
	@Autowired
	private JogoService jogoService;
	
	@Autowired
	private TempoJogoService tempoJogoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Collection<TempoJogoDto>> GetAllTempoJogoByJogo(@PathVariable("id") Integer jogoId, @RequestParam(required = false) Integer pagina){
		Jogo jogo = jogoService.retornaJogoPorId(jogoId);
		
		if(jogo == null)
			throw new JogoInvalidoException("Jogo nao encontrado!");
		
		Pageable paginacao =null;
		
		if(pagina==null) 
			paginacao = PageRequest.of(0, 2);
		
		else 
			paginacao = PageRequest.of(pagina, 2);
		
		Page<TempoJogo> pageList =tempoJogoRep.FindByJogo_appIdSteam(jogoId, paginacao);

		return ResponseEntity.ok(TempoJogoDto.converteListaParaDto(pageList.getContent(), jogoId));	
	}	
	
	@GetMapping
	public ResponseEntity<Float> getAllTempo(){
		return ResponseEntity.ok(tempoJogoService.calculaTempoTotalJogado());	
	}
}