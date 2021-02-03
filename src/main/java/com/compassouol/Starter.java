package com.compassouol;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2

public class Starter {

	public static void main(String[] args) throws IOException, ParseException {		
		SpringApplication.run(Starter.class, args);
	}
}