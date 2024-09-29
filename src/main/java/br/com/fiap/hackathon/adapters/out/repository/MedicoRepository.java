package br.com.fiap.hackathon.adapters.out.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.hackathon.adapters.out.entity.AgendaEntity;
import br.com.fiap.hackathon.adapters.out.entity.MedicoEntity;
import br.com.fiap.hackathon.adapters.out.mapper.MedicoMapper;
import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.domain.Medico;
import br.com.fiap.hackathon.domain.enums.DiaSemana;
import br.com.fiap.hackathon.domain.enums.PeriodoTrabalho;
import br.com.fiap.hackathon.main.utils.OrdenadorAgenda;

public class MedicoRepository implements MedicoRepositoryPort {

	private final JpaMedicoRepository jpaMedicoRepository;

	@Autowired
	public MedicoRepository(JpaMedicoRepository jpaMedicoRepository) {
		this.jpaMedicoRepository = jpaMedicoRepository;
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
	public List<LocalTime> obterHorariosAtendimento(String emailUsuario, LocalDate data) {
		MedicoEntity medico = jpaMedicoRepository.findByEmail(emailUsuario);
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

		return horarios;
	}

}
