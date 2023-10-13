package com.zalempablo.project.trabalhador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TrabalhadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhadorApplication.class, args);
	}

}
