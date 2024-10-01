package br.com.fiap.hackathon.application.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.domain.Medico;

public class CadastrarMedicoUseCaseTest {

    private MedicoRepositoryPort medicoRepository;
    private PasswordEncoder passwordEncoder;
    private CadastrarMedicoUseCase cadastrarMedicoUseCase;

    @BeforeEach
    public void setUp() {
        medicoRepository = mock(MedicoRepositoryPort.class);
        passwordEncoder = mock(PasswordEncoder.class);
        cadastrarMedicoUseCase = new CadastrarMedicoUseCase(medicoRepository, passwordEncoder);
    }

    @Test
    public void testCadastrarMedico_Sucesso() {
        Medico medico = new Medico();
        medico.setNome("Dr. João");
        medico.setSenha("senhaSecreta");

        String senhaCodificada = "senhaCodificada";
        when(passwordEncoder.encode(medico.getSenha())).thenReturn(senhaCodificada);

        cadastrarMedicoUseCase.executar(medico);

        assertEquals(senhaCodificada, medico.getSenha());
        verify(medicoRepository).cadastrarMedico(medico);  // Verifica se o método foi chamado
    }
}
