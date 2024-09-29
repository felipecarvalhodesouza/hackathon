package br.com.fiap.hackathon.adapters.out.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.hackathon.adapters.out.entity.AgendaEntity;
import br.com.fiap.hackathon.adapters.out.entity.MedicoEntity;
import br.com.fiap.hackathon.adapters.out.mapper.MedicoMapper;
import br.com.fiap.hackathon.application.ports.out.ConsultaRepositoryPort;
import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.domain.Consulta;
import br.com.fiap.hackathon.domain.GradeAtendimento;
import br.com.fiap.hackathon.domain.Medico;
import br.com.fiap.hackathon.domain.enums.DiaSemana;
import br.com.fiap.hackathon.domain.enums.PeriodoTrabalho;
import br.com.fiap.hackathon.main.utils.OrdenadorAgenda;

public class MedicoRepository implements MedicoRepositoryPort {

	private final JpaMedicoRepository jpaMedicoRepository;
	private final ConsultaRepositoryPort consultaRepository;

	@Autowired
	public MedicoRepository(JpaMedicoRepository jpaMedicoRepository, ConsultaRepositoryPort consultaRepository) {
		this.jpaMedicoRepository = jpaMedicoRepository;
		this.consultaRepository = consultaRepository;
	}

	@Override
	public Optional<Medico> findByEmail(String email) {
		Medico medico = null;
		MedicoEntity medicoEntity = jpaMedicoRepository.findByEmail(email);
		if (medicoEntity != null) {
			medico = MedicoMapper.toDomain(medicoEntity);
		}
		return Optional.ofNullable(medico);
	}

	@Override
	public Medico cadastrarMedico(Medico medico) {
		MedicoEntity medicoEntity = MedicoMapper.toEntity(medico);
		this.preencherAgendaInicial(medicoEntity);
		MedicoEntity usuarioPersistido = jpaMedicoRepository.save(medicoEntity);
		return MedicoMapper.toDomain(usuarioPersistido);
	}

	private void preencherAgendaInicial(MedicoEntity medicoEntity) {
		AgendaEntity agendaEntity = new AgendaEntity();
		Map<DiaSemana, PeriodoTrabalho> dias = new LinkedHashMap<>();
		dias.put(DiaSemana.SEGUNDA, PeriodoTrabalho.NENHUM);
		dias.put(DiaSemana.TERCA, PeriodoTrabalho.NENHUM);
		dias.put(DiaSemana.QUARTA, PeriodoTrabalho.NENHUM);
		dias.put(DiaSemana.QUINTA, PeriodoTrabalho.NENHUM);
		dias.put(DiaSemana.SEXTA, PeriodoTrabalho.NENHUM);
		dias.put(DiaSemana.SABADO, PeriodoTrabalho.NENHUM);
		dias.put(DiaSemana.DOMINGO, PeriodoTrabalho.NENHUM);
		agendaEntity.setDiasComPeriodos(dias);
		medicoEntity.setAgenda(agendaEntity);
	}

	@Override
	public List<GradeAtendimento> buscarHorariosAtendimento(String emailUsuario, LocalDate data) {
		MedicoEntity medico = jpaMedicoRepository.findByEmail(emailUsuario);
		
		if(medico == null) {
			return null;
		}
		
		DiaSemana diaSemana = DiaSemana.from(data.getDayOfWeek());
		PeriodoTrabalho periodoTrabalho = OrdenadorAgenda.getAgendaOrdenada(medico.getAgenda().getDiasComPeriodos()).get(diaSemana);

		List<LocalTime> horarios = new ArrayList<>();
		if (periodoTrabalho != PeriodoTrabalho.NENHUM) {
			LocalTime inicio = periodoTrabalho == PeriodoTrabalho.MANHA || periodoTrabalho == PeriodoTrabalho.DIA_TODO ? LocalTime.of(8, 0) : LocalTime.of(13, 0);
			LocalTime fim = periodoTrabalho == PeriodoTrabalho.MANHA ? LocalTime.of(13, 00) : LocalTime.of(17, 00);

			while (!inicio.isAfter(fim)) {
				if(inicio.isAfter(LocalTime.of(11, 0)) && inicio.isBefore(LocalTime.of(13, 30))) {
					inicio = inicio.plusMinutes(30);
					continue;
				}
				
				inicio = inicio.plusMinutes(30);
				horarios.add(inicio);
			}
		}
		
		List<GradeAtendimento> gradeAtendimento = new ArrayList<>();
		
		if(!horarios.isEmpty()) {
			List<Consulta> consultasAgendadas = consultaRepository.buscarTodasConsultasPorMedicoEDia(data, medico.getId());
			
			
			for (LocalTime horario : horarios) {
				
				Optional<Consulta> compromisso = consultasAgendadas.stream().filter( consulta -> consulta.getHorario().equals(horario)).findFirst();
				
				GradeAtendimento grade = new GradeAtendimento();
				grade.setHorario(horario);
				grade.setDescricao(compromisso.isPresent() ? compromisso.get().getPaciente().getNome() : "Horário disponível");
				gradeAtendimento.add(grade);
			}
			
		}

		return gradeAtendimento;
	}

	@Override
	public List<LocalTime> buscarHorariosDisponiveis(Long medicoId, LocalDate data) {
		MedicoEntity medicoEntity = jpaMedicoRepository.findById(medicoId).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
		return buscarHorariosAtendimento(medicoEntity.getEmail(), data).stream().map(GradeAtendimento::getHorario).collect(Collectors.toList());
	}

	@Override
	public List<Medico> buscarMedicosDisponiveis(LocalDate data) {
        DiaSemana diaSemanaAtual = DiaSemana.from(data.getDayOfWeek());
        return jpaMedicoRepository.findMedicosComAtendimentoNoDia(diaSemanaAtual).stream().map(MedicoMapper::toDomain).collect(Collectors.toList());
	}

	@Override
	public Medico buscarPor(Long medicoId) {
		return MedicoMapper.toDomain(jpaMedicoRepository.findById(medicoId).orElseThrow( () -> new RuntimeException("Médico não encontrado")));
	}

}
