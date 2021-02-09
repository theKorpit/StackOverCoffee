package com.compassouol.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compassouol.exceptions.JogosEmMesmoHorarioException;
import com.compassouol.model.Jogo;
import com.compassouol.model.TempoJogo;
import com.compassouol.repository.JogoRepository;
import com.compassouol.repository.TempoJogoRepository;

@Service
public class TempoJogoService {

	@Autowired
	private TempoJogoRepository tempoJogoRepository;
	@Autowired
	private JogoRepository jogoRepository;
	
	
	public void adicionaTempoJogo(Jogo jogo, LocalDateTime dataInicio, LocalDateTime dataFim) {
		
		TempoJogo tempoJogo = new TempoJogo(dataInicio, dataFim);

		tempoJogoRepository.findAll().forEach(e -> {

			if((tempoJogo.getDataFim().isAfter(e.getDataInicio()) && tempoJogo.getDataFim().isBefore(e.getDataFim())) || 
			   (tempoJogo.getDataInicio().isAfter(e.getDataInicio()) && tempoJogo.getDataInicio().isBefore(e.getDataFim())) ||
			   (tempoJogo.getDataInicio().isEqual(e.getDataInicio())) && tempoJogo.getDataFim().isEqual(e.getDataFim()) ||
			   (tempoJogo.getDataInicio().isBefore(e.getDataInicio()) && tempoJogo.getDataFim().isAfter(e.getDataFim()))) 
				throw new JogosEmMesmoHorarioException(e.getDataInicio(),e.getDataFim(),tempoJogo.getDataInicio(), tempoJogo.getDataFim());
		});
		
		tempoJogo.setJogo(jogo);
		jogo.adicionaTempoJogo(tempoJogo.getDataInicio(), tempoJogo.getDataFim());
		tempoJogoRepository.save(tempoJogo);
	}
	
	public Page<TempoJogo> buscaTodosTempoJogos(Integer jogoId, Pageable paginacao) {
		return tempoJogoRepository.FindByJogo_appIdSteam(jogoId, paginacao);
	}
	
	public Float calculaTempoTotalJogado() {
		Float tempoTotal=0f;
		for(Jogo jogo : jogoRepository.findAll()) {
			tempoTotal+=jogo.tempoTotalJogado();
		}
		return tempoTotal;
	}
}