package com.compassouol.controller;

import java.io.IOException;
import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.dao.BibliotecaDao;
import com.compassouol.dao.JogoDao;
import com.compassouol.model.Jogo;
import com.compassouol.services.SteamApiService;

@RestController
@RequestMapping(value = "/Jogo")
public class JogoController {

	public JogoDao jogoDao = new JogoDao();

	@PostMapping
	public ResponseEntity<Jogo> adicionaJogo(@NotNull @RequestBody String campoDeBusca)
			throws IOException, ParseException {
		try {
			int idJogo = Integer.parseInt(campoDeBusca);
			Jogo jogo = jogoDao.findById(idJogo);
			if (jogo == null) {
				SteamApiService steam = new SteamApiService(idJogo);
				jogo = steam.retornaJogo();
				jogoDao.save(jogo);
				return ResponseEntity.ok(jogo);
			}
			else
				return ResponseEntity.badRequest().build();

		} catch (NumberFormatException e) {

			Jogo jogo = jogoDao.findByName(campoDeBusca);
			if (jogo == null) {
				SteamApiService steam = new SteamApiService(campoDeBusca);
				jogo = steam.retornaJogo();
				jogoDao.save(jogo);
				return ResponseEntity.ok(jogo);
			}
			else
				return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping
	public Collection<Jogo> buscaJogos() {
		return BibliotecaDao.biblioteca.getJogos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscaJogoPorId(@PathVariable("id") Integer jogoId) {
		Jogo jogo = jogoDao.findById(jogoId);

		if (jogo == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(jogo);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeJogo(@PathVariable("id") Integer jogoId) {
		Jogo jogo = jogoDao.findById(jogoId);
		if (jogo == null)
			return ResponseEntity.notFound().build();
		else {
			BibliotecaDao.biblioteca.getJogos().remove(jogo);
			return ResponseEntity.ok().build();
		}
	}
}