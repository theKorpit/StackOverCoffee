package testService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.compassouol.dao.BibliotecaDao;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.services.JogoService;

class TesteJogoService {
	
	JogoService jogoService = new JogoService();
	
	private String variavelStringIdValido = "70"; 
	private String variavelStringIdInvalido = "awdwda";
	private String variavelStringValida = "70";
	
	
	@Test
	void testVerificaIdValido() {
		assertTrue(jogoService.verificaId(this.variavelStringIdValido));
	}

	@Test
	void testVerificaIdInvalido() {
		assertFalse(jogoService.verificaId(this.variavelStringIdInvalido));
	}
	
	@Test
	void testAdicionaJogoNovoNaBibliotecaPorId() throws IOException, ParseException {
		assertTrue(jogoService.adicionaJogoBiblioteca("70"));
	}
	
	@Test
	void testAdicionaJogoNovoNaBibliotecaPorNome() throws IOException, ParseException {
		assertTrue(jogoService.adicionaJogoBiblioteca("Half-life"));
	}
	
	@Test
	void testNaoiAdicionaJogoExistenteNaBiblioteca() throws IOException, ParseException {
		jogoService.adicionaJogoBiblioteca("Half-life");
		assertThrows(JogoInvalidoException.class, () -> {
			jogoService.adicionaJogoBiblioteca("70");
		});
	}
	
	@Test
	void testRetornaJogoPorId() throws IOException, ParseException {
		jogoService.adicionaJogoBiblioteca("Half-life");
		assertTrue(jogoService.retornaJogoPorId(80) != null);
	}
	
	@AfterEach
	void limpaBiblioteca() {
		BibliotecaDao.biblioteca.getJogos().clear();
	}
}