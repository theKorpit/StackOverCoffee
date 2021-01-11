package test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.services.SteamApiService;

public class TesteApiSteam {
	@Test
	void testAdicionaJogoPorId() throws IOException, ParseException {
		SteamApiService apiTeste = new SteamApiService(70);
		Jogo j = apiTeste.retornaJogo();

		Assertions.assertEquals(j.getNomeJogo(), "Half-Life");
	}

	@Test
	void testAdicionaJogoPorNome() throws IOException, ParseException {
		SteamApiService apiTeste = new SteamApiService("Half-Life");
		Jogo j = apiTeste.retornaJogo();

		Assertions.assertEquals(j.getAppIdSteam(), 70);
	}

	@Test
	void testIdIncorreto() throws IOException, ParseException {

		assertThrows(JogoInvalidoException.class, () -> {
			SteamApiService sapi = new SteamApiService(1);
		});
	}

	@Test
	void testNomeJogoIncorreto() throws IOException, ParseException {

		assertThrows(JogoInvalidoException.class, () -> {
			SteamApiService sapi = new SteamApiService("RAUFI LAIFI");
		});
	}

	@Test
	void testRetornaInfoCorretaJogoPago() throws IOException, ParseException {

		SteamApiService apiTeste = new SteamApiService(70);
		Jogo j = apiTeste.retornaJogo();

		Assertions.assertEquals(j.getNomeJogo(), "Half-Life");
		Assertions.assertEquals(j.getDesenvolvedor(), "Valve");
		Assertions.assertEquals(j.getDistribuidora(), "Valve");
		Assertions.assertEquals(j.getDataLancamento(), "8 Nov 1998");
		Assertions.assertEquals(j.getCategoria(), "Single-player,Action");
		Assertions.assertEquals(j.getValorDeVenda(), 20.69);
		Assertions.assertEquals(j.getDescricao(), "Named Game of the Year by over 50 publications, "
				+ "Valve's debut title blends action and adventure with award-winning technology to create a frighteningly realistic world "
				+ "where players must think to survive. Also includes an exciting multiplayer mode that allows you to play against friends and enemies "
				+ "around the world.");
	}

	@Test
	void testRetornaInfoCorretaJogoGratis() throws IOException, ParseException {

		SteamApiService apiTeste = new SteamApiService(730);
		Jogo j = apiTeste.retornaJogo();

		Assertions.assertEquals(j.getNomeJogo(), "Counter-Strike: Global Offensive");
		Assertions.assertEquals(j.getDesenvolvedor(), "Valve,Hidden Path Entertainment");
		Assertions.assertEquals(j.getDistribuidora(), "Valve");
		Assertions.assertEquals(j.getDataLancamento(), "21 Aug 2012");
		Assertions.assertEquals(j.getCategoria(), "Multi-player,Action,Free to Play");
		Assertions.assertEquals(j.getValorDeVenda(), 0.0);
		Assertions.assertEquals(j.getDescricao(),
				"Counter-Strike: Global Offensive (CS: GO) expands upon the team-based action gameplay that "
						+ "it pioneered when it was launched 19 years ago."
						+ " CS: GO features new maps, characters, weapons, and game modes, and delivers updated versions of the classic CS content (de_dust2, etc.).");
	}
}
