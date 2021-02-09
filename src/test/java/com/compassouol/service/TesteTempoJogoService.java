package com.compassouol.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.compassouol.exceptions.JogosEmMesmoHorarioException;
import com.compassouol.model.Jogo;
import com.compassouol.model.TempoJogo;
import com.compassouol.repository.TempoJogoRepository;
import com.compassouol.services.TempoJogoService;

@SpringBootTest(classes = com.compassouol.Starter.class)
class TesteTempoJogoService {

	@Autowired
	private TempoJogoService tempoJogoService;

	@MockBean
	private TempoJogoRepository tempoRepository;
	
	private List<TempoJogo> tempoJogoBD;
	
	private Jogo jogo;
	
	@BeforeEach
	void TempoJogoListCreate() {
		jogo = new Jogo();
		
		tempoJogoBD = new ArrayList<>();
		tempoJogoBD.add(new TempoJogo(LocalDateTime.parse("2021-01-01T13:00:00.000000"), LocalDateTime.parse("2021-01-01T14:00:00.000000")));
		tempoJogoBD.add(new TempoJogo(LocalDateTime.parse("2021-01-01T15:00:00.000000"), LocalDateTime.parse("2021-01-01T16:00:00.000000")));
		
		when(this.tempoRepository.findAll()).thenReturn(tempoJogoBD);
	}
	
	@Test
	void test_NaoRetornaException_RecebeTempoJogoValido() {
		
		tempoJogoService.adicionaTempoJogo(jogo, LocalDateTime.parse("2021-01-01T17:00:00.000000"), LocalDateTime.parse("2021-01-01T18:00:00.000000"));
	}

	@Test
	void test_RetornaException_RecebeTempoJogoInvalidoConflitoNoInicio() {
		
		assertThrows(JogosEmMesmoHorarioException.class, () -> 
				tempoJogoService.adicionaTempoJogo(jogo,LocalDateTime.parse("2021-01-01T15:30:00.000000"), LocalDateTime.parse("2021-01-01T16:30:00.000000")));
	}
	
	@Test
	void test_RetornaException_RecebeTempoJogoInvalidoConflitoNoFim() {

		assertThrows(JogosEmMesmoHorarioException.class, () -> 
				tempoJogoService.adicionaTempoJogo(jogo,LocalDateTime.parse("2021-01-01T12:00:00.000000"), LocalDateTime.parse("2021-01-01T15:30:00.000000")));
	}
	
	@Test
	void test_RetornaException_RecebeTempoJogoInvalidoConflitoDatasIguais() {

		assertThrows(JogosEmMesmoHorarioException.class, () -> 
				tempoJogoService.adicionaTempoJogo(jogo,LocalDateTime.parse("2021-01-01T13:00:00.000000"), LocalDateTime.parse("2021-01-01T14:00:00.000000")));
	}
	
	@Test
	void test_RetornaException_RecebeTempoJogoInvalidoConflitoTotal() {

		assertThrows(JogosEmMesmoHorarioException.class, () -> 
				tempoJogoService.adicionaTempoJogo(jogo,LocalDateTime.parse("2021-01-01T12:00:00.000000"), LocalDateTime.parse("2021-01-01T17:00:00.000000")));
	}
}