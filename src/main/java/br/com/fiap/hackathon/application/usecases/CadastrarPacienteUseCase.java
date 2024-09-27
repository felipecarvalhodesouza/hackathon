package br.com.fiap.hackathon.application.usecases;

import br.com.fiap.hackathon.application.ports.out.UsuarioRepositoryPort;
import br.com.fiap.hackathon.domain.Usuario;

public class CadastrarPacienteUseCase {
	
	private final UsuarioRepositoryPort repository;

	public CadastrarPacienteUseCase(UsuarioRepositoryPort repository) {
		this.repository = repository;
	}

	public void executar(Usuario usuario) {
		repository.cadastrarUsuario(usuario);
	}
}
