package com.compassouol.services;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compassouol.exceptions.AvaliacaoDuplicadaException;
import com.compassouol.exceptions.EntradaInvalidaException;
import com.compassouol.exceptions.JogoDuplicadoException;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Avaliacao;
import com.compassouol.model.Jogo;
import com.compassouol.repository.AvaliacaoRepository;
import com.compassouol.repository.JogoRepository;
import com.compassouol.repository.TempoJogoRepository;

@Service
public class JogoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	@Autowired
	private JogoRepository jogoRepository;
	@Autowired
	private TempoJogoRepository tempoJogoRepository;
	@Autowired
	private SteamApiService steamAPI;

	public Jogo adicionaJogoBiblioteca(Integer idSteam, String nomeJogo) throws IOException, ParseException {
		if (idSteam != null) {
			if (jogoRepository.findByAppIdSteam(idSteam) != null)
				throw new JogoDuplicadoException(idSteam);
		} else {
			if (jogoRepository.findByNomeJogo(nomeJogo) != null)
				throw new JogoDuplicadoException(nomeJogo);
		}
		Jogo jogo = steamAPI.retornaJogo(idSteam, nomeJogo);
		jogoRepository.save(jogo);
		return jogo;
	}

	public Jogo retornaJogoPorId(Integer idSteam) {
		return jogoRepository.findByAppIdSteam(idSteam);
	}

	public Page<Jogo> retornaJogoPorCategoria(String cat, Pageable paginacao) {
		return jogoRepository.findByCategoria(cat, paginacao);
	}

	public Page<Jogo> retornaJogos(Pageable paginacao) {
		return jogoRepository.findAll(paginacao);
	}

	public void deletaJogo(Integer id) {
		try {
			avaliacaoRepository.deleteByJogo_appIdSteam(id);
			tempoJogoRepository.deleteByJogo_appIdSteam(id);
			jogoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException erro) {
			throw new JogoInvalidoException(id);
		}
	}

	public void adicionarAvaliacao(Integer id, String comentario, Integer nota) {
		Jogo jogo = jogoRepository.findByAppIdSteam(id);
		if (jogo == null)
			throw new JogoInvalidoException(id);
		if(jogo.getAvaliacao() != null) 
			throw new AvaliacaoDuplicadaException();
		
		Avaliacao avaliacao = new Avaliacao(nota, comentario, jogo);
		jogo.setAvaliacao(avaliacao);
		jogoRepository.save(jogo); 
	}
	
	public void alteraAvaliacao(Integer id, String comentario, Integer nota) {
		Jogo jogo = jogoRepository.findByAppIdSteam(id);
		if (jogo == null)
			throw new JogoInvalidoException(id);
		if(jogo.getAvaliacao() !=null) {
			Avaliacao avaliacao = jogo.getAvaliacao();
			avaliacao.setComentario(comentario);
			avaliacao.setNota(nota);
			jogoRepository.save(jogo);
		}else
			throw new EntradaInvalidaException("Nao existe nenhum comentario para atualizar");
	}
}