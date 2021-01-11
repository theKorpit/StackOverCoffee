package test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.compassouol.controller.TempoJogoController;
import com.compassouol.controller.form.AddTempoJogoForm;
import com.compassouol.dao.JogoDao;
import com.compassouol.exceptions.DataInicioMaiorQueDataFimException;
import com.compassouol.exceptions.JogosEmMesmoHorarioException;
import com.compassouol.model.Jogo;
@RunWith( SpringRunner.class )
@SpringBootTest
class TesteTempoJogoController {

	
	@Test
	void testAddTempoJogo() {
		TempoJogoController tempoJogoController = new TempoJogoController();
		JogoDao jogoDao = new JogoDao();
		jogoDao.salva(new Jogo(70, "Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0, "Descrição"));
		AddTempoJogoForm TempoJogoForm = new AddTempoJogoForm(70,LocalDateTime.now(),LocalDateTime.now().plusHours(1));
		tempoJogoController.AddTempoJogoByDate(TempoJogoForm);
		
		
		assertEquals(jogoDao.buscaPorId(70).getTempoJogado().size(), 1);
	}
	
	@Test
	void testAddTempoJogoDataFinalMaiorQueDataInicial() {
		TempoJogoController tempoJogoController = new TempoJogoController();
		JogoDao jogoDao = new JogoDao();
		jogoDao.salva(new Jogo(70, "Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0, "Descrição"));
		AddTempoJogoForm TempoJogoForm = new AddTempoJogoForm(70,LocalDateTime.now().plusHours(1),LocalDateTime.now());
		try {
			tempoJogoController.AddTempoJogoByDate(TempoJogoForm);
			fail();
		}catch (DataInicioMaiorQueDataFimException e) {}
	}
	
	@Test
	void testAddTempoJogoDataConflitante() {
		TempoJogoController tempoJogoController = new TempoJogoController();
		JogoDao jogoDao = new JogoDao();
		jogoDao.salva(new Jogo(70, "Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0, "Descrição"));
		AddTempoJogoForm TempoJogoForm = new AddTempoJogoForm(70,LocalDateTime.now(),LocalDateTime.now().plusHours(2));
		AddTempoJogoForm TempoJogoForm2 = new AddTempoJogoForm(70,LocalDateTime.now().plusHours(1),LocalDateTime.now().plusHours(2));
		tempoJogoController.AddTempoJogoByDate(TempoJogoForm);
		
		try {
			tempoJogoController.AddTempoJogoByDate(TempoJogoForm2);
			fail();
		}catch (JogosEmMesmoHorarioException e) {}
		
	}
	
	
	@Test
	void testAddTempoJogoJogoNaoExiste() {
		TempoJogoController tempoJogoController = new TempoJogoController();
		AddTempoJogoForm TempoJogoForm = new AddTempoJogoForm(70,LocalDateTime.now(),LocalDateTime.now().plusHours(1));
		
		assertEquals(tempoJogoController.AddTempoJogoByDate(TempoJogoForm).getStatusCode().compareTo(HttpStatus.NOT_FOUND),0); 
		
	}

}
