package br.com.fiap.hackathon.application.usecases;

import java.time.LocalDate;
import java.util.List;

import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.domain.GradeAtendimento;

public class BuscarHorariosAtendimentoUseCase {
	
	private final MedicoRepositoryPort repository;
	
	public BuscarHorariosAtendimentoUseCase(MedicoRepositoryPort repository) {
		this.repository = repository;
	}

	public List<GradeAtendimento> executar(String nomeUsuario, LocalDate data){
		return repository.buscarHorariosAtendimento(nomeUsuario, data);
	}

}
