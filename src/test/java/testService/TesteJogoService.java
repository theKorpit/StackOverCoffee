package testService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.compassouol.exceptions.JogoDuplicadoException;
import com.compassouol.model.Jogo;
import com.compassouol.repository.JogoRepository;
import com.compassouol.services.JogoService;

@SpringBootTest(classes = com.compassouol.Starter.class)
class TesteJogoService {
	
	@Autowired
	JogoService jogoService;
	
	@MockBean
	JogoRepository jogoRepository;

	
	private Jogo jogoCs = new Jogo(730, "Counter-Strike: Global Offensive", "Valve,Hidden Path Entertainment", "Valve", "21 Aug 2012", "Multi-player,Action,Free to Play", 0.0, null);
	
	@Test
	void testAdicionaJogoNovoNaBibliotecaPorIdQuandoJogoNaoEstaNoBancoDeDados() throws IOException, ParseException {
		
		when(this.jogoRepository.findById(730)).thenReturn(Optional.empty());
	
		
		Jogo jogo = jogoService.adicionaJogoBiblioteca(730, null);
		
		assertEquals(730, jogo.getAppIdSteam());
		assertEquals("Counter-Strike: Global Offensive", jogo.getNomeJogo());
	}
	
	@Test
	void testAdicionaJogoNovoNaBibliotecaPorIdQuandoJogoEstaNoBancoDeDados() throws IOException, ParseException {
		
		when(this.jogoRepository.findById(730)).thenReturn(Optional.of(jogoCs));
		
		
		assertThrows(JogoDuplicadoException.class, () ->  jogoService.adicionaJogoBiblioteca(730, null));
		
	}
	
	@Test
	void testAdicionaJogoNovoNaBibliotecaPorNomeQuandoJogoNaoEstaNoBancoDeDados() throws IOException, ParseException {
		
		when(this.jogoRepository.findByNomeJogo("Half-life")).thenReturn(null);
		
		Jogo jogo = jogoService.adicionaJogoBiblioteca(null, "Half-life");
		
		assertEquals(70, jogo.getAppIdSteam());
		assertEquals("Half-life", jogo.getNomeJogo());
	}
	
	@Test
	void testAdicionaJogoNovoNaBibliotecaPorNomeQuandoJogoEstaNoBancoDeDados() throws IOException, ParseException {
		
		when(this.jogoRepository.findByNomeJogo("Counter-Strike: Global Offensive")).thenReturn(jogoCs);

		
		assertThrows(JogoDuplicadoException.class, () ->  jogoService.adicionaJogoBiblioteca(null, "Counter-Strike: Global Offensive"));
	}
	

	@Test
	void testRetornaJogoPorIdJogoExistenteNoBanco() throws IOException, ParseException {
		
		when(this.jogoRepository.findById(730)).thenReturn(Optional.of(jogoCs));
		
		assertTrue(jogoService.retornaJogoPorId(730) != null);
	}
	
	@Test
	void testRetornaJogoPorIdJogoInexistententeNoBanco() throws IOException, ParseException {
		
		when(this.jogoRepository.findById(730)).thenReturn(Optional.empty());
		
		assertTrue(jogoService.retornaJogoPorId(730) == null);
	}
	
}