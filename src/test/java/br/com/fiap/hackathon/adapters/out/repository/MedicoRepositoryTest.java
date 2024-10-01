package br.com.fiap.hackathon.adapters.out.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.hackathon.adapters.out.entity.AgendaEntity;
import br.com.fiap.hackathon.adapters.out.entity.MedicoEntity;
import br.com.fiap.hackathon.application.ports.out.ConsultaRepositoryPort;
import br.com.fiap.hackathon.domain.Consulta;
import br.com.fiap.hackathon.domain.GradeAtendimento;
import br.com.fiap.hackathon.domain.Medico;
import br.com.fiap.hackathon.domain.Usuario;
import br.com.fiap.hackathon.domain.enums.DiaSemana;
import br.com.fiap.hackathon.domain.enums.PeriodoTrabalho;

class MedicoRepositoryTest {

    @Mock
    private JpaMedicoRepository jpaMedicoRepository;

    @Mock
    private ConsultaRepositoryPort consultaRepository;

    @InjectMocks
    private MedicoRepository medicoRepository;

    private MedicoEntity medicoEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        medicoEntity = new MedicoEntity();
        medicoEntity.setId(1L);
        medicoEntity.setEmail("medico@exemplo.com");
        AgendaEntity agenda = new AgendaEntity();
        Map<DiaSemana, PeriodoTrabalho> dias = new LinkedHashMap<>();
        dias.put(DiaSemana.SEGUNDA, PeriodoTrabalho.MANHA);
        agenda.setDiasComPeriodos(dias);
        medicoEntity.setAgenda(agenda);
    }

    @Test
    void testFindByEmail_ReturnsMedico() {
        when(jpaMedicoRepository.findByEmail("medico@exemplo.com")).thenReturn(medicoEntity);

        Optional<Medico> resultado = medicoRepository.findByEmail("medico@exemplo.com");

        assertTrue(resultado.isPresent());
        assertEquals("medico@exemplo.com", resultado.get().getEmail());
    }

    @Test
    void testCadastrarMedico() {
        Medico medico = new Medico();
        medico.setEmail("novo_medico@exemplo.com");

        when(jpaMedicoRepository.save(any(MedicoEntity.class))).thenReturn(medicoEntity);

        Medico resultado = medicoRepository.cadastrarMedico(medico);

        assertNotNull(resultado);
        assertEquals("medico@exemplo.com", resultado.getEmail());
        verify(jpaMedicoRepository).save(any(MedicoEntity.class));
    }

    @Test
    void testBuscarHorariosAtendimento_MedicoNaoEncontrado() {
        LocalDate data = LocalDate.now();
        when(jpaMedicoRepository.findByEmail("medico@exemplo.com")).thenReturn(null);

        List<GradeAtendimento> resultado = medicoRepository.buscarHorariosAtendimento("medico@exemplo.com", data);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void testBuscarHorariosAtendimento_HorariosDisponiveis() {
        LocalDate data = LocalDate.now();
        when(jpaMedicoRepository.findByEmail("medico@exemplo.com")).thenReturn(medicoEntity);

        List<Consulta> consultasAgendadas = new ArrayList<>();
        Consulta consulta = new Consulta();
        consulta.setMedico(new Medico());
        consulta.setPaciente(new Usuario());
        consulta.setDia(LocalDate.now());
        consulta.setHorario(LocalTime.now());
        consultasAgendadas.add(consulta);
        when(consultaRepository.buscarTodasConsultasPorMedicoEDia(data, medicoEntity.getId())).thenReturn(consultasAgendadas);

        List<GradeAtendimento> resultado = medicoRepository.buscarHorariosAtendimento("medico@exemplo.com", data);

        assertFalse(resultado.isEmpty());
    }

    @Test
    void testBuscarHorariosDisponiveis() {
        LocalDate data = LocalDate.now();
        when(jpaMedicoRepository.findById(1L)).thenReturn(Optional.of(medicoEntity));

        List<LocalTime> resultado = medicoRepository.buscarHorariosDisponiveis(1L, data);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void testBuscarMedicosDisponiveis() {
        LocalDate data = LocalDate.now();
        List<MedicoEntity> medicos = Collections.singletonList(medicoEntity);
        when(jpaMedicoRepository.findMedicosComAtendimentoNoDia(any())).thenReturn(medicos);

        List<Medico> resultado = medicoRepository.buscarMedicosDisponiveis(data);

        assertFalse(resultado.isEmpty());
    }

    @Test
    void testBuscarPor_MedicoEncontrado() {
        when(jpaMedicoRepository.findById(1L)).thenReturn(Optional.of(medicoEntity));

        Medico resultado = medicoRepository.buscarPor(1L);

        assertNotNull(resultado);
        assertEquals(medicoEntity.getEmail(), resultado.getEmail());
    }

    @Test
    void testBuscarPor_MedicoNaoEncontrado() {
        when(jpaMedicoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> medicoRepository.buscarPor(1L));
    }
}
