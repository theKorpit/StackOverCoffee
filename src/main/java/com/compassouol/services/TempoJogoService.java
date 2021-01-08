package com.compassouol.services;

import org.springframework.stereotype.Service;

import com.compassouol.dao.JogoDao;
import com.compassouol.dao.TempoJogoDao;
import com.compassouol.exceptions.JogosEmMesmoHorarioException;
import com.compassouol.model.Jogo;
import com.compassouol.model.TempoJogo;
@Service
public class TempoJogoService {

	private JogoDao jogoDao = new JogoDao();
	
	private TempoJogoDao tempoJogoDao = new TempoJogoDao();
	
	
	public void addTempoJogo(Jogo jogo, TempoJogo tempoJogo) {
		
		tempoJogoDao.findAll().forEach(e -> {

			if((tempoJogo.getDataFim().isAfter(e.getDataInicio()) && tempoJogo.getDataFim().isBefore(e.getDataFim())) || 
			   (tempoJogo.getDataInicio().isAfter(e.getDataInicio()) && tempoJogo.getDataInicio().isBefore(e.getDataFim()))) 
				throw new JogosEmMesmoHorarioException("Jogos não podem ser jogados no mesmo horario",e.getDataInicio(),e.getDataFim(),tempoJogo.getDataInicio(), tempoJogo.getDataFim());
		});
		
		jogo.adicionaTempoJogo(tempoJogo.getDataInicio(), tempoJogo.getDataFim());
	}
	
	public Jogo getJogoById(Integer id) {//Transferir este metodo para a classe JogoService
		return jogoDao.FindById(id);
	}
	
}
