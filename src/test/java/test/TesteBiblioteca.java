package test;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.compassouol.model.Jogo;
import com.compassouol.services.Biblioteca;

public class TesteBiblioteca {
	@Test
	public void testBuscaPorId() throws IOException {
		Biblioteca bb = new Biblioteca();
		bb.adicionaJogoLista("Nome Jogo","Desenvolvedora", "Distribuidora","26/07/1998", "Categoria", 100.0);
		Jogo jogo2 = bb.buscaPorId(1);
		Assertions.assertEquals(jogo2.getNomeJogo(), "Nome Jogo");
	}
}
