package com.zalempablo.project.folhadepagamento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfiguracaoDaAplicacao {
	
	//Padrao Singleton
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
