package br.com.fiap.hackathon.adapters.out.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.hackathon.adapters.out.entity.ConsultaEntity;

public interface JpaConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

	@Query("SELECT c FROM ConsultaEntity c WHERE c.dia = :dia AND c.medico.id = :medicoId")
	List<ConsultaEntity> findAllByDiaAndMedico(@Param("dia") LocalDate dia, @Param("medicoId") Long medicoId);

}
