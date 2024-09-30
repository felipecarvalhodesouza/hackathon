package br.com.fiap.hackathon.adapters.in.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.hackathon.application.usecases.CadastrarPacienteUseCase;
import br.com.fiap.hackathon.domain.Usuario;

class CadastroPacienteControllerTest {

    private CadastroPacienteController controller;

    @Mock
    private CadastrarPacienteUseCase cadastrarPacienteUseCase;

    @BeforeEach
    void setUp() {
        cadastrarPacienteUseCase = mock(CadastrarPacienteUseCase.class);
        controller = new CadastroPacienteController(cadastrarPacienteUseCase);
    }

    @Test
    void testCadastrarUsuarioPacienteSuccess() throws Exception {
        Usuario usuario = new Usuario();

        doNothing().when(cadastrarPacienteUseCase).executar(usuario);

        RedirectAttributes mockRedirectAttributes = mock(RedirectAttributes.class);

        String result = controller.cadastrarUsuarioPaciente(usuario, mockRedirectAttributes);

        verify(cadastrarPacienteUseCase).executar(usuario);

        verify(mockRedirectAttributes).addFlashAttribute("mensagem", "Usuário criado com sucesso");

        assertEquals("redirect:/login", result);
    }
}

