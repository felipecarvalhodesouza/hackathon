package br.com.fiap.hackathon.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.hackathon.adapters.out.entity.ConsultaEntity;

public interface JpaConsultaRepository extends JpaRepository<ConsultaEntity, Long>{

}
