package com.compassouol.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.NotNull;

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

import com.compassouol.controller.dto.JogoDto;
import com.compassouol.dao.BibliotecaDao;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.services.JogoService;

@RestController
@RequestMapping(value = "/jogo")
public class JogoController {

	@Autowired
	public JogoService jogoService;

	@PostMapping
	public ResponseEntity<?> adicionaJogo(@NotNull @RequestBody String campoDeBusca) throws IOException, ParseException {
		if(jogoService.adicionaJogoBiblioteca(campoDeBusca))
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.badRequest().build();
	}

	@GetMapping
	public ResponseEntity<List<JogoDto>> buscaJogos() {	
		return ResponseEntity.ok(new JogoDto().converteJogoParaJogoDto(BibliotecaDao.biblioteca.getJogos()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscaJogoPorId(@PathVariable("id") Integer jogoId) {
		
		if (jogoService.retornaJogoPorId(jogoId) == null)
			throw new JogoInvalidoException("Jogo nao encontrado!", jogoId);

		return ResponseEntity.ok(jogoService.retornaJogoPorId(jogoId));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeJogo(@PathVariable("id") Integer jogoId) {
		
		if (jogoService.retornaJogoPorId(jogoId) == null) {
			throw new JogoInvalidoException("Jogo nao encontrado!", jogoId);
		}
		else {
			BibliotecaDao.biblioteca.getJogos().remove(jogoService.retornaJogoPorId(jogoId));
			return ResponseEntity.ok().build();
		}
	}
}