package com.example.stage_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class StageBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(StageBackendApplication.class, args);
		System.out.println("Bonjour !"); // Affiche le message "Bonjour !" dans la console

	}

}
