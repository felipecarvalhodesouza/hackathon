package br.com.fiap.hackathon.application.usecases;

import java.time.LocalDate;
import java.util.List;

import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.main.utils.ConversorLocalDate;

public class BuscarHorariosDisponiveisUseCase {
	
	private final MedicoRepositoryPort repository;
	
	public BuscarHorariosDisponiveisUseCase(MedicoRepositoryPort repository) {
		this.repository = repository;
	}

	public List<String> buscarHorariosDisponiveis(Long medicoId, LocalDate data){
		return ConversorLocalDate.getHoraFormatada(repository.buscarHorariosDisponiveis(medicoId, data));
	}

}
