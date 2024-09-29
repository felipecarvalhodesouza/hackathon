package br.com.fiap.hackathon.application.usecases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;

public class ObterHorariosAtendimentoUseCase {
	
	private final MedicoRepositoryPort repository;
	
	public ObterHorariosAtendimentoUseCase(MedicoRepositoryPort repository) {
		this.repository = repository;
	}

	public List<LocalTime> obterHorariosAtendimento(String nomeUsuario, LocalDate data){
		return repository.obterHorariosAtendimento(nomeUsuario, data);
	}

}
