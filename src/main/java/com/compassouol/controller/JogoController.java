package com.compassouol.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.controller.dto.TempoJogoDto;
import com.compassouol.dto.entrada.JogoDtoEntrada;
import com.compassouol.dto.entrada.TempoJogoDtoEntrada;
import com.compassouol.dto.saida.JogoDtoSaida;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.services.JogoService;
import com.compassouol.services.TempoJogoService;

@RestController
@RequestMapping(value = "/jogo")
public class JogoController {

	@Autowired
	public JogoDtoSaida jogoDtoSaida;
	
	@Autowired
	private TempoJogoService tempoJogoService;
	
	@Autowired
	public JogoService jogoService;

	@PostMapping
	public ResponseEntity<JogoDtoSaida> adicionaJogo(@RequestBody JogoDtoEntrada jogoDtoEntrada) throws IOException, ParseException {
		jogoDtoEntrada.aplicaValidacoes();
		JogoDtoSaida jogoDtoSaida = new JogoDtoSaida(jogoService.adicionaJogoBiblioteca(jogoDtoEntrada));
		
		return ResponseEntity.ok(jogoDtoSaida);
	}

	@GetMapping
	public ResponseEntity<List<JogoDtoSaida>> buscaTodosJogos(@RequestParam int pagina) {
		
		Pageable paginacao = PageRequest.of(pagina, 2);
		
		
		return ResponseEntity.ok(jogoDtoSaida.retornaListaJogos(jogoService.retornaJogos(paginacao)));
	}
	
	@GetMapping("/categoria/")
	public ResponseEntity<List<JogoDtoSaida>> buscaJogoPorCategoria(@RequestParam String categoria) {	
		
		return ResponseEntity.ok(jogoDtoSaida.retornaListaJogos(jogoService.retornaJogoPorCategoria(categoria)));
	}

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

	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeJogo(@PathVariable("id") Integer jogoId) {
		
		jogoService.deletaJogo(jogoId);
		
		return ResponseEntity.ok("Jogo deletado");
		
	}
	
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