package com.compassouol.services;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.compassouol.dao.JogoDao;
import com.compassouol.exceptions.JogoDuplicadoException;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;

@Service
public class JogoService {

	private JogoDao jogoDao = new JogoDao();

	public boolean verificaId(String dado) {
		try {
			Integer.parseInt(dado);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public Integer converteStringIdParaIntegerId(String dado) {
		return Integer.parseInt(dado);
	}

	public boolean adicionaJogoBiblioteca(String dado) throws IOException, ParseException {

		if (this.verificaId(dado)) {
			Jogo jogo = jogoDao.buscaPorId(this.converteStringIdParaIntegerId(dado));

			if (jogo == null) {
				SteamApiService steam = new SteamApiService(this.converteStringIdParaIntegerId(dado));
				jogo = steam.retornaJogo();
				jogoDao.salva(jogo);
				return true;
			} else
				throw new JogoDuplicadoException("Jogo ja adicionado!", this.converteStringIdParaIntegerId(dado));
		} else {
			Jogo jogo = jogoDao.buscaPorNome(dado);

			if (jogo == null) {
				SteamApiService steam = new SteamApiService(dado);
				jogo = steam.retornaJogo();
				jogoDao.salva(jogo);
				return true;
			} else
				throw new JogoDuplicadoException("Jogo ja adicionado!", dado);
		}
	}

	public Jogo retornaJogoPorId(Integer id) {
		return jogoDao.buscaPorId(id);
	}
}