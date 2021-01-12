package test;

import static org.junit.Assert.assertTrue;
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
import com.compassouol.services.SteamApiService;

@SpringBootTest(classes = com.compassouol.Starter.class)
class TesteJogoController {

	@Autowired
	JogoController jogoController;

	public String variavelStringIdValido = "70";
	public String variavelStringIdInvalido = "1";
	public String variavelStringNomeValido = "Half-life";
	public String variavelStringNomeInvalido = "saddawedaea";
	public Integer variavelIdValido = 70;
	public Integer variavelIdInvalido = 0;

	void metodoAdicionaJogo(String dado) throws IOException, ParseException {
		jogoController.adicionaJogo(dado);
	}

	void metodoBuscaJogoId(Integer dado) {
		jogoController.buscaJogoPorId(dado);
	}
	

	@Test
	void testAdicionaJogoIdValido() throws IOException, ParseException {
		this.metodoAdicionaJogo(this.variavelStringIdValido);
		assertTrue(BibliotecaDao.biblioteca.getJogos().size() > 0);
	}

	@Test
	void testAdicionaJogoNomeValido() throws IOException, ParseException {
		this.metodoAdicionaJogo(this.variavelStringNomeValido);
		assertTrue(BibliotecaDao.biblioteca.getJogos().size() > 0);
	}

	@Test
	void testAdicionaJogoIdInvalido() throws IOException, ParseException {
		try {
			this.metodoAdicionaJogo(this.variavelStringIdInvalido);
		} catch (JogoInvalidoException e) {}

		assertTrue(BibliotecaDao.biblioteca.getJogos().size() == 0);
	}

	@Test
	void testAdicionaJogoNomeInvalido() throws IOException, ParseException {
		try {
			this.metodoAdicionaJogo(this.variavelStringNomeInvalido);
		} catch (JogoInvalidoException e) {}
		
		assertTrue(BibliotecaDao.biblioteca.getJogos().size() == 0);
	}

	@Test
	void testBuscaJogoIdValido() throws IOException, ParseException {
		jogoController.adicionaJogo(this.variavelStringIdValido);
		this.metodoBuscaJogoId(this.variavelIdValido);
	}
	
	@Test
	void testBuscaJogoIdInvalido() throws IOException, ParseException {
		jogoController.adicionaJogo(this.variavelStringIdValido);
		assertThrows(JogoInvalidoException.class, () -> {
			this.metodoBuscaJogoId(this.variavelIdInvalido);
		});
	}

	@AfterEach
	void limpaBiblioteca() {
		BibliotecaDao.biblioteca.getJogos().clear();
	}
}