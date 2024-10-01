package br.com.fiap.hackathon.application.usecases;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.domain.GradeAtendimento;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class BuscarHorariosAtendimentoUseCaseTest {

    private MedicoRepositoryPort repository;
    private BuscarHorariosAtendimentoUseCase buscarHorariosAtendimentoUseCase;

    @BeforeEach
    public void setUp() {
        repository = mock(MedicoRepositoryPort.class);
        buscarHorariosAtendimentoUseCase = new BuscarHorariosAtendimentoUseCase(repository);
    }

    @Test
    public void testExecutarChamaBuscarHorariosAtendimento() {
        String nomeUsuario = "medico@example.com";
        LocalDate data = LocalDate.now();
        List<GradeAtendimento> horariosEsperados = Arrays.asList(new GradeAtendimento(), new GradeAtendimento());

        when(repository.buscarHorariosAtendimento(nomeUsuario, data)).thenReturn(horariosEsperados);

        List<GradeAtendimento> result = buscarHorariosAtendimentoUseCase.executar(nomeUsuario, data);

        assertEquals(horariosEsperados, result);
        verify(repository, times(1)).buscarHorariosAtendimento(nomeUsuario, data);
    }
}
