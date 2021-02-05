package com.compassouol.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.services.SteamApiService;

@SpringBootTest(classes = com.compassouol.Starter.class)
public class TesteApiSteam {
	@Autowired
	private SteamApiService steamAPI;

	@Test
	void testAdicionaJogoPorId() throws IOException, ParseException {
		Jogo j = steamAPI.retornaJogo(70, "");
		Assertions.assertEquals("Half-Life", j.getNomeJogo());
	}

	@Test
	void testAdicionaJogoPorNome() throws IOException, ParseException {

		Jogo j = steamAPI.retornaJogo(null, "Half-Life");
		Assertions.assertEquals(70, j.getAppIdSteam());
	}

	@Test
	void testIdIncorreto() throws IOException, ParseException {

		assertThrows(JogoInvalidoException.class, () -> {
			steamAPI.retornaJogo(1, "");
		});
	}

	@Test
	void testNomeJogoIncorreto() throws IOException, ParseException {

		assertThrows(JogoInvalidoException.class, () -> {
			steamAPI.retornaJogo(null, "RAUFI LAIFI");
		});
	}

	@Test
	void testRetornaInfoCorretaJogoPago() throws IOException, ParseException {

		Jogo j = steamAPI.retornaJogo(70, "");

		Assertions.assertEquals("Half-Life", j.getNomeJogo());
		Assertions.assertEquals("Valve", j.getDesenvolvedor());
		Assertions.assertEquals("Valve", j.getDistribuidora());
		Assertions.assertEquals("8 Nov 1998", j.getDataLancamento());
		Assertions.assertEquals("Single-player,Action", j.getCategoria());
		Assertions.assertEquals(20.69, j.getValorDeVenda());
		Assertions.assertEquals("Named Game of the Year by over 50 publications, "
				+ "Valve's debut title blends action and adventure with award-winning technology to create a frighteningly realistic world "
				+ "where players must think to survive. Also includes an exciting multiplayer mode that allows you to play against friends and enemies "
				+ "around the world.", j.getDescricao());
	}

	@Test
	void testRetornaInfoCorretaJogoGratis() throws IOException, ParseException {

		Jogo j = steamAPI.retornaJogo(730, "");

		Assertions.assertEquals("Counter-Strike: Global Offensive", j.getNomeJogo());
		Assertions.assertEquals("Valve,Hidden Path Entertainment", j.getDesenvolvedor());
		Assertions.assertEquals("Valve", j.getDistribuidora());
		Assertions.assertEquals("21 Aug 2012", j.getDataLancamento());
		Assertions.assertEquals("Multi-player,Action,Free to Play", j.getCategoria());
		Assertions.assertEquals(0.0, j.getValorDeVenda());
		Assertions.assertEquals(
				"Counter-Strike: Global Offensive (CS: GO) expands upon the team-based action gameplay that "
						+ "it pioneered when it was launched 19 years ago."
						+ " CS: GO features new maps, characters, weapons, and game modes, and delivers updated versions of the classic CS content (de_dust2, etc.).",
				j.getDescricao());
	}
}
