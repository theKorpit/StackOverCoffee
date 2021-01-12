package com.compassouol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.controller.dto.TempoJogoDto;
import com.compassouol.controller.form.AddTempoJogoForm;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.model.TempoJogo;
import com.compassouol.services.JogoService;
import com.compassouol.services.TempoJogoService;

@RestController
@RequestMapping(value = "/Jogar")
public class TempoJogoController {

	@Autowired
	private TempoJogoService tempoJogoService;
	@Autowired
	private JogoService jogoService;
	
	@PostMapping
	public ResponseEntity<?> AddTempoJogoByDate(@Validated @RequestBody AddTempoJogoForm tempoJogoForm) {
		
		Jogo jogo = jogoService.retornaJogoPorId(tempoJogoForm.getIdJogo());
	
		TempoJogo tempoJogo = new TempoJogo(tempoJogoForm.getDataInicial(), tempoJogoForm.getDataFinal());
		
		if(jogo == null) 
			throw new JogoInvalidoException("Jogo não existente", tempoJogoForm.getIdJogo());
		
		tempoJogoService.adicionaTempoJogo(jogo, tempoJogo);
		
		TempoJogoDto tempoJogoDto = new TempoJogoDto(jogo.getAppIdSteam(),tempoJogo.getDataInicio(),tempoJogo.getDataFim());
		
		return ResponseEntity.ok(tempoJogoDto);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> GetAllTempoJogoByJogo(@PathVariable("id") Integer jogoId){
		Jogo jogo = jogoService.retornaJogoPorId(jogoId);
		
		if(jogo == null) 
			throw new JogoInvalidoException("Jogo não existente", jogoId);
		
		return ResponseEntity.ok(TempoJogoDto.converteListaParaDto(jogo.getTempoJogado(), jogoId));	
	}	
}