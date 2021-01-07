package com.compassouol;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.compassouol.services.SteamAPI;

import com.compassouol.dao.BibliotecaDao;
import com.compassouol.model.Jogo;


@SpringBootApplication
public class Starter {


	public static void main(String[] args) throws IOException, ParseException {		
		Jogo jogo = new Jogo("Jogo Foda","Eu Mesmo","Eu mesmo", "20/10/20","Jogo Show", 50.0);
		BibliotecaDao.biblioteca.getJogos().add(jogo);
		
		System.out.println("Adicionando jogo com id: "+ jogo.getCodigoJogo());
		
		SpringApplication.run(Starter.class, args);
	}

}
