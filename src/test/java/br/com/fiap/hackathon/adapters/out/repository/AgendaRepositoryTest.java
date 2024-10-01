package br.com.fiap.hackathon.adapters.out.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.hackathon.adapters.out.entity.AgendaEntity;
import br.com.fiap.hackathon.adapters.out.entity.MedicoEntity;
import br.com.fiap.hackathon.adapters.out.mapper.AgendaMapper;
import br.com.fiap.hackathon.domain.Agenda;
import br.com.fiap.hackathon.domain.enums.DiaSemana;
import br.com.fiap.hackathon.domain.enums.PeriodoTrabalho;

public class AgendaRepositoryTest {

    @Mock
    private JpaAgendaRepository jpaAgendaRepository;

    @InjectMocks
    private AgendaRepository agendaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAtualizarAgenda() {
        Agenda agenda = new Agenda();
        Map<DiaSemana, PeriodoTrabalho> diasComPeriodos = new HashMap<>();
        diasComPeriodos.put(DiaSemana.SEGUNDA, PeriodoTrabalho.MANHA);
        diasComPeriodos.put(DiaSemana.QUARTA, null);  // Deve ser tratado como NENHUM
        agenda.setDiasComPeriodos(diasComPeriodos);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setDiasComPeriodos(new HashMap<>());

        when(jpaAgendaRepository.findByMedicoEmail("medico@exemplo.com")).thenReturn(agendaEntity);

        agendaRepository.atualizarAgenda(agenda, "medico@exemplo.com");

        verify(jpaAgendaRepository).save(agendaEntity);
        assertEquals(PeriodoTrabalho.MANHA, agendaEntity.getDiasComPeriodos().get(DiaSemana.SEGUNDA));
        assertEquals(PeriodoTrabalho.NENHUM, agendaEntity.getDiasComPeriodos().get(DiaSemana.QUARTA));
    }

    @Test
    void testBuscarAgendaPorEmailMedico() {
        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setDiasComPeriodos(new HashMap<>());
        agendaEntity.setMedico(new MedicoEntity());
        
        when(jpaAgendaRepository.findByMedicoEmail("medico@exemplo.com")).thenReturn(agendaEntity);

        Agenda agenda = agendaRepository.buscarAgendaPorEmailMedico("medico@exemplo.com");

        assertNotNull(agenda);
        verify(jpaAgendaRepository).findByMedicoEmail("medico@exemplo.com");
        assertEquals(agendaEntity.getDiasComPeriodos(), AgendaMapper.toDomain(agendaEntity).getDiasComPeriodos());
    }
}
