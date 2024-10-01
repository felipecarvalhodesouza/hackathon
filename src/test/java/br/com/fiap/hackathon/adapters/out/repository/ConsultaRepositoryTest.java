package br.com.fiap.hackathon.adapters.out.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.hackathon.adapters.out.entity.ConsultaEntity;
import br.com.fiap.hackathon.adapters.out.entity.MedicoEntity;
import br.com.fiap.hackathon.adapters.out.entity.UsuarioEntity;
import br.com.fiap.hackathon.adapters.out.mapper.ConsultaMapper;
import br.com.fiap.hackathon.application.ports.out.EnvioNotificacaoGateway;
import br.com.fiap.hackathon.application.ports.out.UsuarioRepositoryPort;
import br.com.fiap.hackathon.domain.Consulta;
import br.com.fiap.hackathon.domain.GradeAtendimento;
import br.com.fiap.hackathon.domain.Medico;
import br.com.fiap.hackathon.domain.Usuario;
import br.com.fiap.hackathon.domain.exception.HorarioIndisponivelException;

class ConsultaRepositoryTest {

    @Mock
    private JpaConsultaRepository jpaConsultaRepository;

    @Mock
    private EnvioNotificacaoGateway notificacaoGateway;

    @Mock
    private UsuarioRepositoryPort usuarioRepository;

    @InjectMocks
    private ConsultaRepository consultaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarConsulta_Sucesso() throws HorarioIndisponivelException {
        Consulta consulta = new Consulta();
        consulta.setMedico(new Medico());
        consulta.setPaciente(new Usuario());
        
        ConsultaEntity consultaEntity = new ConsultaEntity();
        consultaEntity.setMedico(new MedicoEntity());
        consultaEntity.setPaciente(new UsuarioEntity());

        when(jpaConsultaRepository.save(any())).thenReturn(consultaEntity);
        doNothing().when(notificacaoGateway).enviarNotificacao(any());

        Consulta result = consultaRepository.cadastrarConsulta(consulta);

        verify(jpaConsultaRepository).save(any());
        verify(notificacaoGateway).enviarNotificacao(any());
        assertNotNull(result);
    }

    @Test
    void testCadastrarConsulta_HorarioIndisponivelException() {
        Consulta consulta = new Consulta();
        consulta.setMedico(new Medico());
        consulta.setPaciente(new Usuario());

        when(jpaConsultaRepository.save(ConsultaMapper.toEntity(consulta))).thenThrow(new RuntimeException("Error"));

        assertThrows(HorarioIndisponivelException.class, () -> consultaRepository.cadastrarConsulta(consulta));
    }

    @Test
    void testBuscarTodasConsultasPorMedicoEDia() {
        LocalDate dia = LocalDate.now();
        Long medicoId = 1L;
        ConsultaEntity consultaEntity = new ConsultaEntity(); 
        consultaEntity.setMedico(new MedicoEntity());
        consultaEntity.setPaciente(new UsuarioEntity());
        List<ConsultaEntity> consultaEntities = Arrays.asList(consultaEntity);

        when(jpaConsultaRepository.findAllByDiaAndMedico(dia, medicoId)).thenReturn(consultaEntities);

        List<Consulta> result = consultaRepository.buscarTodasConsultasPorMedicoEDia(dia, medicoId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(jpaConsultaRepository).findAllByDiaAndMedico(dia, medicoId);
    }

    @Test
    void testBuscarTodasConsultasPorPaciente_Sucesso() {
        String emailPaciente = "paciente@exemplo.com";
        Usuario paciente = new Usuario(); 
        paciente.setId(1L);
        
        ConsultaEntity consultaEntity = new ConsultaEntity();
        consultaEntity.setDia(LocalDate.now());
        consultaEntity.setHorario(LocalTime.now()); 
        consultaEntity.setMedico(new MedicoEntity()); 

        when(usuarioRepository.findByEmail(emailPaciente)).thenReturn(Optional.of(paciente));
        when(jpaConsultaRepository.findAllByPacienteId(paciente.getId())).thenReturn(Arrays.asList(consultaEntity));

        List<GradeAtendimento> result = consultaRepository.buscarTodasConsultasPorPaciente(emailPaciente);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Dr. " + consultaEntity.getMedico().getNome(), result.get(0).getDescricao());
        verify(usuarioRepository).findByEmail(emailPaciente);
        verify(jpaConsultaRepository).findAllByPacienteId(paciente.getId());
    }

    @Test
    void testBuscarTodasConsultasPorPaciente_PacienteNaoEncontrado() {
        String emailPaciente = "paciente@inexistente.com";

        when(usuarioRepository.findByEmail(emailPaciente)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaRepository.buscarTodasConsultasPorPaciente(emailPaciente);
        });
        assertEquals("Paciente não encontrado", exception.getMessage());
    }
}
