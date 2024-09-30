package br.com.fiap.hackathon.adapters.in.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.hackathon.application.usecases.CadastrarMedicoUseCase;
import br.com.fiap.hackathon.domain.Medico;

class CadastroMedicoControllerTest {

    private CadastroMedicoController controller;

    @Mock
    private CadastrarMedicoUseCase cadastrarMedicoUseCase;

    @BeforeEach
    void setUp() {
        cadastrarMedicoUseCase = mock(CadastrarMedicoUseCase.class);
        controller = new CadastroMedicoController(cadastrarMedicoUseCase);
    }

    @Test
    void testCadastrarUsuarioMedicoSuccess() {
        Medico medico = new Medico();

        doNothing().when(cadastrarMedicoUseCase).executar(medico);

        RedirectAttributes mockRedirectAttributes = mock(RedirectAttributes.class);

        String result = controller.cadastrarUsuarioMedico(medico, mockRedirectAttributes);

        verify(cadastrarMedicoUseCase).executar(medico);

        verify(mockRedirectAttributes).addFlashAttribute("mensagem", "Usuário criado com sucesso");

        assertEquals("redirect:/login", result);
    }
}
