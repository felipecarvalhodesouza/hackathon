package br.com.fiap.hackathon.application.usecases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;

public class BuscarHorariosAtendimentoUseCase {
	
	private final MedicoRepositoryPort repository;
	
	public BuscarHorariosAtendimentoUseCase(MedicoRepositoryPort repository) {
		this.repository = repository;
	}

	public List<LocalTime> executar(String nomeUsuario, LocalDate data){
		return repository.buscarHorariosAtendimento(nomeUsuario, data);
	}

}
