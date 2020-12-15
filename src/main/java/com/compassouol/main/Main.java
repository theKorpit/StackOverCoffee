package com.compassouol.main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import com.compassouol.model.Jogo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nDigite o codigo do jogo :");
		int codigoJogo=sc.nextInt();
		System.out.println("\nDigite o nome do jogo :");
		String nomeJogo= sc.nextLine();
		nomeJogo= sc.nextLine();
		System.out.println("\nDigite o nome do desenvolvedor :");
		String desenvolvedor= sc.nextLine();
		System.out.println("\nDigite o nome da distribuidora :");
		String distribuidora=sc.nextLine();
		System.out.println("\nDigite a data do lancamento : DD/MM/YYYY");
		String dataLancamento= sc.nextLine();
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //CRIAR METODO UTIL
		LocalDate dl = LocalDate.parse(dataLancamento,formato);
		
			

		System.out.println("\nDigite a categoria :");
		String categoria = sc.nextLine();
		System.out.println("\nDigite o valor : ");
		double valorDeVenda = sc.nextDouble();
		
		
		try {
			Jogo j = new Jogo(codigoJogo, nomeJogo,desenvolvedor,distribuidora,dataLancamento,categoria,valorDeVenda);
			j.adicionaAvaliacaoJogo(5, "Rafao homao");
			j.adicionaAvaliacaoJogo(0, "Diogo me xingou");
			System.out.println(j);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
