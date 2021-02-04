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
	
	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.tempoJogoController);
		tempoJogo = new ArrayList<TempoJogo>();
		tempoJogo.add(new TempoJogo(LocalDateTime.now(), LocalDateTime.now().plusHours(5)));
	}

	@Test
	void testEnvioIdJogoExistenteNoBancoDeDados() throws Exception {
		
		when(this.jogoService.retornaJogoPorId(10)).thenReturn(new Jogo(10, "Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0, "Descrição"));
		when(this.tempoJogoRepository.FindByJogo_appIdSteam(10, PageRequest.of(0, 10))).thenReturn(new PageImpl<TempoJogo>(tempoJogo));
		
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
			      .andExpect(result -> assertEquals("Nao foi localizado nenhum jogo com os dados informados no campo de busca acima!", result.getResolvedException().getMessage()));
	}
	
	/*@Test
	void test_RetornaStatus200_AdicionandoTempoJogoValidoEmUmJogoValido() throws Exception {
		
		when(this.jogoService.retornaJogoPorId(730)).thenReturn(new Jogo(730, "Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0, "Descrição"));

		mvc.perform(post("/jogar").content("{\n"
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
		
		
		mvc.perform(get("/jogar/{id}", 10)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest())
			      .andExpect(result -> assertTrue(result.getResolvedException() instanceof JogoInvalidoException))
			      .andExpect(result -> assertEquals("Jogo não existente", result.getResolvedException().getMessage()));

	}
	
	@Test
	void test_RetornaStatus401_IdDeJogoValidoETempoJogoInvalido() throws Exception {
		
		when(this.jogoService.retornaJogoPorId(10)).thenReturn(null);
		
		mvc.perform(post("/jogar").content("{\n"
				+ "    \"idJogo\":730,\n"
				+ "    \"dataInicial\":\"2021-01-09T23:00:00.000000\",\n"
				+ "    \"dataFinal\":\"2021-01-09T22:00:00.000000\"\n"
				+ "}")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest())
			      .andExpect(result -> assertTrue(result.getResolvedException() instanceof DataInicioMaiorQueDataFimException))
			      .andExpect(result -> assertEquals("A data inicial nao pode ser menor que a data final", result.getResolvedException().getMessage()));
	}*/

}