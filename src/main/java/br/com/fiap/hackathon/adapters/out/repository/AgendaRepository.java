package br.com.fiap.hackathon.adapters.out.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.hackathon.adapters.out.entity.AgendaEntity;
import br.com.fiap.hackathon.adapters.out.mapper.AgendaMapper;
import br.com.fiap.hackathon.application.ports.out.AgendaRepositoryPort;
import br.com.fiap.hackathon.domain.Agenda;
import br.com.fiap.hackathon.domain.enums.DiaSemana;
import br.com.fiap.hackathon.domain.enums.PeriodoTrabalho;

public class AgendaRepository implements AgendaRepositoryPort {

	private final JpaAgendaRepository jpaAgendaRepository;

	@Autowired
	public AgendaRepository(JpaAgendaRepository jpaAgendaRepository) {
		this.jpaAgendaRepository = jpaAgendaRepository;
	}

	@Override
	public void atualizarAgenda(Agenda agenda, String emailMedico) {
		AgendaEntity agendaEntity = jpaAgendaRepository.findByMedicoEmail(emailMedico);
		
        Map<DiaSemana, PeriodoTrabalho> diasTrabalho = agenda.getDiasComPeriodos();
        for (Map.Entry<DiaSemana, PeriodoTrabalho> entry : diasTrabalho.entrySet()) {
        	DiaSemana dia = entry.getKey();
            PeriodoTrabalho periodoTrabalho = entry.getValue();
            if(periodoTrabalho == null) {
            	periodoTrabalho = PeriodoTrabalho.NENHUM;
            }

            agendaEntity.getDiasComPeriodos().put(dia, periodoTrabalho);
        }
        
        agendaEntity.setDiasComPeriodos(agendaEntity.getDiasComPeriodos());

        jpaAgendaRepository.save(agendaEntity);
	}

	@Override
	public Agenda buscarAgendaPorEmailMedico(String email) {
		return AgendaMapper.toDomain(jpaAgendaRepository.findByMedicoEmail(email));
	}
}
