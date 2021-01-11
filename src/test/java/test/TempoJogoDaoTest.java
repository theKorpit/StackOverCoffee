package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.compassouol.dao.JogoDao;
import com.compassouol.dao.TempoJogoDao;
import com.compassouol.model.Jogo;

class TempoJogoDaoTest {

	@Test
	void testBuscaTodos() {
		
		TempoJogoDao tempoJogoDao = new TempoJogoDao();
		JogoDao jogoDao = new JogoDao();
		Jogo jogo = new Jogo(70, "Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0, "Descrição");
		jogoDao.salva(jogo);
		
		LocalDateTime inicio = LocalDateTime.now().plusHours(1);
		LocalDateTime fim = LocalDateTime.now().plusHours(2);
		
		jogo.adicionaTempoJogo(inicio, fim);
		
		assertEquals(tempoJogoDao.findAll().size(), 1);

	}

}
