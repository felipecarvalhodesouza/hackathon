package br.com.fiap.hackathon.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.fiap.hackathon.adapters.out.mapper.UsuarioMapper;
import br.com.fiap.hackathon.application.ports.out.UsuarioRepositoryPort;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UsuarioRepositoryPort repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return UsuarioMapper.toEntity(repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username)));
	}

}
