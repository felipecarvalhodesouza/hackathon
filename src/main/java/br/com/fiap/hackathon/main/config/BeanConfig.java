package br.com.fiap.hackathon.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.hackathon.adapters.out.repository.AgendaRepository;
import br.com.fiap.hackathon.adapters.out.repository.JpaAgendaRepository;
import br.com.fiap.hackathon.adapters.out.repository.JpaMedicoRepository;
import br.com.fiap.hackathon.adapters.out.repository.JpaUsuarioRepository;
import br.com.fiap.hackathon.adapters.out.repository.MedicoRepository;
import br.com.fiap.hackathon.adapters.out.repository.UsuarioRepository;
import br.com.fiap.hackathon.application.ports.out.AgendaRepositoryPort;
import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.application.ports.out.UsuarioRepositoryPort;
import br.com.fiap.hackathon.application.usecases.AtualizarAgendaUseCase;
import br.com.fiap.hackathon.application.usecases.BuscarAgendaUseCase;
import br.com.fiap.hackathon.application.usecases.BuscarHorariosAtendimentoUseCase;
import br.com.fiap.hackathon.application.usecases.BuscarHorariosDisponiveisUseCase;
import br.com.fiap.hackathon.application.usecases.BuscarMedicosDisponiveisUseCase;
import br.com.fiap.hackathon.application.usecases.CadastrarMedicoUseCase;
import br.com.fiap.hackathon.application.usecases.CadastrarPacienteUseCase;

@Configuration
public class BeanConfig {

	@Bean
	UsuarioRepositoryPort createUsuarioRepositoryPort(JpaUsuarioRepository jpaUsuarioRepository) {
		return new UsuarioRepository(jpaUsuarioRepository);
	}

	@Bean
	MedicoRepositoryPort createMedicoRepositoryPort(JpaMedicoRepository jpaMedicoRepository) {
		return new MedicoRepository(jpaMedicoRepository);
	}
	
	@Bean
	AgendaRepositoryPort createAgendaRepositoryPort(JpaAgendaRepository jpaAgendaRepository) {
		return new AgendaRepository(jpaAgendaRepository);
	}

	@Bean
	CadastrarPacienteUseCase createCadastrarPacienteUseCase(UsuarioRepositoryPort repository, PasswordEncoder passwordEncoder) {
		return new CadastrarPacienteUseCase(repository, passwordEncoder);
	}
	
	@Bean
	CadastrarMedicoUseCase createCadastrarMedicoUseCase(MedicoRepositoryPort repository, PasswordEncoder passwordEncoder) {
		return new CadastrarMedicoUseCase(repository, passwordEncoder);
	}
	
	@Bean
	BuscarHorariosAtendimentoUseCase createObterHorariosAtendimentoUseCase(MedicoRepositoryPort repository) {
		return new BuscarHorariosAtendimentoUseCase(repository);
	}
	
	@Bean
	AtualizarAgendaUseCase createAtualizarAgendaUseCase(AgendaRepositoryPort repository) {
		return new AtualizarAgendaUseCase(repository);
	}
	
	@Bean
	BuscarAgendaUseCase createBuscarAgendaUseCase(AgendaRepositoryPort repository) {
		return new BuscarAgendaUseCase(repository);
	}
	
	@Bean
	BuscarHorariosDisponiveisUseCase createBuscarHorariosDisponiveisUseCase(MedicoRepositoryPort repository) {
		return new BuscarHorariosDisponiveisUseCase(repository);
	}
	
	@Bean
	BuscarMedicosDisponiveisUseCase createBuscarMedicosDisponiveisUseCase(MedicoRepositoryPort repository) {
		return new BuscarMedicosDisponiveisUseCase(repository);
	}

	@Bean
	PasswordEncoder createPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
