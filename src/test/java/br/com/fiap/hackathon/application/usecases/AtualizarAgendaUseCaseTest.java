package br.com.fiap.hackathon.application.usecases;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.hackathon.application.ports.out.AgendaRepositoryPort;
import br.com.fiap.hackathon.domain.Agenda;

public class AtualizarAgendaUseCaseTest {

    private AgendaRepositoryPort repository;
    private AtualizarAgendaUseCase atualizarAgendaUseCase;

    @BeforeEach
    public void setUp() {
        repository = mock(AgendaRepositoryPort.class);
        atualizarAgendaUseCase = new AtualizarAgendaUseCase(repository);
    }

    @Test
    public void testExecutarChamaAtualizarAgenda() {
        Agenda agenda = new Agenda();
        String emailMedico = "medico@example.com";

        atualizarAgendaUseCase.executar(agenda, emailMedico);

        verify(repository, times(1)).atualizarAgenda(agenda, emailMedico);
    }
}
