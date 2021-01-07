package com.compassouol.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.controller.dto.TempoJogoDto;
import com.compassouol.controller.form.AddTempoJogoForm;
import com.compassouol.dao.JogoDao;
import com.compassouol.model.Jogo;

@RestController
@RequestMapping(value = "/api/tempoJogo")
public class TempoJogoController {

	public JogoDao jogoDao = new JogoDao();
	
	@PostMapping
	public ResponseEntity<?> AddTempoJogoByDate(@RequestBody @Valid AddTempoJogoForm tempoJogo) {
		Jogo jogo = jogoDao.FindById(tempoJogo.getIdJogo());
		
		if(jogo == null) 
			return ResponseEntity.notFound().build();
		
		jogo.adicionaTempoJogo(tempoJogo.getDataInicial(), tempoJogo.getDataFinal());
		
		jogoDao.save(jogo);
		
		TempoJogoDto tempoJogoDto = new TempoJogoDto(tempoJogo.getIdJogo(),tempoJogo.getDataInicial(),tempoJogo.getDataFinal());
		
		return ResponseEntity.ok(tempoJogoDto);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> GetAllTempoJogoByJogo(@PathVariable("id") Integer jogoId){
		Jogo jogo = jogoDao.FindById(jogoId);
		
		if(jogo == null) 
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(TempoJogoDto.convertListToDto(jogo.getTempoJogado(), jogoId));
		
	}
	
//	@GetMapping(value = "/tempoJogado")
//	public Float GetTempoJogado(Integer jogoId) {
//		Jogo jogo = jogoDao.FindById(jogoId);
//		
//		if(jogo == null) 
//			return null;
//		
//		return jogo.tempoTotalJogado();
//		
//	}

//	@GetMapping(value = "/tempoTotalJogado")
//	public Float GetTempoJogado() {
//		
//		Float tempoTotal = 0f;
//		
//		for(Jogo jogo : BibliotecaDao.biblioteca.getJogos())
//			tempoTotal+=jogo.tempoTotalJogado();
//		
//		return tempoTotal;
//		
//	}
	
}
