package com.compassouol.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.model.TempoJogo;
import com.compassouol.repository.TempoJogoRepository;
import com.compassouol.services.JogoService;
import com.compassouol.services.TempoJogoService;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest
class TesteTempoJogoController {

	@Autowired
	TempoJogoController tempoJogoController;
	
	@MockBean
	private TempoJogoService tempoJogoService;
	
	@MockBean
	private JogoService jogoService;
	
	@MockBean
	private TempoJogoRepository tempoJogoRepository;
	
	@Autowired
	private MockMvc mvc;
	
	private List<TempoJogo> tempoJogo;
	
	private Jogo jogoTeste = new Jogo();
	
	public void insereDadoJogo() {
		jogoTeste.setAppIdSteam(10);
		jogoTeste.setNomeJogo("Nome Jogo");
		jogoTeste.setDesenvolvedor("Desenvolvedora");
		jogoTeste.setDistribuidora( "Distribuidora");
		jogoTeste.setDataLancamento("26/07/1998");
		jogoTeste.setCategoria("Categoria");
		jogoTeste.setValorDeVenda(100.0);
		jogoTeste.setDescricao("Descrição");
	}
	
	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.tempoJogoController);
		tempoJogo = new ArrayList<TempoJogo>();
		tempoJogo.add(new TempoJogo(LocalDateTime.now(), LocalDateTime.now().plusHours(5)));
		
		this.insereDadoJogo();
	}

	@Test
	void testEnvioIdJogoExistenteNoBancoDeDados() throws Exception {
		
		when(this.jogoService.retornaJogoPorId(10)).thenReturn(jogoTeste);
		when(this.tempoJogoService.buscaTodosTempoJogos(10, PageRequest.of(0, 10))).thenReturn(new PageImpl<TempoJogo>(tempoJogo));
		
		mvc.perform(get("/tempojogado/{id}", 10)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}
	
	@Test
	void testEnvioIdJogoNaoExistenteNoBancoDeDados() throws Exception {
		
		when(this.jogoService.retornaJogoPorId(10)).thenReturn(null);

		mvc.perform(get("/tempojogado/{id}", 10)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNotFound())
			      .andExpect(result -> assertTrue(result.getResolvedException() instanceof JogoInvalidoException))
			      .andExpect(result -> assertEquals("Nao foi localizado nenhum jogo com os dados informados", result.getResolvedException().getMessage()));
	}
}