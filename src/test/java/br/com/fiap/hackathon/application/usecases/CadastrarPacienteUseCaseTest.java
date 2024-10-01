package br.com.fiap.hackathon.application.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.hackathon.application.ports.out.UsuarioRepositoryPort;
import br.com.fiap.hackathon.domain.Usuario;

public class CadastrarPacienteUseCaseTest {

    private UsuarioRepositoryPort usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private CadastrarPacienteUseCase cadastrarPacienteUseCase;

    @BeforeEach
    public void setUp() {
        usuarioRepository = mock(UsuarioRepositoryPort.class);
        passwordEncoder = mock(PasswordEncoder.class);
        cadastrarPacienteUseCase = new CadastrarPacienteUseCase(usuarioRepository, passwordEncoder);
    }

    @Test
    public void testCadastrarPaciente_Sucesso() {
        Usuario usuario = new Usuario();
        usuario.setNome("João da Silva");
        usuario.setSenha("senhaSecreta");

        String senhaCodificada = "senhaCodificada";
        when(passwordEncoder.encode(usuario.getSenha())).thenReturn(senhaCodificada);

        cadastrarPacienteUseCase.executar(usuario);

        assertEquals(senhaCodificada, usuario.getSenha());
        verify(usuarioRepository).cadastrarUsuario(usuario);
    }
}
