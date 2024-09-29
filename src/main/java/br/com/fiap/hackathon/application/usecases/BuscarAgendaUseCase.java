package br.com.fiap.hackathon.application.usecases;

import br.com.fiap.hackathon.application.ports.out.AgendaRepositoryPort;
import br.com.fiap.hackathon.domain.Agenda;

public class BuscarAgendaUseCase {
	
	private final AgendaRepositoryPort repository;

	public BuscarAgendaUseCase(AgendaRepositoryPort repository) {
		this.repository = repository;
	}

	public Agenda executar(String email) {
		return repository.buscarAgendaPorEmailMedico(email);
	}
}
