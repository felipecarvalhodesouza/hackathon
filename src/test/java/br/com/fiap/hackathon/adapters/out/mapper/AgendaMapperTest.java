package br.com.fiap.hackathon.adapters.out.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import br.com.fiap.hackathon.adapters.out.entity.AgendaEntity;
import br.com.fiap.hackathon.adapters.out.entity.MedicoEntity;
import br.com.fiap.hackathon.domain.Agenda;
import br.com.fiap.hackathon.domain.enums.DiaSemana;
import br.com.fiap.hackathon.domain.enums.PeriodoTrabalho;

public class AgendaMapperTest {

	@Test
	public void testToDomain() {
		AgendaEntity entity = new AgendaEntity();
		entity.setId(1L);
		MedicoEntity medicoEntity = new MedicoEntity();
		medicoEntity.setId(2L);
		entity.setMedico(medicoEntity);

		Map<DiaSemana, PeriodoTrabalho> diasComPeriodos = new HashMap<>();
		diasComPeriodos.put(DiaSemana.SEGUNDA, PeriodoTrabalho.MANHA);
		entity.setDiasComPeriodos(diasComPeriodos);

		Agenda agenda = AgendaMapper.toDomain(entity);

		assertEquals(entity.getId(), agenda.getId());
		assertEquals(2L, agenda.getMedicoId());
		assertEquals(diasComPeriodos, agenda.getDiasComPeriodos());
	}

	@Test
	public void testToEntity() {
		Agenda agenda = new Agenda();
		agenda.setId(1L);
		agenda.setMedicoId(2L);

		Map<DiaSemana, PeriodoTrabalho> diasComPeriodos = new HashMap<>();
		diasComPeriodos.put(DiaSemana.SEGUNDA, PeriodoTrabalho.MANHA);
		agenda.setDiasComPeriodos(diasComPeriodos);

		AgendaEntity entity = AgendaMapper.toEntity(agenda);

		assertEquals(agenda.getId(), entity.getId());
		assertEquals(2L, entity.getMedico().getId());
		assertEquals(diasComPeriodos, entity.getDiasComPeriodos());
	}

}
