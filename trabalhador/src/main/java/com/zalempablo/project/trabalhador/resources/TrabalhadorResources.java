package com.zalempablo.project.trabalhador.resources;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zalempablo.project.trabalhador.entities.Trabalhador;
import com.zalempablo.project.trabalhador.repositories.TrabalhadorRepository;

@RefreshScope
@RestController
@RequestMapping(value = "/trabalhadores")
public class TrabalhadorResources {

	private static Logger logger = LoggerFactory.getLogger(TrabalhadorResources.class);
	
	@Value("${test.config}")
	private String testConfig;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private TrabalhadorRepository trabalhadorRepository;

	@GetMapping(value="/configs")
	public ResponseEntity<Void> getConfig() {
		logger.info("CONFIG = " + testConfig);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Trabalhador>> findAll() {
		List<Trabalhador> list = trabalhadorRepository.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Trabalhador> findById(@PathVariable Long id) {
		logger.info("PORT" + environment.getProperty("local.server.port"));
		
		Trabalhador trabalhador = trabalhadorRepository.findById(id).get();
		return ResponseEntity.ok(trabalhador);
	}

	@PostMapping
	public ResponseEntity<Trabalhador> inserir(@RequestBody Trabalhador obj) {
		try {
			Trabalhador novoTrabalhador = trabalhadorRepository.save(obj);
			return ResponseEntity.status(HttpStatus.CREATED).body(novoTrabalhador);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Trabalhador> atualizar(@PathVariable Long id, @RequestBody Trabalhador novoTrabalhador) {
		Optional<Trabalhador> optionalTrabalhador = trabalhadorRepository.findById(id);

		if (optionalTrabalhador.isPresent()) {
			Trabalhador trabalhadorExistente = optionalTrabalhador.get();
			trabalhadorExistente.setNome(novoTrabalhador.getNome());
			trabalhadorExistente.setGanhaPorDia(novoTrabalhador.getGanhaPorDia());
			// Defina outras propriedades que você deseja atualizar

			Trabalhador trabalhadorAtualizado = trabalhadorRepository.save(trabalhadorExistente);
			return ResponseEntity.ok(trabalhadorAtualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		Optional<Trabalhador> optionalTrabalhador = trabalhadorRepository.findById(id);

		if (optionalTrabalhador.isPresent()) {
			trabalhadorRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
