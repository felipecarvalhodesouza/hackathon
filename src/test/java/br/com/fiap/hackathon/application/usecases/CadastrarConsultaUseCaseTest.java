package br.com.fiap.hackathon.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.hackathon.application.ports.out.ConsultaRepositoryPort;
import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.application.ports.out.UsuarioRepositoryPort;
import br.com.fiap.hackathon.domain.Consulta;
import br.com.fiap.hackathon.domain.Medico;
import br.com.fiap.hackathon.domain.Usuario;
import br.com.fiap.hackathon.domain.exception.HorarioIndisponivelException;

public class CadastrarConsultaUseCaseTest {

    private ConsultaRepositoryPort consultaRepository;
    private UsuarioRepositoryPort usuarioRepository;
    private MedicoRepositoryPort medicoRepository;
    private CadastrarConsultaUseCase cadastrarConsultaUseCase;

    @BeforeEach
    void setUp() {
        consultaRepository = mock(ConsultaRepositoryPort.class);
        usuarioRepository = mock(UsuarioRepositoryPort.class);
        medicoRepository = mock(MedicoRepositoryPort.class);
        cadastrarConsultaUseCase = new CadastrarConsultaUseCase(consultaRepository, usuarioRepository, medicoRepository);
    }

    @Test
    void testCadastrarConsulta_Sucesso() {
        Long medicoId = 1L;
        String emailPaciente = "paciente@example.com";
        LocalDate data = LocalDate.of(2024, 9, 30);
        String horario = "10:00";

        Usuario paciente = new Usuario();
        paciente.setEmail(emailPaciente);

        Medico medico = new Medico();
        medico.setId(medicoId);

        when(usuarioRepository.findByEmail(emailPaciente)).thenReturn(Optional.of(paciente));
        when(medicoRepository.buscarPor(medicoId)).thenReturn(medico);

        Consulta consultaEsperada = new Consulta();
        consultaEsperada.setMedico(medico);
        consultaEsperada.setPaciente(paciente);
        consultaEsperada.setDia(data);
        consultaEsperada.setHorario(LocalTime.parse(horario));

        when(consultaRepository.cadastrarConsulta(any(Consulta.class))).thenReturn(consultaEsperada);

        Consulta consultaResult = cadastrarConsultaUseCase.executar(medicoId, emailPaciente, data, horario);

        assertNotNull(consultaResult);
        assertEquals(medico, consultaResult.getMedico());
        assertEquals(paciente, consultaResult.getPaciente());
        assertEquals(data, consultaResult.getDia());
        assertEquals(LocalTime.parse(horario), consultaResult.getHorario());
    }

    @Test
    void testCadastrarConsulta_PacienteNaoEncontrado() {
        Long medicoId = 1L;
        String emailPaciente = "paciente@inexistente.com";
        LocalDate data = LocalDate.of(2024, 9, 30);
        String horario = "10:00";

        when(usuarioRepository.findByEmail(emailPaciente)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cadastrarConsultaUseCase.executar(medicoId, emailPaciente, data, horario);
        });

        assertEquals("Paciente não encontrado", exception.getMessage());
    }

    @Test
    void testCadastrarConsulta_MedicoNaoEncontrado() {
        Long medicoId = 1L;
        String emailPaciente = "paciente@example.com";
        LocalDate data = LocalDate.of(2024, 9, 30);
        String horario = "10:00";

        Usuario paciente = new Usuario();
        paciente.setEmail(emailPaciente);

        when(usuarioRepository.findByEmail(emailPaciente)).thenReturn(Optional.of(paciente));
        when(medicoRepository.buscarPor(medicoId)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> {
            cadastrarConsultaUseCase.executar(medicoId, emailPaciente, data, horario);
        });
    }

    @Test
    void testCadastrarConsulta_HorarioIndisponivel() {
        Long medicoId = 1L;
        String emailPaciente = "paciente@example.com";
        LocalDate data = LocalDate.of(2024, 9, 30);
        String horario = "10:00";

        Usuario paciente = new Usuario();
        paciente.setEmail(emailPaciente);

        Medico medico = new Medico();
        medico.setId(medicoId);

        when(usuarioRepository.findByEmail(emailPaciente)).thenReturn(Optional.of(paciente));
        when(medicoRepository.buscarPor(medicoId)).thenReturn(medico);

        when(consultaRepository.cadastrarConsulta(any(Consulta.class))).thenThrow(new HorarioIndisponivelException("Horário já ocupado"));

        HorarioIndisponivelException exception = assertThrows(HorarioIndisponivelException.class, () -> {
            cadastrarConsultaUseCase.executar(medicoId, emailPaciente, data, horario);
        });

        assertEquals("Horário já ocupado", exception.getMessage());
    }
}
