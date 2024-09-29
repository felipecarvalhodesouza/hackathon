package br.com.fiap.hackathon.adapters.out.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.hackathon.adapters.out.entity.ConsultaEntity;
import br.com.fiap.hackathon.adapters.out.mapper.ConsultaMapper;
import br.com.fiap.hackathon.application.ports.out.ConsultaRepositoryPort;
import br.com.fiap.hackathon.domain.Consulta;
import br.com.fiap.hackathon.domain.exception.HorarioIndisponivelException;

public class ConsultaRepository implements ConsultaRepositoryPort{

	private final JpaConsultaRepository jpaConsultaRepository;

	@Autowired
	public ConsultaRepository(JpaConsultaRepository jpaConsultaRepository) {
		this.jpaConsultaRepository = jpaConsultaRepository;
	}
	
	@Override
	public Consulta cadastrarConsulta(Consulta consulta) throws HorarioIndisponivelException {
		try {
			ConsultaEntity entity = jpaConsultaRepository.save(ConsultaMapper.toEntity(consulta));
			return ConsultaMapper.toDomain(entity);
		} catch(Exception exception) {
			throw new HorarioIndisponivelException("Horário não está mais disponível para atendimento");
		}
		
	}

	@Override
	public List<Consulta> buscarTodasConsultasPorMedicoEDia(LocalDate dia, Long medicoId) {
		return jpaConsultaRepository.findAllByDiaAndMedico(dia, medicoId).stream().map(ConsultaMapper::toDomain).collect(Collectors.toList());
	}

}
