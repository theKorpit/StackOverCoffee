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

import com.compassouol.dto.entrada.JogoDtoEntrada;
import com.compassouol.dto.saida.JogoDtoSaida;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.services.JogoService;

@RestController
@RequestMapping(value = "/jogo")
public class JogoController {

	@Autowired
	public JogoService jogoService;

	@PostMapping
	public ResponseEntity<JogoDtoSaida> adicionaJogo(@RequestBody JogoDtoEntrada jogoDtoEntrada) throws IOException, ParseException {
		jogoDtoEntrada.aplicaValidacoes();
		/*faco aqui ou direto na service e só chamo o metodo da service*/
		JogoDtoSaida jogoDtoSaida = new JogoDtoSaida(jogoService.adicionaJogoBiblioteca(jogoDtoEntrada));
		
		return ResponseEntity.ok(jogoDtoSaida);
	}

	@GetMapping
	public ResponseEntity<List<JogoDtoSaida>> buscaTodosJogos() {
		
		JogoDtoSaida jogoDtoSaida = new JogoDtoSaida();
		
		return ResponseEntity.ok(jogoDtoSaida.retornaListaJogos(jogoService.retornaJogos()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<JogoDtoSaida> buscaJogoPorId(@PathVariable("id") Integer jogoId) {
			JogoDtoSaida jogoDtoSaida = new JogoDtoSaida(jogoService.retornaJogoPorId(jogoId));
			return ResponseEntity.ok(jogoDtoSaida);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeJogo(@PathVariable("id") Integer jogoId) {
		
		jogoService.deletaJogo(jogoId);
		
		return ResponseEntity.ok("Jogo deletado");
		
	}
}