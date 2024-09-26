package br.com.fiap.hackathon.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.hackathon.adapters.out.repository.JpaUsuarioRepository;
import br.com.fiap.hackathon.adapters.out.repository.UsuarioRepository;
import br.com.fiap.hackathon.application.ports.out.UsuarioRepositoryPort;

@Configuration
public class UsuarioConfig {

	@Bean
	UsuarioRepositoryPort createUsuarioRepositoryPort(JpaUsuarioRepository jpaUsuarioRepository) {
		return new UsuarioRepository(jpaUsuarioRepository);
	}
}
