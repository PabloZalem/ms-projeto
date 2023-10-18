package com.zalempablo.project.usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zalempablo.project.usuario.entities.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long>{
	Usuarios findByEmail(String email);
}
