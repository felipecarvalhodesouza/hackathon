package br.com.fiap.hackathon.adapters.out.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.fiap.hackathon.adapters.out.entity.ConsultaEntity;
import br.com.fiap.hackathon.adapters.out.entity.MedicoEntity;
import br.com.fiap.hackathon.adapters.out.entity.UsuarioEntity;
import br.com.fiap.hackathon.domain.Consulta;
import br.com.fiap.hackathon.domain.Medico;
import br.com.fiap.hackathon.domain.Usuario;
import br.com.fiap.hackathon.main.utils.ConversorLocalDate;

class ConsultaMapperTest {

	@Test
	void testToDomain() {
		MedicoEntity medicoMock = new MedicoEntity();
		medicoMock.setId(1l);
		UsuarioEntity usuarioMock = new UsuarioEntity();
		usuarioMock.setId(2l);

		ConsultaEntity entity = new ConsultaEntity();
		entity.setId(1L);
		entity.setMedico(medicoMock);
		entity.setPaciente(usuarioMock);
		entity.setDia(LocalDate.now());
		entity.setHorario(ConversorLocalDate.getHoraLocalTime("10:00"));

		Consulta consulta = ConsultaMapper.toDomain(entity);

		assertEquals(entity.getId(), consulta.getId());
		assertEquals(medicoMock.getId(), consulta.getMedico().getId());
		assertEquals(usuarioMock.getId(), consulta.getPaciente().getId());
		assertEquals(entity.getDia(), consulta.getDia());
		assertEquals(entity.getHorario(), consulta.getHorario());
	}

	@Test
	void testToEntity() {
		Medico medicoMock = new Medico();
		medicoMock.setId(1l);
		Usuario usuarioMock = new Usuario();
		usuarioMock.setId(2l);

		Consulta consulta = new Consulta();
		consulta.setId(1L);
		consulta.setMedico(medicoMock);
		consulta.setPaciente(usuarioMock);
		consulta.setDia(LocalDate.now());
		consulta.setHorario(ConversorLocalDate.getHoraLocalTime("10:00"));

		ConsultaEntity entity = ConsultaMapper.toEntity(consulta);

		assertEquals(consulta.getId(), entity.getId());
		assertEquals(medicoMock.getId(), entity.getMedico().getId());
		assertEquals(usuarioMock.getId(), entity.getPaciente().getId());
		assertEquals(consulta.getDia(), entity.getDia());
		assertEquals(consulta.getHorario(), entity.getHorario());
	}

}
