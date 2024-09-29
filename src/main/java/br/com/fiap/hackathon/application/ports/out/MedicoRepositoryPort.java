package br.com.fiap.hackathon.application.ports.out;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import br.com.fiap.hackathon.domain.GradeAtendimento;
import br.com.fiap.hackathon.domain.Medico;

public interface MedicoRepositoryPort {

	Optional<Medico> findByEmail(String email);
	Medico buscarPor(Long medicoId);
	Medico cadastrarMedico(Medico medico);
	List<GradeAtendimento> buscarHorariosAtendimento(String nomeUsuario, LocalDate data);
	List<LocalTime> buscarHorariosDisponiveis(Long medicoId, LocalDate data);
	List<Medico> buscarMedicosDisponiveis(LocalDate data);
}
