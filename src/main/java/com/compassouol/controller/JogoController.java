package com.compassouol.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.controller.dto.TempoJogoDto;
import com.compassouol.dto.entrada.JogoDtoEntrada;
import com.compassouol.dto.entrada.TempoJogoDtoEntrada;
import com.compassouol.dto.saida.JogoDtoSaida;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.services.JogoService;
import com.compassouol.services.TempoJogoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiOperation(value = "Lista todas as Avaliações", notes = "Lista todas as Avaliações", response = JogoDtoSaida.class, responseContainer = "List" )

@RestController
@RequestMapping(value = "/jogo")
public class JogoController {

	@Autowired
	private TempoJogoService tempoJogoService;
	
	@Autowired
	public JogoService jogoService;

	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Jogo adicionado com sucesso"),
			@ApiResponse(code = 400, message = "Entrada invalida"),
		    @ApiResponse(code = 404, message = "Jogo não encontrado na Steam") })
	@PostMapping
	public ResponseEntity<JogoDtoSaida> adicionaJogo(@RequestBody JogoDtoEntrada jogoDtoEntrada) throws IOException, ParseException {
		jogoDtoEntrada.aplicaValidacoes();
		JogoDtoSaida jogoDtoSaida = new JogoDtoSaida(jogoService.adicionaJogoBiblioteca(jogoDtoEntrada));
		
		return ResponseEntity.status(201).body(jogoDtoSaida);
	}
	
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Jogos encontrados com sucesso"),
			@ApiResponse(code = 400, message = "Entrada invalida"),
		    @ApiResponse(code = 404, message = "Biblioteca Vazia") })
	@GetMapping
	public ResponseEntity<List<JogoDtoSaida>> buscaTodosJogos() {
		
		JogoDtoSaida jogoDtoSaida = new JogoDtoSaida();
		
		return ResponseEntity.ok(jogoDtoSaida.retornaListaJogos(jogoService.retornaJogos()));
	}
	
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Jogo encontrado com sucesso"),
			@ApiResponse(code = 400, message = "Entrada invalida"),
		    @ApiResponse(code = 404, message = "Jogo não encontrado na biblioteca") })
	@GetMapping("/{id}")
	public ResponseEntity<JogoDtoSaida> buscaJogoPorId(@PathVariable("id") Integer jogoId) {
			Jogo jogo = jogoService.retornaJogoPorId(jogoId);
			JogoDtoSaida JogoDtoSaida;
			if(jogo != null) {
				JogoDtoSaida = new JogoDtoSaida(jogo);
				return ResponseEntity.ok(JogoDtoSaida);
			}
			else 
				throw new JogoInvalidoException("Jogo nao encontrado!");
			
			
	}
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Jogo removido com sucesso"),
			@ApiResponse(code = 400, message = "Entrada invalida"),
		    @ApiResponse(code = 404, message = "Jogo não encontrado na Biblioteca") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeJogo(@PathVariable("id") Integer jogoId) {
		
		jogoService.deletaJogo(jogoId);
		
		return ResponseEntity.ok("Jogo deletado");
		
	}
	
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Adicionado tempo de jogo com sucesso"),
			@ApiResponse(code = 400, message = "Entrada invalida"),
		    @ApiResponse(code = 404, message = "Jogo não encontrado na Steam") })
	@PostMapping("/{id}/jogar")
	public ResponseEntity<?> AddTempoJogoByDate(@PathVariable("id") Integer jogoId ,@RequestBody TempoJogoDtoEntrada tempoJogadoDtoEntrada) {
		
		tempoJogadoDtoEntrada.aplicaValidacoes();
		
		Jogo jogo = jogoService.retornaJogoPorId(jogoId);
		
		if(jogo == null)
			throw new JogoInvalidoException("Jogo nao encontrado!");
		
		tempoJogoService.adicionaTempoJogo(jogo, tempoJogadoDtoEntrada.getDataInicial(), tempoJogadoDtoEntrada.getDataFinal());
		
		TempoJogoDto tempoJogoDto = new TempoJogoDto(jogo.getAppIdSteam(),tempoJogadoDtoEntrada.getDataInicial(),tempoJogadoDtoEntrada.getDataFinal());
		
		return ResponseEntity.ok(tempoJogoDto);
		
	}
}