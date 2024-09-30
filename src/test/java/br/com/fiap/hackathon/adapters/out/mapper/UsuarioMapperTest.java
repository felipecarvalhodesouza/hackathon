package br.com.fiap.hackathon.adapters.out.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.fiap.hackathon.adapters.out.entity.UsuarioEntity;
import br.com.fiap.hackathon.domain.Usuario;

class UsuarioMapperTest {

	@Test
	public void testToDomain() {
		UsuarioEntity entity = new UsuarioEntity();
		entity.setId(1L);
		entity.setNome("Carlos Silva");
		entity.setCpf("98765432100");
		entity.setEmail("carlos@example.com");
		entity.setSenha("senhaSegura");

		Usuario usuario = UsuarioMapper.toDomain(entity);

		assertEquals(entity.getId(), usuario.getId());
		assertEquals(entity.getNome(), usuario.getNome());
		assertEquals(entity.getCpf(), usuario.getCpf());
		assertEquals(entity.getEmail(), usuario.getEmail());
		assertEquals(entity.getSenha(), usuario.getSenha());
	}

	@Test
	public void testToEntity() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Carlos Silva");
		usuario.setCpf("98765432100");
		usuario.setEmail("carlos@example.com");
		usuario.setSenha("senhaSegura");

		UsuarioEntity entity = UsuarioMapper.toEntity(usuario);

		assertEquals(usuario.getId(), entity.getId());
		assertEquals(usuario.getNome(), entity.getNome());
		assertEquals(usuario.getCpf(), entity.getCpf());
		assertEquals(usuario.getEmail(), entity.getEmail());
		assertEquals(usuario.getSenha(), entity.getSenha());
	}

}
