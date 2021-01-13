package com.compassouol.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.compassouol.exceptions.DataInicioMaiorQueDataFimException;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.services.JogoService;
import com.compassouol.services.TempoJogoService;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
@WebMvcTest
class TesteTempoJogoController {

	@Autowired
	TempoJogoController tempoJogoController;
	
	@MockBean
	private TempoJogoService tempoJogoService;
	
	@MockBean
	private JogoService jogoService;
	
	@Autowired
	private MockMvc mvc;
	
	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.tempoJogoController);
	}

	@Test
	void test_RetornaStatus200_IdDeJogoValidoRequisitado() {
		
		when(this.jogoService.retornaJogoPorId(10)).thenReturn(new Jogo(10, "Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0, "Descrição"));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/Jogar/{id}",10)
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	void test_RetornaStatus401_IdDeJogoInexistente() throws Exception {
		
		when(this.jogoService.retornaJogoPorId(10)).thenReturn(null);
		
		mvc.perform(get("/Jogar/{id}", 10)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest())
			      .andExpect(result -> assertTrue(result.getResolvedException() instanceof JogoInvalidoException))
			      .andExpect(result -> assertEquals("Jogo não existente", result.getResolvedException().getMessage()));
	}
	
	@Test
	void test_RetornaStatus200_AdicionandoTempoJogoValidoEmUmJogoValido() throws Exception {
		
		when(this.jogoService.retornaJogoPorId(730)).thenReturn(new Jogo(730, "Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0, "Descrição"));

		mvc.perform(post("/Jogar").content("{\n"
				+ "    \"idJogo\":730,\n"
				+ "    \"dataInicial\":\"2021-01-09T20:30:00.000000\",\n"
				+ "    \"dataFinal\":\"2021-01-09T22:45:00.000000\"\n"
				+ "}")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());

	}
	
	@Test
	void test_RetornaStatus401_IdDeJogoInexistenteETempoJogoValido() throws Exception {
		
		when(this.jogoService.retornaJogoPorId(10)).thenReturn(null);
		
		
		mvc.perform(get("/Jogar/{id}", 10)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest())
			      .andExpect(result -> assertTrue(result.getResolvedException() instanceof JogoInvalidoException))
			      .andExpect(result -> assertEquals("Jogo não existente", result.getResolvedException().getMessage()));

	}
	
	@Test
	void test_RetornaStatus401_IdDeJogoValidoETempoJogoInvalido() throws Exception {
		
		when(this.jogoService.retornaJogoPorId(10)).thenReturn(null);
		
		mvc.perform(post("/Jogar").content("{\n"
				+ "    \"idJogo\":730,\n"
				+ "    \"dataInicial\":\"2021-01-09T23:00:00.000000\",\n"
				+ "    \"dataFinal\":\"2021-01-09T22:00:00.000000\"\n"
				+ "}")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest())
			      .andExpect(result -> assertTrue(result.getResolvedException() instanceof DataInicioMaiorQueDataFimException))
			      .andExpect(result -> assertEquals("A data inicial nao pode ser menor que a data final", result.getResolvedException().getMessage()));

	}

}