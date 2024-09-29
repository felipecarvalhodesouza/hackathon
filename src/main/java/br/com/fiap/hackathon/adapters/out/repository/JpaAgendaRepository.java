package br.com.fiap.hackathon.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.hackathon.adapters.out.entity.AgendaEntity;

public interface JpaAgendaRepository extends JpaRepository<AgendaEntity, Long>{

	AgendaEntity findByMedicoId(Long medicoId);
	AgendaEntity findByMedicoEmail(String email);
}
