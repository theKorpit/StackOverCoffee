package com.compassouol.model;

public class Avaliacao {
	
	private String comentario; 
	private double nota; 
	
	public Avaliacao(String comentario, double nota){
		
		this.comentario = comentario;
		
		
		if(validaNota(nota))
			this.nota = nota;
		else 
			System.out.println("deu ruim");
	}

	public String getComentario() {
		return this.comentario;
	}
	
	public double getNota() {
		return this.nota;
	}
	
	public void alteraComentario(String comentario) {
		this.comentario = comentario; 
	}
	
	private boolean validaNota(double nota) {
		if(nota > 5 || nota < 0)
			return false;
		return true;
	}
}