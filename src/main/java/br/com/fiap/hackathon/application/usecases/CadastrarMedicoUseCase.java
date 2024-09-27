package br.com.fiap.hackathon.application.usecases;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.domain.Medico;

public class CadastrarMedicoUseCase {
	
	private final MedicoRepositoryPort repository;
	private final PasswordEncoder passwordEncoder;

	public CadastrarMedicoUseCase(MedicoRepositoryPort repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	public void executar(Medico medico) {
		medico.setSenha(passwordEncoder.encode(medico.getSenha()));
		repository.cadastrarMedico(medico);
	}
}
