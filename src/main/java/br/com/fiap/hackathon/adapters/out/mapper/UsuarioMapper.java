package br.com.fiap.hackathon.adapters.out.mapper;

import br.com.fiap.hackathon.adapters.out.entity.UsuarioEntity;
import br.com.fiap.hackathon.domain.Usuario;

public class UsuarioMapper {

	public static Usuario toDomain(UsuarioEntity entity) {
		Usuario usuario = new Usuario();
		usuario.setId(entity.getId());
		usuario.setNome(entity.getNome());
		usuario.setCpf(entity.getCpf());
		usuario.setEmail(entity.getEmail());
		usuario.setSenha(entity.getSenha());
		return usuario;
	}
	
	public static UsuarioEntity toEntity(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        entity.setNome(usuario.getNome());
        entity.setCpf(usuario.getCpf());
        entity.setEmail(usuario.getEmail());
        entity.setSenha(usuario.getSenha());
        return entity;
	}
}
