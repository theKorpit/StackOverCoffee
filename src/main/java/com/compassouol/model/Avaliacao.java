package com.compassouol.model;

import java.io.IOException;

public class Avaliacao {
	
	private String comentario; 
	private double nota; 
	
	public Avaliacao(String comentario, double nota) throws IOException{
		
		try {
			validaComentario(comentario);
			validaNota(nota);
		}
		catch (IOException ex){
			throw ex;
		}
		this.comentario = comentario;
		
		this.nota = nota;
	}

	public String getComentario() {
		return this.comentario;
	}
	
	public double getNota() {
		return this.nota;
	}
	
	public void validaComentario(String comentario) throws IOException {
		comentario = comentario.trim();
		if(comentario.isEmpty())
			throw new IOException("Comentario não pode ser vazio");
	}
	
	private void validaNota(double nota) throws IOException {
		if(nota > 5 || nota < 0)
			throw new IOException("A nota precisa estar entre zero e cinco");
	}
	
	@Override
	public String toString() {
		return ("Comentario: " + this.getComentario() + " Nota: " + this.getNota() + "\n");
	}
}