package br.com.fiap.hackathon.application.usecases;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.hackathon.application.ports.out.AgendaRepositoryPort;
import br.com.fiap.hackathon.domain.Agenda;

class AtualizarAgendaUseCaseTest {

    private AgendaRepositoryPort repository;
    private AtualizarAgendaUseCase atualizarAgendaUseCase;

    @BeforeEach
    void setUp() {
        repository = mock(AgendaRepositoryPort.class);
        atualizarAgendaUseCase = new AtualizarAgendaUseCase(repository);
    }

    @Test
    void testExecutarChamaAtualizarAgenda() {
        Agenda agenda = new Agenda();
        String emailMedico = "medico@example.com";

        atualizarAgendaUseCase.executar(agenda, emailMedico);

        verify(repository, times(1)).atualizarAgenda(agenda, emailMedico);
    }
}
