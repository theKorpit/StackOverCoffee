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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.compassouol.dto.entrada.JogoDtoEntrada;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
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
	
	@Autowired
	private MockMvc mvc;
	
	private Jogo jogoCs = new Jogo(730, "Counter-Strike: Global Offensive", "Valve,Hidden Path Entertainment", "Valve", "21 Aug 2012", "Multi-player,Action,Free to Play", 0.0, null);
	
	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.jogoController);
	}


	@Test
	void testAdicionaJogoIdValido() throws Exception {
		
		JogoDtoEntrada jogoDtoEntrada = new JogoDtoEntrada(730, null);
		
		when(this.jogoService.adicionaJogoBiblioteca(jogoDtoEntrada)).thenReturn(jogoCs);
		
		mvc.perform(post("/jogo").content("{\n"
				+ "    \"idSteam\":\"730\",\n"
				+ "    \"nomeJogo\":\"\"\n"
				+ "}")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}

	@Test
	void testAdicionaJogoNomeValido() throws Exception {
		JogoDtoEntrada jogoDtoEntrada = new JogoDtoEntrada(null, "Counter-Strike: Global Offensive");
		
		when(this.jogoService.adicionaJogoBiblioteca(jogoDtoEntrada)).thenReturn(jogoCs);
		
		mvc.perform(post("/jogo").content("{\n"
				+ "    \"idSteam\":\"\",\n"
				+ "    \"nomeJogo\":\"Counter-Strike: Global Offensive\"\n"
				+ "}")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}

	@Test
	void testBuscaTodos() throws Exception {
		
		List<Jogo> jogos = new ArrayList<Jogo>();
		jogos.add(jogoCs);
		
		when(this.jogoService.retornaJogos()).thenReturn(jogos);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/jogo")
		.then()
			.statusCode(HttpStatus.OK.value());
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
			      .andExpect(result -> assertEquals("Nao foi localizado nenhum jogo com os dados informados no campo de busca acima!", result.getResolvedException().getMessage()));
	}
	
}