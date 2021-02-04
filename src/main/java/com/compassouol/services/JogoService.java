package com.compassouol.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compassouol.exceptions.JogoDuplicadoException;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.repository.JogoRepository;
import com.compassouol.repository.TempoJogoRepository;

@Service
public class JogoService {

	@Autowired
	private JogoRepository jogoRepository;

	@Autowired
	private TempoJogoRepository tempoJogoRep;
	@Autowired
	private SteamApiService steamAPI;

	public Jogo adicionaJogoBiblioteca(Integer idSteam, String nomeJogo) throws IOException, ParseException {

		if (idSteam != null) {
			Optional<Jogo> jogoOptional = jogoRepository.findById(idSteam);
			Jogo jogo = jogoOptional.isPresent() ? jogoOptional.get() : null;

			if (jogo == null) {
				jogo = steamAPI.retornaJogo(idSteam, nomeJogo);
				jogoRepository.save(jogo);
				return jogo;
			}
		} else {
			Jogo jogo = jogoRepository.findByNomeJogo(nomeJogo);

			if (jogo == null) {
				jogo = steamAPI.retornaJogo(idSteam, nomeJogo);
				jogoRepository.save(jogo);
				return jogo;

			}

		}

		throw new JogoDuplicadoException(nomeJogo);
	}

	public Jogo retornaJogoPorId(Integer idSteam) {
		Optional<Jogo> jogoOptional = jogoRepository.findById(idSteam);
		if (jogoOptional.isPresent())
			return jogoOptional.get();
		else {
			return null;
		}
	}

	public List<Jogo> retornaJogos() {
		return jogoRepository.findAll();

	}

	public Page<Jogo> retornaJogoPorCategoria(String cat, Pageable paginacao) {
		return jogoRepository.findByCategoria(cat, paginacao);

	}

	public Page<Jogo> retornaJogos(Pageable paginacao) {
		return jogoRepository.findAll(paginacao);

	}

	public void deletaJogo(Integer id) {
		try {
			tempoJogoRep.deleteByJogo_appIdSteam(id);
			jogoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException erro) {
			throw new JogoInvalidoException("Jogo nao encontrado!");
		}
	}

}