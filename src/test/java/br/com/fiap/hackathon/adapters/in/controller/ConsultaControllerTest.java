package br.com.fiap.hackathon.adapters.in.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import br.com.fiap.hackathon.application.usecases.BuscarHorariosDisponiveisUseCase;
import br.com.fiap.hackathon.application.usecases.BuscarMedicosDisponiveisUseCase;
import br.com.fiap.hackathon.application.usecases.CadastrarConsultaUseCase;
import br.com.fiap.hackathon.domain.Consulta;
import br.com.fiap.hackathon.domain.Medico;
import br.com.fiap.hackathon.domain.exception.HorarioIndisponivelException;

class ConsultaControllerTest {

    private ConsultaController controller;

    @Mock
    private BuscarHorariosDisponiveisUseCase buscarHorariosDisponiveisUseCase;

    @Mock
    private BuscarMedicosDisponiveisUseCase buscarMedicosDisponiveisUseCase;

    @Mock
    private CadastrarConsultaUseCase cadastrarConsultaUseCase;

    @BeforeEach
    void setUp() {
        buscarHorariosDisponiveisUseCase = mock(BuscarHorariosDisponiveisUseCase.class);
        buscarMedicosDisponiveisUseCase = mock(BuscarMedicosDisponiveisUseCase.class);
        cadastrarConsultaUseCase = mock(CadastrarConsultaUseCase.class);
        controller = new ConsultaController(
            buscarHorariosDisponiveisUseCase,
            buscarMedicosDisponiveisUseCase,
            cadastrarConsultaUseCase
        );
    }

    @Test
    void test_mostrar_marcar_consulta() {
    	
        List<Medico> mockMedicos = Arrays.asList(
            new Medico(),
            new Medico()
        );
        when(buscarMedicosDisponiveisUseCase.executar(LocalDate.now())).thenReturn(mockMedicos);

        Model mockModel = mock(Model.class);
        Authentication auth = mock(Authentication.class);

        controller.mostrarMarcarConsulta(auth, mockModel);

        verify(mockModel).addAttribute("usuario", auth.getName());
        verify(mockModel).addAttribute("medicos", mockMedicos);
    }

    @Test
    void test_marcar_consulta_success() {
        Long medicoId = 1L;
        String horario = "10:00";
        String emailPaciente = "email@email.com";

        when(cadastrarConsultaUseCase.executar(medicoId, emailPaciente, LocalDate.now(), horario)).thenReturn(new Consulta());

        Model mockModel = mock(Model.class);
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn(emailPaciente);

        String result = controller.marcarConsulta(medicoId, horario, mockModel, auth);

        verify(cadastrarConsultaUseCase).executar(medicoId, emailPaciente, LocalDate.now(), horario);

        verify(mockModel).addAttribute("mensagem", "Consulta agendada com sucesso!");

        assertEquals("home", result);
    }

    @Test
    void test_marcar_consulta_exception() {
        Long medicoId = 1L;
        String horario = "10:00";
        String emailPaciente = "email@email.com";

        doThrow(new HorarioIndisponivelException("Horário indisponível")).when(cadastrarConsultaUseCase).executar(medicoId, emailPaciente, LocalDate.now(), horario);

        Model mockModel = mock(Model.class);
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn(emailPaciente);

        String result = controller.marcarConsulta(medicoId, horario, mockModel, auth);

        verify(cadastrarConsultaUseCase).executar(medicoId, emailPaciente, LocalDate.now(), horario);

        verify(mockModel).addAttribute("mensagem", "Horário indisponível");

        assertEquals("home", result);
    }
}
