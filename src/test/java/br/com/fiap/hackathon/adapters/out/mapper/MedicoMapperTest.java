package br.com.fiap.hackathon.adapters.out.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.fiap.hackathon.adapters.out.entity.MedicoEntity;
import br.com.fiap.hackathon.domain.Medico;

class MedicoMapperTest {

	@Test
	public void testToDomain() {
		MedicoEntity entity = new MedicoEntity();
		entity.setId(1L);
		entity.setNome("Dr. João");
		entity.setCpf("12345678901");
		entity.setEmail("joao@example.com");
		entity.setSenha("senhaSegura");
		entity.setCrm("CRM12345");

		Medico medico = MedicoMapper.toDomain(entity);

		assertEquals(entity.getId(), medico.getId());
		assertEquals(entity.getNome(), medico.getNome());
		assertEquals(entity.getCpf(), medico.getCpf());
		assertEquals(entity.getEmail(), medico.getEmail());
		assertEquals(entity.getSenha(), medico.getSenha());
		assertEquals(entity.getCrm(), medico.getCrm());
	}

	@Test
	public void testToEntity() {
		Medico medico = new Medico();
		medico.setId(1L);
		medico.setNome("Dr. João");
		medico.setCpf("12345678901");
		medico.setEmail("joao@example.com");
		medico.setSenha("senhaSegura");
		medico.setCrm("CRM12345");

		MedicoEntity entity = MedicoMapper.toEntity(medico);

		assertEquals(medico.getId(), entity.getId());
		assertEquals(medico.getNome(), entity.getNome());
		assertEquals(medico.getCpf(), entity.getCpf());
		assertEquals(medico.getEmail(), entity.getEmail());
		assertEquals(medico.getSenha(), entity.getSenha());
		assertEquals(medico.getCrm(), entity.getCrm());
	}

}
