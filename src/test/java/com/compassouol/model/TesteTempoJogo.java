package com.compassouol.model;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TesteTempoJogo {

	@Test
	void testTotalTempoJogado() {
		
		TempoJogo tempoJogo = new TempoJogo(LocalDateTime.now(), LocalDateTime.now().plusHours(2));
		
		Assertions.assertEquals(2f, tempoJogo.totalTempoJogado());
	}
}