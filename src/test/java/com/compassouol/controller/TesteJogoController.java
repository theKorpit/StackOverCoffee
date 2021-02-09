package com.compassouol.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.repository.TempoJogoRepository;
import com.compassouol.services.JogoService;
import com.compassouol.services.TempoJogoService;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest
class TesteJogoController {

	@Autowired
	JogoController jogoController;
	
	@MockBean
	JogoService jogoService;
	
	@MockBean
	TempoJogoService tempojogoService;
	
	
	@MockBean
	private TempoJogoRepository tempoJogoRepository;
	
	@Autowired
	private MockMvc mvc;
	
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
	
	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.jogoController);
		this.insereDadoJogo();
	}

	@Test
	void testAdicionaJogoIdValido() throws Exception {
		when(this.jogoService.adicionaJogoBiblioteca(730,"")).thenReturn(jogoCs);
		
		mvc.perform(post("/jogo").content("{\n"
				+ "    \"idSteam\":\"730\",\n"
				+ "    \"nomeJogo\":\"\"\n"
				+ "}")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isCreated());
	}

	@Test
	void testAdicionaJogoNomeValido() throws Exception {
		
		when(this.jogoService.adicionaJogoBiblioteca(null,"Counter-Strike: Global Offensive")).thenReturn(jogoCs);
		
		mvc.perform(post("/jogo").content("{\n"
				+ "    \"idSteam\":\"\",\n"
				+ "    \"nomeJogo\":\"Counter-Strike: Global Offensive\"\n"
				+ "}")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isCreated());
	}

	@Test
	void testBuscaTodos() throws Exception {
		List<Jogo> jogos = new ArrayList<Jogo>();
		jogos.add(jogoCs);
		when(this.jogoService.retornaJogos(PageRequest.of(0, 10))).thenReturn(new PageImpl<Jogo>(jogos));
		
		mvc.perform(get("/jogo")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}
	
	@Test
	void testBuscaJogoPorIdJogoExisteNoBancoDeDados() {
		when(this.jogoService.retornaJogoPorId(730)).thenReturn(jogoCs);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/jogo/{id}",730)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void testBuscaJogoPorIdJogoNaoExisteNoBancoDeDados() throws Exception {
		when(this.jogoService.retornaJogoPorId(730)).thenReturn(null);
		
		mvc.perform(get("/jogo/{id}", 730)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNotFound())
			      .andExpect(result -> assertTrue(result.getResolvedException() instanceof JogoInvalidoException))
			      .andExpect(result -> assertEquals("Nao foi localizado nenhum jogo com os dados informados", result.getResolvedException().getMessage()));
	}
	
	@Test
	void testAvaliaJogo() throws Exception {
		
		mvc.perform(post("/jogo/{id}/avaliar",730).content("{\n"
				+ "    \"comentario\":\"Jogo muito bom\",\n"
				+ "    \"nota\":\"10\"\n"
				+ "}")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isCreated());
	}
	
	@Test
	void testAvaliaJogoSemNota() throws Exception {
		
		mvc.perform(post("/jogo/{id}/avaliar",730).content("{\n"
				+ "    \"comentario\":\"Jogo muito bom\",\n"
				+ "    \"nota\":\"\"\n"
				+ "}")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest());
	}
}