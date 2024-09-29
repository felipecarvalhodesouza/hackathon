package br.com.fiap.hackathon.application.ports.out;

import br.com.fiap.hackathon.domain.Agenda;

public interface AgendaRepositoryPort {

	void atualizarAgenda(Agenda agenda, String emailMedico);
	Agenda buscarAgendaPorEmailMedico(String email);
}
