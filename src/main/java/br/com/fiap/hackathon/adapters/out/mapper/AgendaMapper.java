package br.com.fiap.hackathon.adapters.out.mapper;

import br.com.fiap.hackathon.adapters.out.entity.AgendaEntity;
import br.com.fiap.hackathon.adapters.out.entity.MedicoEntity;
import br.com.fiap.hackathon.domain.Agenda;

public class AgendaMapper {
	
	private AgendaMapper() {}

	public static Agenda toDomain(AgendaEntity entity) {
		Agenda agenda = new Agenda();
		agenda.setDiasComPeriodos(entity.getDiasComPeriodos());
		agenda.setId(entity.getId());
		agenda.setMedicoId(entity.getMedico().getId());
		return agenda;
	}
	
	public static AgendaEntity toEntity(Agenda agenda) {
		AgendaEntity entity = new AgendaEntity();
		entity.setDiasComPeriodos(agenda.getDiasComPeriodos());
		entity.setId(agenda.getId());
		MedicoEntity medicoEntity = new MedicoEntity();
		medicoEntity.setId(agenda.getMedicoId());
		entity.setMedico(medicoEntity);
		return entity;
	}
}
