package br.com.fiap.hackathon.application.usecases;

import java.time.LocalDate;

import br.com.fiap.hackathon.application.ports.out.ConsultaRepositoryPort;
import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.application.ports.out.UsuarioRepositoryPort;
import br.com.fiap.hackathon.domain.Consulta;
import br.com.fiap.hackathon.domain.Medico;
import br.com.fiap.hackathon.domain.Usuario;
import br.com.fiap.hackathon.domain.exception.HorarioIndisponivelException;
import br.com.fiap.hackathon.main.utils.ConversorLocalDate;

public class CadastrarConsultaUseCase {
	
	private final ConsultaRepositoryPort repository;
	private final UsuarioRepositoryPort usuarioRepository;
	private final MedicoRepositoryPort medicoRepository;

	public CadastrarConsultaUseCase(ConsultaRepositoryPort repository, UsuarioRepositoryPort usuarioRepository, MedicoRepositoryPort medicoRepository) {
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;
		this.medicoRepository = medicoRepository;
	}
	
	public Consulta executar(Long medicoId, String emailPaciente, LocalDate data, String horario) throws HorarioIndisponivelException {
		Usuario paciente = usuarioRepository.findByEmail(emailPaciente).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
		Medico medico = medicoRepository.buscarPor(medicoId);

		Consulta consulta = new Consulta();
		
		consulta.setMedico(medico);
		consulta.setPaciente(paciente);
		consulta.setDia(data);
		consulta.setHorario(ConversorLocalDate.getHoraLocalTime(horario));
		
		consulta = repository.cadastrarConsulta(consulta);
		
		return consulta;
	}
}
