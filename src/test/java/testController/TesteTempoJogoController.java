package testController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.compassouol.controller.TempoJogoController;
import com.compassouol.controller.form.AddTempoJogoForm;
import com.compassouol.dao.BibliotecaDao;
import com.compassouol.dao.JogoDao;
import com.compassouol.exceptions.DataInicioMaiorQueDataFimException;
import com.compassouol.exceptions.JogosEmMesmoHorarioException;
import com.compassouol.model.Jogo;

@SpringBootTest(classes = com.compassouol.Starter.class)
class TesteTempoJogoController {

	@Autowired
	TempoJogoController tempoJogoController;

	@Test
	void testAddTempoJogo() {
		JogoDao jogoDao = new JogoDao();
		jogoDao.salva(new Jogo(70, "Nome Jogo", "Desenvolvedora", "Distribuidora", "26/07/1998", "Categoria", 100.0,
				"Descrição"));
		AddTempoJogoForm TempoJogoForm = new AddTempoJogoForm(70, LocalDateTime.now(),
				LocalDateTime.now().plusHours(1));
		tempoJogoController.AddTempoJogoByDate(TempoJogoForm);

		assertEquals(1, jogoDao.buscaPorId(70).getTempoJogado().size());
	}

	@Test
	void testAddTempoJogoDataFinalMaiorQueDataInicial() {
		JogoDao jogoDao = new JogoDao();
		jogoDao.salva(new Jogo(70, "Nome Jogo", "Desenvolvedora", "Distribuidora", "26/07/1998", "Categoria", 100.0,
				"Descrição"));
		AddTempoJogoForm TempoJogoForm = new AddTempoJogoForm(70, LocalDateTime.now().plusHours(1),
				LocalDateTime.now());
		try {
			tempoJogoController.AddTempoJogoByDate(TempoJogoForm);
			fail();
		} catch (DataInicioMaiorQueDataFimException e) {
		}
	}

	@Test
	void testAddTempoJogoDataConflitante() {
		JogoDao jogoDao = new JogoDao();
		jogoDao.salva(new Jogo(70, "Nome Jogo", "Desenvolvedora", "Distribuidora", "26/07/1998", "Categoria", 100.0,
				"Descrição"));
		AddTempoJogoForm TempoJogoForm = new AddTempoJogoForm(70, LocalDateTime.now(),
				LocalDateTime.now().plusHours(2));
		AddTempoJogoForm TempoJogoForm2 = new AddTempoJogoForm(70, LocalDateTime.now().plusHours(1),
				LocalDateTime.now().plusHours(2));
		tempoJogoController.AddTempoJogoByDate(TempoJogoForm);

		try {
			tempoJogoController.AddTempoJogoByDate(TempoJogoForm2);
			fail();
		} catch (JogosEmMesmoHorarioException e) {
		}

	}

	@Test
	void testAddTempoJogoJogoNaoExiste() {
		AddTempoJogoForm TempoJogoForm = new AddTempoJogoForm(70, LocalDateTime.now(),
				LocalDateTime.now().plusHours(1));

		assertEquals(
				tempoJogoController.AddTempoJogoByDate(TempoJogoForm).getStatusCode().compareTo(HttpStatus.NOT_FOUND),
				0);

	}
	
	@AfterEach
	void limpaBiblioteca() {
		BibliotecaDao.biblioteca.getJogos().clear();
	}

}
