package br.com.fiap.hackathon.adapters.out.repository;

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

}
