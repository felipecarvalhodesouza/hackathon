package br.com.fiap.hackathon.application.usecases;

import java.util.List;

import br.com.fiap.hackathon.application.ports.out.ConsultaRepositoryPort;
import br.com.fiap.hackathon.domain.GradeAtendimento;

public class BuscarMinhasConsultasAgendadasUseCase {
	
	private final ConsultaRepositoryPort repository;
	
	public BuscarMinhasConsultasAgendadasUseCase(ConsultaRepositoryPort repository) {
		this.repository = repository;
	}

	public List<GradeAtendimento> executar(String email){
		return repository.buscarTodasConsultasPorPaciente(email);
	}

}
