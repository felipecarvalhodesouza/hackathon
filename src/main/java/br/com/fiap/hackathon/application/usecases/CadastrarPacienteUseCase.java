package br.com.fiap.hackathon.application.usecases;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.hackathon.application.ports.out.UsuarioRepositoryPort;
import br.com.fiap.hackathon.domain.Usuario;

public class CadastrarPacienteUseCase {
	
	private final UsuarioRepositoryPort repository;
	private final PasswordEncoder passwordEncoder;

	public CadastrarPacienteUseCase(UsuarioRepositoryPort repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	public void executar(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		repository.cadastrarUsuario(usuario);
	}
}
