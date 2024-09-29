package br.com.fiap.hackathon.application.usecases;

import br.com.fiap.hackathon.application.ports.out.AgendaRepositoryPort;
import br.com.fiap.hackathon.domain.Agenda;

public class AtualizarAgendaUseCase {
	
	private final AgendaRepositoryPort repository;

	public AtualizarAgendaUseCase(AgendaRepositoryPort repository) {
		this.repository = repository;
	}

	public void executar(Agenda agenda, String emailMedico) {
		repository.atualizarAgenda(agenda, emailMedico);
	}
}
