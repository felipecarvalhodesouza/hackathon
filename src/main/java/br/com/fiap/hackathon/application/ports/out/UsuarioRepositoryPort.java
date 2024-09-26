package br.com.fiap.hackathon.application.ports.out;

import java.util.Optional;

import br.com.fiap.hackathon.domain.Usuario;

public interface UsuarioRepositoryPort {

	Optional<Usuario> findByEmail(String email);
}
