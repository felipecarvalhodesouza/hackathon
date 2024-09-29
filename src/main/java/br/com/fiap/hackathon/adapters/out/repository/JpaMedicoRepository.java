package br.com.fiap.hackathon.adapters.out.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.hackathon.adapters.out.entity.MedicoEntity;
import br.com.fiap.hackathon.domain.enums.DiaSemana;

public interface JpaMedicoRepository extends JpaRepository<MedicoEntity, Long>{

	MedicoEntity findByEmail(String email);

    @Query("SELECT m FROM MedicoEntity m " +
            "JOIN m.agenda a " +
            "JOIN a.diasComPeriodos d " +
            "WHERE KEY(d) = :diaSemanaAtual AND d <> 'NENHUM'")
     List<MedicoEntity> findMedicosComAtendimentoNoDia(@Param("diaSemanaAtual") DiaSemana diaSemanaAtual);

}
