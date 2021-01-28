package testModel;

import static org.junit.Assert.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.compassouol.model.TempoJogo;

class TesteTempoJogo {

	@Test
	void testTotalTempoJogado() {
		
		LocalDateTime dataInicio = LocalDateTime.now();
		
		LocalDateTime dataFim = LocalDateTime.now().plusHours(2);
		
		
		TempoJogo tempoJogo = new TempoJogo(dataInicio, dataFim);
		
		float valor = tempoJogo.totalTempoJogado();
		
		Assertions.assertEquals(valor, 2f);
	}
	
	@Test
	void testTotalTempoJogadoFalha() {
		
		LocalDateTime dataInicio = LocalDateTime.now().plusHours(2);
		
		LocalDateTime dataFim = LocalDateTime.now();
		
		
		try {
			new TempoJogo(dataInicio, dataFim);
			fail("Data inicio maior que data final");
		}catch (Exception e) {}
		
	}

}
