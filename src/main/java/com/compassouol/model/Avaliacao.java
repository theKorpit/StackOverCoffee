package com.compassouol.model;

public class Avaliacao {
	
	private String comentario; 
	private double nota; 
	
	public Avaliacao(String comentario, double nota){
		
		validaComentario(comentario);
		validaNota(nota);
		
		this.comentario = comentario;
		
		this.nota = nota;
	}

	public String getComentario() {
		return this.comentario;
	}
	
	public double getNota() {
		return this.nota;
	}
	
	public void validaComentario(String comentario) {
		if(comentario.isEmpty())
			throw new IllegalArgumentException();
	}
	
	private void validaNota(double nota) {
		if(nota > 5 || nota < 0)
			throw new IllegalArgumentException();
	}
}