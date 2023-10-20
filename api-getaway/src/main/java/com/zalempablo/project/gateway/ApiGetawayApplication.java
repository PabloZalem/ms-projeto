package com.zalempablo.project.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class ApiGetawayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGetawayApplication.class, args);
	}

}
