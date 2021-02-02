package com.compassouol.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.controller.dto.TempoJogoDto;
import com.compassouol.dto.entrada.TempoJogoDtoEntrada;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.services.JogoService;
import com.compassouol.services.TempoJogoService;

@RestController
@RequestMapping(value = "/jogar")
public class TempoJogoController {


	@Autowired
	private JogoService jogoService;
	
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Collection<TempoJogoDto>> GetAllTempoJogoByJogo(@PathVariable("id") Integer jogoId){
		Jogo jogo = jogoService.retornaJogoPorId(jogoId);
		
		if(jogo == null)
			throw new JogoInvalidoException("Jogo nao encontrado!");

		return ResponseEntity.ok(TempoJogoDto.converteListaParaDto(jogo.getTempoJogado(), jogoId));	
	}	
}