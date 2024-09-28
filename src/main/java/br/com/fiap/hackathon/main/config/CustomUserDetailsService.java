package br.com.fiap.hackathon.main.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.fiap.hackathon.adapters.out.mapper.MedicoMapper;
import br.com.fiap.hackathon.adapters.out.mapper.UsuarioMapper;
import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.application.ports.out.UsuarioRepositoryPort;
import br.com.fiap.hackathon.domain.Medico;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UsuarioRepositoryPort repository;
	
	@Autowired
	MedicoRepositoryPort medicoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Medico> usuarioMedico = medicoRepository.findByEmail(username);

		if(usuarioMedico.isPresent()) {
			return MedicoMapper.toEntity(usuarioMedico.get());
		}

		return UsuarioMapper.toEntity(repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username)));
	}

}
