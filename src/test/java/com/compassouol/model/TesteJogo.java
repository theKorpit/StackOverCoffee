package com.compassouol.model;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TesteJogo {

	@Test
	void testAdicionaTempoJogo() {
		Jogo jogo = new Jogo();
		jogo.adicionaTempoJogo(LocalDateTime.now(), LocalDateTime.now().plusHours(2));

		Assertions.assertEquals(jogo.getTempoJogado().size(), 1);
	}
	
	
	@Test
	void testTempoTotalJogado() {
		Jogo jogo = new Jogo();
		
		jogo.adicionaTempoJogo(LocalDateTime.now(), LocalDateTime.now().plusHours(2));
		jogo.adicionaTempoJogo(LocalDateTime.now(), LocalDateTime.now().plusHours(4));
		
		Assertions.assertEquals(jogo.tempoTotalJogado(), 6f);
	}

}
