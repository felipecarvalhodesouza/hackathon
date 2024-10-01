package br.com.fiap.hackathon.main.utils;

import br.com.fiap.hackathon.domain.enums.DiaSemana;
import br.com.fiap.hackathon.domain.enums.PeriodoTrabalho;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrdenadorAgendaTest {

    @Test
    public void testGetAgendaOrdenada_OrdensCrescentes() {
        Map<DiaSemana, PeriodoTrabalho> diasComPeriodos = new HashMap<>();
        diasComPeriodos.put(DiaSemana.SEXTA, PeriodoTrabalho.MANHA);
        diasComPeriodos.put(DiaSemana.QUARTA, PeriodoTrabalho.TARDE);
        diasComPeriodos.put(DiaSemana.SEGUNDA, PeriodoTrabalho.MANHA);

        Map<DiaSemana, PeriodoTrabalho> expected = new HashMap<>();
        expected.put(DiaSemana.SEGUNDA, PeriodoTrabalho.MANHA);
        expected.put(DiaSemana.QUARTA, PeriodoTrabalho.TARDE);
        expected.put(DiaSemana.SEXTA, PeriodoTrabalho.MANHA);

        Map<DiaSemana, PeriodoTrabalho> actual = OrdenadorAgenda.getAgendaOrdenada(diasComPeriodos);
        
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAgendaOrdenada_Vazia() {
        Map<DiaSemana, PeriodoTrabalho> diasComPeriodos = new HashMap<>();

        Map<DiaSemana, PeriodoTrabalho> actual = OrdenadorAgenda.getAgendaOrdenada(diasComPeriodos);
        
        assertEquals(diasComPeriodos, actual);
    }

    @Test
    public void testGetAgendaOrdenada_Nula() {
        Map<DiaSemana, PeriodoTrabalho> actual = OrdenadorAgenda.getAgendaOrdenada(null);
        
        assertEquals(null, actual);
    }

    @Test
    public void testGetAgendaOrdenada_OrdensDesordenadas() {
        Map<DiaSemana, PeriodoTrabalho> diasComPeriodos = new HashMap<>();
        diasComPeriodos.put(DiaSemana.QUINTA, PeriodoTrabalho.MANHA);
        diasComPeriodos.put(DiaSemana.TERCA, PeriodoTrabalho.TARDE);
        diasComPeriodos.put(DiaSemana.SABADO, PeriodoTrabalho.MANHA);

        Map<DiaSemana, PeriodoTrabalho> expected = new HashMap<>();
        expected.put(DiaSemana.TERCA, PeriodoTrabalho.TARDE);
        expected.put(DiaSemana.QUINTA, PeriodoTrabalho.MANHA);
        expected.put(DiaSemana.SABADO, PeriodoTrabalho.MANHA);

        Map<DiaSemana, PeriodoTrabalho> actual = OrdenadorAgenda.getAgendaOrdenada(diasComPeriodos);
        
        assertEquals(expected, actual);
    }
}
