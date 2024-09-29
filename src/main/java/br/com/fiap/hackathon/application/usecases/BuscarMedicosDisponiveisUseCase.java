package br.com.fiap.hackathon.application.usecases;

import java.time.LocalDate;
import java.util.List;

import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.domain.Medico;

public class BuscarMedicosDisponiveisUseCase {
	
	private final MedicoRepositoryPort repository;
	
	public BuscarMedicosDisponiveisUseCase(MedicoRepositoryPort repository) {
		this.repository = repository;
	}

	public List<Medico> executar(LocalDate data){
		return repository.buscarMedicosDisponiveis(data);
	}

}
