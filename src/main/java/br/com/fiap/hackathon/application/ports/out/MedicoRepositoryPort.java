package br.com.fiap.hackathon.application.ports.out;

import java.util.Optional;

import br.com.fiap.hackathon.domain.Medico;

public interface MedicoRepositoryPort {

	Optional<Medico> findByEmail(String email);
	Medico cadastrarMedico(Medico medico);
}
