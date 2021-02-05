package com.compassouol.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ErroValidacaoCampoDto {

	private String campo;
	
	private String erro;
}