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
		
		Jogo jogo = new Jogo("Nome","Desenvolvedora","Distribuidora", "20/10/20","Categoria", 50.0);
	
		Integer jogoId = jogo.getCodigoJogo();
		
		jogoDao.save(jogo);
		
		assertNotNull(jogoDao.FindById(jogoId));
	}
	
	@Test
	void testUpdateJogo() {
		JogoDao jogoDao = new JogoDao();
		
		Jogo jogo = new Jogo("Nome","Desenvolvedora","Distribuidora", "20/10/20","Categoria", 50.0);
	
		jogoDao.save(jogo);
		
		Jogo jogo2 = jogoDao.FindById(jogo.getCodigoJogo());
		
		jogo2.setCategoria("CategoriaModificada");
		
		jogoDao.save(jogo2);
		
		assertEquals(jogo2.getCategoria(), "CategoriaModificada");
	}

}
