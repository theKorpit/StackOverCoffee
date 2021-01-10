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
import com.compassouol.model.Jogo;
import com.compassouol.model.TempoJogo;
import com.compassouol.services.TempoJogoService;

@RestController
@RequestMapping(value = "/Jogar")
public class TempoJogoController {

	@Autowired
	private TempoJogoService tempoJogoService;
	
	@PostMapping
	public ResponseEntity<?> AddTempoJogoByDate(@Validated @RequestBody AddTempoJogoForm tempoJogoForm) {
		
		Jogo jogo = tempoJogoService.buscaJogoPorId(tempoJogoForm.getIdJogo());
	
		TempoJogo tempoJogo = new TempoJogo(tempoJogoForm.getDataInicial(), tempoJogoForm.getDataFinal());
		
		if(jogo == null) 
			return ResponseEntity.notFound().build();
		
		tempoJogoService.adicionaTempoJogo(jogo, tempoJogo);
		
		TempoJogoDto tempoJogoDto = new TempoJogoDto(jogo.getAppIdSteam(),tempoJogo.getDataInicio(),tempoJogo.getDataFim());
		
		return ResponseEntity.ok(tempoJogoDto);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> GetAllTempoJogoByJogo(@PathVariable("id") Integer jogoId){
		Jogo jogo = tempoJogoService.buscaJogoPorId(jogoId);
		
		if(jogo == null) 
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(TempoJogoDto.converteListaParaDto(jogo.getTempoJogado(), jogoId));	
	}	
}