package com.compassouol.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AvaliacaoForm {
	@Size(max = 255)
	private String comentario;

	@NotNull
	private Integer nota;

}