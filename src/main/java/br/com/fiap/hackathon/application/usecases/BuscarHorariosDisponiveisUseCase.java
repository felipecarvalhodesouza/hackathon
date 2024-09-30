package br.com.fiap.hackathon.application.usecases;

import java.time.LocalDate;
import java.util.List;

import br.com.fiap.hackathon.application.ports.out.ConsultaRepositoryPort;
import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.domain.Consulta;
import br.com.fiap.hackathon.main.utils.ConversorLocalDate;

public class BuscarHorariosDisponiveisUseCase {
	
	private final MedicoRepositoryPort repository;
	private final ConsultaRepositoryPort consultaRepository;
	
	public BuscarHorariosDisponiveisUseCase(MedicoRepositoryPort repository, ConsultaRepositoryPort consultaRepository) {
		this.repository = repository;
		this.consultaRepository = consultaRepository;
	}

	public List<String> buscarHorariosDisponiveis(Long medicoId, LocalDate data){
		List<String> horaFormatada = ConversorLocalDate.getHoraFormatada(repository.buscarHorariosDisponiveis(medicoId, data));
		
		List<Consulta> buscarTodasConsultasPorMedicoEDia = consultaRepository.buscarTodasConsultasPorMedicoEDia(data, medicoId);
		
		for (Consulta consulta : buscarTodasConsultasPorMedicoEDia) {
			horaFormatada = horaFormatada.stream().filter(hora -> !ConversorLocalDate.getHoraFormatada(consulta.getHorario()).equals(hora)).toList();
		}
		
		return horaFormatada;
	}

}
