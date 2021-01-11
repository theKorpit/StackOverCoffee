package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.compassouol.dao.BibliotecaDao;
import com.compassouol.dao.JogoDao;
import com.compassouol.exceptions.JogosEmMesmoHorarioException;
import com.compassouol.model.Jogo;
import com.compassouol.model.TempoJogo;
import com.compassouol.services.TempoJogoService;

class TesteTempoJogoService {

	@Test
	void testAdicionaTempoJogo() {
		
		TempoJogoService tempoJogoService = new TempoJogoService();
		JogoDao jogoDao = new JogoDao();
		Jogo jogo = new Jogo(70, "Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0, "Descrição");
		jogoDao.salva(jogo);
		
		TempoJogo tempoJogo = new TempoJogo(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
		TempoJogo tempoJogo2 = new TempoJogo(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3));
		
		tempoJogoService.adicionaTempoJogo(jogo, tempoJogo);
		tempoJogoService.adicionaTempoJogo(jogo, tempoJogo2);
		
		assertEquals(jogo.getTempoJogado().size(), 2);

	}
	
	@Test
	void testAdicionaTempoJogoDataConflitante() {
		
		TempoJogoService tempoJogoService = new TempoJogoService();
		JogoDao jogoDao = new JogoDao();
		
		Jogo jogo = new Jogo(70, "Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0, "Descrição");
		TempoJogo tempoJogo = new TempoJogo(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3));
		TempoJogo tempoJogo2 = new TempoJogo(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4));
		jogoDao.salva(jogo);

		tempoJogoService.adicionaTempoJogo(jogo, tempoJogo);
		
		try {
			tempoJogoService.adicionaTempoJogo(jogo, tempoJogo2);
			fail("Data conflitante foi adicionada");
		}catch (JogosEmMesmoHorarioException e) {}

	}
	
	@AfterEach
	void limpaBiblioteca() {
		BibliotecaDao.biblioteca.getJogos().clear();
	}

}
