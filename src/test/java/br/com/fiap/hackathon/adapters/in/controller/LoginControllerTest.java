package br.com.fiap.hackathon.adapters.in.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import br.com.fiap.hackathon.application.usecases.BuscarHorariosAtendimentoUseCase;
import br.com.fiap.hackathon.application.usecases.BuscarMinhasConsultasAgendadasUseCase;
import br.com.fiap.hackathon.domain.GradeAtendimento;

class TestLoginController {

	private LoginController controller;

	@Mock
	private BuscarHorariosAtendimentoUseCase obterHorariosAtendimentoUseCase;

	@Mock
	private BuscarMinhasConsultasAgendadasUseCase buscarMinhasConsultasAgendadasUseCase;

	@BeforeEach
	void setUp() {
		obterHorariosAtendimentoUseCase = mock(BuscarHorariosAtendimentoUseCase.class);
		buscarMinhasConsultasAgendadasUseCase = mock(BuscarMinhasConsultasAgendadasUseCase.class);
		controller = new LoginController(obterHorariosAtendimentoUseCase, buscarMinhasConsultasAgendadasUseCase);
	}

	@Test
	void test_login() {
		String result = controller.login();
		assertEquals(result, "login");
	}

	@Test
	void test_home() {
		Authentication authentication = mock(Authentication.class);
		when(authentication.getName()).thenReturn("user123");

		List<GradeAtendimento> mockHorarios = Arrays.asList(new GradeAtendimento(), new GradeAtendimento(),
				new GradeAtendimento());
		List<GradeAtendimento> mockConsultas = Arrays.asList(new GradeAtendimento(), new GradeAtendimento());
		when(obterHorariosAtendimentoUseCase.executar("user123", LocalDate.now())).thenReturn(mockHorarios);
		when(buscarMinhasConsultasAgendadasUseCase.executar("user123")).thenReturn(mockConsultas);

		Model mockModel = mock(Model.class);

		String result = controller.home(authentication, mockModel);

		assertEquals(result, "home");

		verify(mockModel).addAttribute("usuario", "user123");
		verify(mockModel).addAttribute("gradeAtendimento", mockHorarios);
		verify(mockModel).addAttribute("minhasConsultas", mockConsultas);
	}
}
