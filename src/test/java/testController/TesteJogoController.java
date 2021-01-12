package testController;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.compassouol.controller.JogoController;
import com.compassouol.dao.BibliotecaDao;
import com.compassouol.exceptions.JogoInvalidoException;

@SpringBootTest(classes = com.compassouol.Starter.class)
class TesteJogoController {

	@Autowired
	JogoController jogoController;

	@Test
	void testAdicionaJogoIdValido() throws IOException, ParseException {
		jogoController.adicionaJogo("70");
		assertTrue(BibliotecaDao.biblioteca.getJogos().size() > 0);
	}

	@Test
	void testAdicionaJogoNomeValido() throws IOException, ParseException {
		jogoController.adicionaJogo("Half-life");
		assertTrue(BibliotecaDao.biblioteca.getJogos().size() > 0);
	}

	@Test
	void testAdicionaJogoIdInvalido() throws IOException, ParseException {
		try {
			jogoController.adicionaJogo("1");
		} catch (JogoInvalidoException e) {
		}

		assertTrue(BibliotecaDao.biblioteca.getJogos().size() == 0);
	}

	@Test
	void testAdicionaJogoNomeInvalido() throws IOException, ParseException {
		try {
			jogoController.adicionaJogo("saskjendw");
		} catch (JogoInvalidoException e) {
		}

		assertTrue(BibliotecaDao.biblioteca.getJogos().size() == 0);
	}

	@Test
	void testBuscaJogoIdValido() throws IOException, ParseException {
		jogoController.adicionaJogo("70");
		assertDoesNotThrow(() -> {
			jogoController.buscaJogoPorId(70);
		});

	}

	@Test
	void testBuscaJogoIdInvalido() throws IOException, ParseException {
		jogoController.adicionaJogo("70");
		assertThrows(JogoInvalidoException.class, () -> {
			jogoController.buscaJogoPorId(1);
		});
	}

	@AfterEach
	void limpaBiblioteca() {
		BibliotecaDao.biblioteca.getJogos().clear();
	}
}