package com.compassouol.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.controller.dto.JogoDetalhadoDto;
import com.compassouol.controller.dto.JogoDto;
import com.compassouol.controller.dto.TempoJogoDto;
import com.compassouol.controller.form.JogoForm;
import com.compassouol.controller.form.TempoJogoForm;
import com.compassouol.exceptions.JogoInvalidoException;
import com.compassouol.model.Jogo;
import com.compassouol.services.JogoService;
import com.compassouol.services.TempoJogoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiOperation(value = "Lista todas as Avaliações", notes = "Lista todas as Avaliações", response = JogoDto.class, responseContainer = "List")

@RestController
@RequestMapping(value = "/jogo")
public class JogoController {

	@Autowired
	private TempoJogoService tempoJogoService;

	@Autowired
	public JogoService jogoService;

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Jogo adicionado com sucesso"),
			@ApiResponse(code = 400, message = "Entrada invalida"),
			@ApiResponse(code = 404, message = "Jogo não encontrado na Steam") })

	@PostMapping
	public ResponseEntity<JogoDetalhadoDto> adicionaJogo(@RequestBody JogoForm jogoForm)
			throws IOException, ParseException {
		jogoForm.aplicaValidacoes();
		JogoDetalhadoDto jogoDtoSaida = new JogoDetalhadoDto(jogoService.adicionaJogoBiblioteca(jogoForm.getIdSteam(),jogoForm.getNomeJogo()));

		return ResponseEntity.status(201).body(jogoDtoSaida);
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Jogos encontrados com sucesso"),
			@ApiResponse(code = 400, message = "Entrada invalida"),
			@ApiResponse(code = 404, message = "Biblioteca Vazia") })

	@GetMapping
	public ResponseEntity<Page<JogoDto>> buscaTodosJogos(@PageableDefault(size = 10) Pageable paginacao) {	
		return ResponseEntity.ok(new JogoDto().retornaListaJogos(jogoService.retornaJogos(paginacao)));
	}

	@GetMapping("/categoria")
	public ResponseEntity<Page<JogoDto>> buscaJogoPorCategoria(@PageableDefault(size = 10) Pageable paginacao,@RequestParam String categoria) {
		return ResponseEntity.ok(new JogoDto().retornaListaJogos(jogoService.retornaJogoPorCategoria(categoria,paginacao)));
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Jogo encontrado com sucesso"),
			@ApiResponse(code = 400, message = "Entrada invalida"),
			@ApiResponse(code = 404, message = "Jogo não encontrado na biblioteca") })

	@GetMapping("/{id}")
	public ResponseEntity<JogoDetalhadoDto> buscaJogoPorId(@PathVariable("id") Integer jogoId) {
		Jogo jogo = jogoService.retornaJogoPorId(jogoId);
		if (jogo != null)
			return ResponseEntity.ok(new JogoDetalhadoDto(jogo));
		else
			throw new JogoInvalidoException("Jogo nao encontrado!");

	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Jogo removido com sucesso"),
			@ApiResponse(code = 400, message = "Entrada invalida"),
			@ApiResponse(code = 404, message = "Jogo não encontrado na Biblioteca") })

	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeJogo(@PathVariable("id") Integer jogoId) {
		jogoService.deletaJogo(jogoId);
		return ResponseEntity.ok("Jogo deletado");
	}

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Adicionado tempo de jogo com sucesso"),
			@ApiResponse(code = 400, message = "Entrada invalida"),
			@ApiResponse(code = 404, message = "Jogo não encontrado na Steam") })

	@PostMapping("/{id}/jogar")
	public ResponseEntity<TempoJogoDto> AddTempoJogoByDate(@PathVariable("id") Integer jogoId, @Valid @RequestBody TempoJogoForm tempoJogoForm) {

		Jogo jogo = jogoService.retornaJogoPorId(jogoId);

		if (jogo == null)
			throw new JogoInvalidoException("Jogo nao encontrado!");

		tempoJogoService.adicionaTempoJogo(jogo, tempoJogoForm.getDataInicial(), tempoJogoForm.getDataFinal());

		TempoJogoDto tempoJogoDto = new TempoJogoDto(jogo.getAppIdSteam(), tempoJogoForm.getDataInicial(), tempoJogoForm.getDataFinal());

		return ResponseEntity.status(201).body(tempoJogoDto);

	}
}