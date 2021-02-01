package com.compassouol.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.compassouol.exceptions.JogoDuplicadoException;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.repository.JogoRepository;

@Service
public class JogoService {

	@Autowired
	private JogoRepository jogoRepository;

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
			Optional<Jogo> jogoOptional = jogoRepository.findById(this.converteStringIdParaIntegerId(dado));
			Jogo jogo = jogoOptional.isPresent() ? jogoOptional.get() : null;

			if (jogo == null) {
				SteamApiService steam = new SteamApiService(this.converteStringIdParaIntegerId(dado));
				jogo = steam.retornaJogo();
				jogoRepository.save(jogo);
				return true;
			} else
				throw new JogoDuplicadoException("Jogo ja adicionado!", this.converteStringIdParaIntegerId(dado));
		} else {
			Jogo jogo = jogoRepository.findByNomeJogo(dado);

			if (jogo == null) {
				SteamApiService steam = new SteamApiService(dado);
				jogo = steam.retornaJogo();
				jogoRepository.save(jogo);
				return true;
			} else
				throw new JogoDuplicadoException("Jogo ja adicionado!", dado);
		}

	}

	public Jogo retornaJogoPorId(Integer id) {
		Optional<Jogo> jogoOptional = jogoRepository.findById(id);
		return jogoOptional.isPresent() ? jogoOptional.get() : null;
	}

	public List<Jogo> retornaJogos() {
		return jogoRepository.findAll();
	}

	public void deletaJogo(Integer id) {
		try {
			jogoRepository.deleteById(id);
		}catch(EmptyResultDataAccessException erro) {
			throw new JogoInvalidoException("Jogo nao encontrado!");
		}
	}

}