package br.com.fiap.hackathon.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.hackathon.adapters.out.entity.MedicoEntity;

public interface JpaMedicoRepository extends JpaRepository<MedicoEntity, Long>{

	MedicoEntity findByEmail(String email);

}
