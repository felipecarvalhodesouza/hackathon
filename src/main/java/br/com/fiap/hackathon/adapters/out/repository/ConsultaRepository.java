package br.com.fiap.hackathon.adapters.out.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.hackathon.adapters.out.entity.ConsultaEntity;
import br.com.fiap.hackathon.adapters.out.mapper.ConsultaMapper;
import br.com.fiap.hackathon.application.ports.out.ConsultaRepositoryPort;
import br.com.fiap.hackathon.application.ports.out.EnvioNotificacaoGateway;
import br.com.fiap.hackathon.application.ports.out.UsuarioRepositoryPort;
import br.com.fiap.hackathon.domain.Consulta;
import br.com.fiap.hackathon.domain.GradeAtendimento;
import br.com.fiap.hackathon.domain.Usuario;
import br.com.fiap.hackathon.domain.exception.HorarioIndisponivelException;

public class ConsultaRepository implements ConsultaRepositoryPort{

	private final JpaConsultaRepository jpaConsultaRepository;
	private final EnvioNotificacaoGateway notificacaoGateway;
	private final UsuarioRepositoryPort usuarioRepository;

	@Autowired
	public ConsultaRepository(JpaConsultaRepository jpaConsultaRepository, EnvioNotificacaoGateway notificacaoGateway, UsuarioRepositoryPort usuarioRepository) {
		this.jpaConsultaRepository = jpaConsultaRepository;
		this.notificacaoGateway = notificacaoGateway;
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	@Transactional
	public Consulta cadastrarConsulta(Consulta consulta) throws HorarioIndisponivelException {
		try {
			ConsultaEntity entity = jpaConsultaRepository.save(ConsultaMapper.toEntity(consulta));
			notificacaoGateway.enviarNotificacao(ConsultaMapper.toDomain(entity));
			return ConsultaMapper.toDomain(entity);
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new HorarioIndisponivelException("Horário não está mais disponível para atendimento");
		}
		
	}

	@Override
	public List<Consulta> buscarTodasConsultasPorMedicoEDia(LocalDate dia, Long medicoId) {
		return jpaConsultaRepository.findAllByDiaAndMedico(dia, medicoId).stream().map(ConsultaMapper::toDomain).toList();
	}

	@Override
	public List<GradeAtendimento> buscarTodasConsultasPorPaciente(String emailPaciente) {
		Usuario paciente = usuarioRepository.findByEmail(emailPaciente).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
		List<ConsultaEntity> consultaList = jpaConsultaRepository.findAllByPacienteId(paciente.getId());
		
		List<GradeAtendimento> atendimentoList = new ArrayList<>();

		for (ConsultaEntity consultaEntity : consultaList) {
			GradeAtendimento atendimento = new GradeAtendimento();
			atendimento.setData(consultaEntity.getDia());
			atendimento.setHorario(consultaEntity.getHorario());
			atendimento.setDescricao("Dr. " + consultaEntity.getMedico().getNome());
			atendimentoList.add(atendimento);
		}

		return atendimentoList;
	}

}
