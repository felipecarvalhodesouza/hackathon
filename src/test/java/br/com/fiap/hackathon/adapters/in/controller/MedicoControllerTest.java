package br.com.fiap.hackathon.adapters.in.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import br.com.fiap.hackathon.application.usecases.AtualizarAgendaUseCase;
import br.com.fiap.hackathon.application.usecases.BuscarAgendaUseCase;
import br.com.fiap.hackathon.application.usecases.BuscarHorariosAtendimentoUseCase;
import br.com.fiap.hackathon.domain.Agenda;
import br.com.fiap.hackathon.domain.GradeAtendimento;

class TestMedicoController {

    private MedicoController controller;

    @Mock
    private AtualizarAgendaUseCase atualizarAgendaUseCase;

    @Mock
    private BuscarAgendaUseCase buscarAgendaUseCase;

    @Mock
    private BuscarHorariosAtendimentoUseCase buscarHorariosAtendimentoUseCase;

    @BeforeEach
    void setUp() {
        atualizarAgendaUseCase = mock(AtualizarAgendaUseCase.class);
        buscarAgendaUseCase = mock(BuscarAgendaUseCase.class);
        buscarHorariosAtendimentoUseCase = mock(BuscarHorariosAtendimentoUseCase.class);
        controller = new MedicoController(atualizarAgendaUseCase, buscarAgendaUseCase, buscarHorariosAtendimentoUseCase);
    }

    @Test
    void test_configurar_agenda() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user123");

        Agenda mockAgenda = new Agenda();
        when(buscarAgendaUseCase.executar("user123")).thenReturn(mockAgenda);

        Model mockModel = mock(Model.class);

        String result = controller.configurarAgenda(authentication, mockModel);

        assertEquals(result, "configurarAgenda");

        verify(mockModel).addAttribute("usuario", "user123");
        verify(mockModel).addAttribute("agenda", mockAgenda);
    }

    @Test
    void test_cadastrar_agenda_medico() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user123");

        Agenda agenda = new Agenda();

        doNothing().when(atualizarAgendaUseCase).executar(agenda, "user123");

        Model mockModel = mock(Model.class);

        List<GradeAtendimento> mockHorarios = new ArrayList<>();
        when(buscarHorariosAtendimentoUseCase.executar("user123", LocalDate.now())).thenReturn(mockHorarios);

        String result = controller.cadastrarAgendaMedico(agenda, authentication, mockModel);

        verify(atualizarAgendaUseCase).executar(agenda, "user123");

        verify(mockModel).addAttribute("usuario", "user123");
        verify(mockModel).addAttribute("gradeAtendimento", mockHorarios);

        assertEquals(result, "home");
    }
}

