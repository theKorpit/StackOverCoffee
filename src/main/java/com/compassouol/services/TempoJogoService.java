package com.compassouol.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compassouol.exceptions.JogosEmMesmoHorarioException;
import com.compassouol.model.Jogo;
import com.compassouol.model.TempoJogo;
import com.compassouol.repository.TempoJogoRepository;

@Service
public class TempoJogoService {

	@Autowired
	private TempoJogoRepository tempoJogoRepository;
	
	
	public void adicionaTempoJogo(Jogo jogo, LocalDateTime dataInicio, LocalDateTime dataFim) {
		
		TempoJogo tempoJogo = new TempoJogo(dataInicio, dataFim);

		tempoJogoRepository.findAll().forEach(e -> {

			if((tempoJogo.getDataFim().isAfter(e.getDataInicio()) && tempoJogo.getDataFim().isBefore(e.getDataFim())) || 
			   (tempoJogo.getDataInicio().isAfter(e.getDataInicio()) && tempoJogo.getDataInicio().isBefore(e.getDataFim())) ||
			   (tempoJogo.getDataInicio().isEqual(e.getDataInicio())) && tempoJogo.getDataFim().isEqual(e.getDataFim()) ||
			   (tempoJogo.getDataInicio().isBefore(e.getDataInicio()) && tempoJogo.getDataFim().isAfter(e.getDataFim()))) 
				throw new JogosEmMesmoHorarioException("Jogos não podem ser jogados no mesmo horario",e.getDataInicio(),e.getDataFim(),tempoJogo.getDataInicio(), tempoJogo.getDataFim());
		});
		
		tempoJogo.setJogo(jogo);
		
		jogo.adicionaTempoJogo(tempoJogo.getDataInicio(), tempoJogo.getDataFim());
		
		tempoJogoRepository.save(tempoJogo);
		
	}
}