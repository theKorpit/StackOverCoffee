package com.compassouol.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.compassouol.exceptions.AvaliacaoDuplicadaException;
import com.compassouol.exceptions.JogoDuplicadoException;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Avaliacao;
import com.compassouol.model.Jogo;
import com.compassouol.repository.AvaliacaoRepository;
import com.compassouol.repository.JogoRepository;
import com.compassouol.services.JogoService;
import com.compassouol.services.SteamApiService;

@SpringBootTest(classes = com.compassouol.Starter.class)
class TesteJogoService {

	@Autowired
	JogoService jogoService;

	@MockBean
	JogoRepository jogoRepository;

	@MockBean
	SteamApiService steamApiService;

	@MockBean
	AvaliacaoRepository avaliacaoRepository;

	private Jogo jogoCs = new Jogo();
	
	public void insereDadoJogo() {
		jogoCs.setAppIdSteam(730);
		jogoCs.setNomeJogo("Counter-Strike: Global Offensive");
		jogoCs.setDesenvolvedor("Valve,Hidden Path Entertainment");
		jogoCs.setDistribuidora("Valve");
		jogoCs.setDataLancamento("21 Aug 2012");
		jogoCs.setCategoria("Multi-player,Action,Free to Play");
		jogoCs.setValorDeVenda(0.0);
		jogoCs.setDescricao(null);
	}

	@Test
	void testAdicionaJogoNovoNaBibliotecaPorIdQuandoJogoNaoEstaNoBancoDeDados() throws IOException, ParseException {
		this.insereDadoJogo();
		
		when(this.jogoRepository.findByAppIdSteam(730)).thenReturn(null);
		when(this.steamApiService.retornaJogo(730, null)).thenReturn(jogoCs);

		Jogo jogo = jogoService.adicionaJogoBiblioteca(730, null);

		assertEquals(730, jogo.getAppIdSteam());
		assertEquals("Counter-Strike: Global Offensive", jogo.getNomeJogo());
	}

	@Test
	void testAdicionaJogoNovoNaBibliotecaPorIdQuandoJogoEstaNoBancoDeDados() throws IOException, ParseException {
		this.insereDadoJogo();
		
		when(this.jogoRepository.findByAppIdSteam(730)).thenReturn(jogoCs);

		assertThrows(JogoDuplicadoException.class, () -> jogoService.adicionaJogoBiblioteca(730, null));

	}

	@Test
	void testAdicionaJogoNovoNaBibliotecaPorNomeQuandoJogoNaoEstaNoBancoDeDados() throws IOException, ParseException {
		this.insereDadoJogo();
		
		when(this.jogoRepository.findByNomeJogo("Counter-Strike: Global Offensive")).thenReturn(null);
		when(this.steamApiService.retornaJogo(null, "Counter-Strike: Global Offensive")).thenReturn(jogoCs);

		Jogo jogo = jogoService.adicionaJogoBiblioteca(null, "Counter-Strike: Global Offensive");

		assertEquals(730, jogo.getAppIdSteam());
		assertEquals("Counter-Strike: Global Offensive", jogo.getNomeJogo());
	}

	@Test
	void testAdicionaJogoNovoNaBibliotecaPorNomeQuandoJogoEstaNoBancoDeDados() throws IOException, ParseException {
		this.insereDadoJogo();
		
		when(this.jogoRepository.findByNomeJogo("Counter-Strike: Global Offensive")).thenReturn(jogoCs);

		assertThrows(JogoDuplicadoException.class,
				() -> jogoService.adicionaJogoBiblioteca(null, "Counter-Strike: Global Offensive"));
	}

	@Test
	void testRetornaJogoPorIdJogoExistenteNoBanco() throws IOException, ParseException {
		this.insereDadoJogo();
		
		when(this.jogoRepository.findByAppIdSteam(730)).thenReturn(jogoCs);

		assertTrue(jogoService.retornaJogoPorId(730) != null);
	}

	@Test
	void testRetornaJogoPorIdJogoInexistententeNoBanco() throws IOException, ParseException {

		when(this.jogoRepository.findById(730)).thenReturn(Optional.empty());

		assertTrue(jogoService.retornaJogoPorId(730) == null);
	}

	@Test
	void testAdicionaAvaliacaoJogoInvalido() {
		when(this.jogoRepository.findByAppIdSteam(730)).thenReturn(null);
		
		assertThrows(JogoInvalidoException.class,() -> jogoService.adicionarAvaliacao(730, "", 10));
	}
	
	@Test
	void testAdicionaAvaliacaoJogoValidoAvaliacaoDuplicada() {
		this.insereDadoJogo();
		
		when(this.jogoRepository.findByAppIdSteam(730)).thenReturn(jogoCs);
		jogoCs.setAvaliacao(new Avaliacao(10,"muito bom",jogoCs));
		
		assertThrows(AvaliacaoDuplicadaException.class,() -> jogoService.adicionarAvaliacao(730, "", 10));
	}
	
	@Test
	void testAdicionaAvaliacaoJogoValidoCorreto() {
		this.insereDadoJogo();
		
		when(this.jogoRepository.findByAppIdSteam(730)).thenReturn(jogoCs);
		
		jogoService.adicionarAvaliacao(730, "", 10);
		assertNotEquals(null,jogoCs.getAvaliacao());
	}
}