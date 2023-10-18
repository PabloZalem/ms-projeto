package com.zalempablo.project.usuario.resources;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zalempablo.project.usuario.entities.Usuarios;
import com.zalempablo.project.usuario.repositories.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResources {

	private static Logger logger = LoggerFactory.getLogger(UsuarioResources.class);

	@Autowired
	private Environment environment;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public ResponseEntity<List<Usuarios>> findAll() {
		List<Usuarios> list = usuarioRepository.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuarios> findById(@PathVariable Long id) {
		logger.info("PORT" + environment.getProperty("local.server.port"));

		Usuarios usuarios = usuarioRepository.findById(id).get();
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping(value = "/pesquisa")
	public ResponseEntity<Usuarios> findByEmail(@RequestParam String email) {
		Usuarios usuarios = usuarioRepository.findByEmail(email);
		return ResponseEntity.ok(usuarios);
	}
	
	@PostMapping
	public ResponseEntity<Usuarios> inserir(@RequestBody Usuarios obj) {
		try {
			Usuarios novoUsuario = usuarioRepository.save(obj);
			return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuarios> atualizar(@PathVariable Long id, @RequestBody Usuarios novoUsuario) {
		Optional<Usuarios> optionalUsuario = usuarioRepository.findById(id);

		if (optionalUsuario.isPresent()) {
			Usuarios usuarioExistente = optionalUsuario.get();
			usuarioExistente.setNome(novoUsuario.getNome());
			// Defina outras propriedades que você deseja atualizar

			Usuarios trabalhadorAtualizado = usuarioRepository.save(usuarioExistente);
			return ResponseEntity.ok(trabalhadorAtualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		Optional<Usuarios> optionalTrabalhador = usuarioRepository.findById(id);

		if (optionalTrabalhador.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
