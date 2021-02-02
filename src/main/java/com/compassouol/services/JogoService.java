package com.compassouol.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.compassouol.dto.entrada.JogoDtoEntrada;
import com.compassouol.exceptions.JogoDuplicadoException;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.repository.JogoRepository;

@Service
public class JogoService {

	@Autowired
	private JogoRepository jogoRepository;

	public Jogo adicionaJogoBiblioteca(JogoDtoEntrada jogoDtoEntrada) throws IOException, ParseException {

		if (jogoDtoEntrada.getIdSteam() != null) {
			
			Optional<Jogo> jogoOptional = jogoRepository.findById(jogoDtoEntrada.getIdSteam());
			Jogo jogo = jogoOptional.isPresent() ? jogoOptional.get() : null;

			if (jogo == null) {
				SteamApiService steam = new SteamApiService(jogoDtoEntrada.getIdSteam());
				jogo = steam.retornaJogo();
				jogoRepository.save(jogo);
				return jogo;
			} else
				throw new JogoDuplicadoException("Jogo ja adicionado!", jogoDtoEntrada.getIdSteam());
		} else {
			Jogo jogo = jogoRepository.findByNomeJogo(jogoDtoEntrada.getNomeJogo());

			if (jogo == null) {
				SteamApiService steam = new SteamApiService(jogoDtoEntrada.getNomeJogo());
				jogo = steam.retornaJogo();
				jogoRepository.save(jogo);
				return jogo;
			} else
				throw new JogoDuplicadoException("Jogo ja adicionado!", jogoDtoEntrada.getNomeJogo());
		}

	}

	public Jogo retornaJogoPorId(Integer idSteam) {
		Optional<Jogo> jogoOptional = jogoRepository.findById(idSteam);
		if(jogoOptional.isPresent())
			return jogoOptional.get();
		else {
			return null;
		}
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