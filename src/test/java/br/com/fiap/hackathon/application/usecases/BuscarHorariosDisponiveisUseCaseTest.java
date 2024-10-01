package br.com.fiap.hackathon.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.hackathon.application.ports.out.ConsultaRepositoryPort;
import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.domain.Consulta;

public class BuscarHorariosDisponiveisUseCaseTest {

    private MedicoRepositoryPort medicoRepository;
    private ConsultaRepositoryPort consultaRepository;
    private BuscarHorariosDisponiveisUseCase buscarHorariosDisponiveisUseCase;

    @BeforeEach
    public void setUp() {
        medicoRepository = mock(MedicoRepositoryPort.class);
        consultaRepository = mock(ConsultaRepositoryPort.class);
        buscarHorariosDisponiveisUseCase = new BuscarHorariosDisponiveisUseCase(medicoRepository, consultaRepository);
    }

    @Test
    public void testBuscarHorariosDisponiveis_SemConsultas() {
        Long medicoId = 1L;
        LocalDate data = LocalDate.of(2024, 9, 30);
        List<LocalTime> horariosDisponiveis = List.of(
                LocalTime.parse("08:00"),
                LocalTime.parse("08:30"),
                LocalTime.parse("09:00"),
                LocalTime.parse("09:30"),
                LocalTime.parse("10:00")
            );
        when(medicoRepository.buscarHorariosDisponiveis(any(), any())).thenReturn(horariosDisponiveis);
        when(consultaRepository.buscarTodasConsultasPorMedicoEDia(any(), any())).thenReturn(new ArrayList<>());

        List<String> result = buscarHorariosDisponiveisUseCase.buscarHorariosDisponiveis(medicoId, data);

        assertEquals(horariosDisponiveis.size(), result.size());
    }

    @Test
    public void testBuscarHorariosDisponiveis_ComConsultasAgendadas() {
        Long medicoId = 1L;
        LocalDate data = LocalDate.of(2024, 9, 30);
        List<LocalTime> horariosDisponiveis = List.of(
                LocalTime.parse("08:00"),
                LocalTime.parse("08:30"),
                LocalTime.parse("09:00"),
                LocalTime.parse("09:30"),
                LocalTime.parse("10:00")
            );

        Consulta consulta1 = new Consulta();
        consulta1.setHorario(LocalTime.of(8, 30));

        Consulta consulta2 = new Consulta();
        consulta2.setHorario(LocalTime.of(9, 0));

        List<Consulta> consultasAgendadas = List.of(consulta1, consulta2);

        when(medicoRepository.buscarHorariosDisponiveis(medicoId, data)).thenReturn(horariosDisponiveis);
        when(consultaRepository.buscarTodasConsultasPorMedicoEDia(data, medicoId)).thenReturn(consultasAgendadas);

        List<String> result = buscarHorariosDisponiveisUseCase.buscarHorariosDisponiveis(medicoId, data);

        List<String> expected = List.of("08:00", "09:30", "10:00"); 
        assertEquals(expected, result);
    }

    @Test
    public void testBuscarHorariosDisponiveis_TodasConsultasAgendadas() {
        Long medicoId = 1L;
        LocalDate data = LocalDate.of(2024, 9, 30);
        List<LocalTime> horariosDisponiveis = List.of(
                LocalTime.parse("08:00"),
                LocalTime.parse("08:30"),
                LocalTime.parse("09:00")
            );

        Consulta consulta1 = new Consulta();
        consulta1.setHorario(LocalTime.of(8, 0));

        Consulta consulta2 = new Consulta();
        consulta2.setHorario(LocalTime.of(8, 30));

        Consulta consulta3 = new Consulta();
        consulta3.setHorario(LocalTime.of(9, 0));

        List<Consulta> consultasAgendadas = List.of(consulta1, consulta2, consulta3);

        when(medicoRepository.buscarHorariosDisponiveis(medicoId, data)).thenReturn(horariosDisponiveis);
        when(consultaRepository.buscarTodasConsultasPorMedicoEDia(data, medicoId)).thenReturn(consultasAgendadas);

        List<String> result = buscarHorariosDisponiveisUseCase.buscarHorariosDisponiveis(medicoId, data);

        List<String> expected = new ArrayList<>(); // Nenhum horário disponível
        assertEquals(expected, result);
    }
}
