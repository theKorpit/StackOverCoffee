package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.compassouol.dao.JogoDao;
import com.compassouol.model.Jogo;

class TesteJogoDao {

	@Test
	void testAddNewJogo() {
		JogoDao jogoDao = new JogoDao();
		
		Jogo jogo = new Jogo(10, "Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0, "Descrição");
	
		Integer jogoId = jogo.getAppIdSteam();
		
		jogoDao.salva(jogo);
		
		assertNotNull(jogoDao.buscaPorId(jogoId));
	}
	
	@Test
	void testBuscaPorId() {
		JogoDao jogoDao = new JogoDao();
		
		Jogo jogo = new Jogo(10, "Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0, "Descrição");
		
		jogoDao.salva(jogo);
		
		jogo = jogoDao.buscaPorId(10);
		
		assertEquals(jogo.getAppIdSteam(), 10);
	}
	
}
