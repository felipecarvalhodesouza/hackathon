package br.com.fiap.hackathon.application.ports.out;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import br.com.fiap.hackathon.domain.Medico;

public interface MedicoRepositoryPort {

	Optional<Medico> findByEmail(String email);
	Medico cadastrarMedico(Medico medico);
	List<LocalTime> buscarHorariosAtendimento(String nomeUsuario, LocalDate data);
	List<LocalTime> buscarHorariosDisponiveis(Long medicoId, LocalDate data);
	List<Medico> buscarMedicosDisponiveis(LocalDate data);
}
