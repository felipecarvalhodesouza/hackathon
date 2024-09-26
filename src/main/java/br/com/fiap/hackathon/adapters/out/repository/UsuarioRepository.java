package br.com.fiap.hackathon.adapters.out.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.hackathon.adapters.out.entity.UsuarioEntity;
import br.com.fiap.hackathon.adapters.out.mapper.UsuarioMapper;
import br.com.fiap.hackathon.application.ports.out.UsuarioRepositoryPort;
import br.com.fiap.hackathon.domain.Usuario;

public class UsuarioRepository implements UsuarioRepositoryPort{
	
	private final JpaUsuarioRepository jpaUsuarioRepository;
	
	@Autowired
    public UsuarioRepository(JpaUsuarioRepository jpaUsuarioRepository) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
    }

	@Override
	public Optional<Usuario> findByEmail(String email) {
		Usuario usuario = null;
		UsuarioEntity usuarioEntity = jpaUsuarioRepository.findByEmail(email);
		if(usuarioEntity != null) {
			usuario = UsuarioMapper.toDomain(usuarioEntity);
		}
		return Optional.ofNullable(usuario);
	}

}
