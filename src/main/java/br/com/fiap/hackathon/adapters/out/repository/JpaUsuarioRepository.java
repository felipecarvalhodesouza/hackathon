package br.com.fiap.hackathon.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.hackathon.adapters.out.entity.UsuarioEntity;

public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

	UsuarioEntity findByEmail(String email);

}
