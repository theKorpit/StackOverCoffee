package testService;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.compassouol.dao.TempoJogoDao;
import com.compassouol.exceptions.JogosEmMesmoHorarioException;
import com.compassouol.model.Jogo;
import com.compassouol.model.TempoJogo;
import com.compassouol.services.TempoJogoService;

@SpringBootTest(classes = com.compassouol.Starter.class)
class TesteTempoJogoService {

	@Autowired
	private TempoJogoService tempoJogoService;

	@MockBean
	private TempoJogoDao tempoJogoDao;
	
	@Test
	void test_NaoRetornaException_RecebeTempoJogoValido() {
		
		List<TempoJogo> tempoJogoBD = new ArrayList<>();
		tempoJogoBD.add(new TempoJogo(LocalDateTime.now(), LocalDateTime.now().plusHours(2)));
		tempoJogoBD.add(new TempoJogo(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4)));
		
		when(this.tempoJogoDao.findAll()).thenReturn(tempoJogoBD);
		
		Jogo jogo = new Jogo(70, "Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0, "Descrição");
		
		TempoJogo tempoJogo = new TempoJogo(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(6));
		
		tempoJogoService.adicionaTempoJogo(jogo, tempoJogo);
		
	}
	
	@Test
	void test_RetornaException_RecebeTempoJogoInvalidoConflitoNoInicio() {

		List<TempoJogo> tempoJogoBD = new ArrayList<>();
		tempoJogoBD.add(new TempoJogo(LocalDateTime.parse("2021-01-01T13:00:00.000000"), LocalDateTime.parse("2021-01-01T14:00:00.000000")));
		tempoJogoBD.add(new TempoJogo(LocalDateTime.parse("2021-01-01T15:00:00.000000"), LocalDateTime.parse("2021-01-01T16:00:00.000000")));

		when(this.tempoJogoDao.findAll()).thenReturn(tempoJogoBD);
		
		Jogo jogo = new Jogo(70, "Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0, "Descrição");
		
		TempoJogo tempoJogo = new TempoJogo(LocalDateTime.parse("2021-01-01T15:30:00.000000"), LocalDateTime.parse("2021-01-01T16:30:00.000000"));
		
		try {
			tempoJogoService.adicionaTempoJogo(jogo, tempoJogo);
			fail();
		}catch (JogosEmMesmoHorarioException e) {
			
		}
		
	}
	
	

}
