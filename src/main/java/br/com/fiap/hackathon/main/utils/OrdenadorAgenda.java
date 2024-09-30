package br.com.fiap.hackathon.main.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.fiap.hackathon.domain.enums.DiaSemana;
import br.com.fiap.hackathon.domain.enums.PeriodoTrabalho;

public class OrdenadorAgenda {
	
	private OrdenadorAgenda() {}

	public static Map<DiaSemana, PeriodoTrabalho> getAgendaOrdenada(Map<DiaSemana, PeriodoTrabalho> diasComPeriodos) {
		if(diasComPeriodos == null) {
			return diasComPeriodos;
		}
		
		Map<DiaSemana, PeriodoTrabalho> sortedMap = new LinkedHashMap<>();

		List<Map.Entry<DiaSemana, PeriodoTrabalho>> sortedEntries = new ArrayList<>(diasComPeriodos.entrySet());
		sortedEntries.sort(Comparator.comparing(Map.Entry::getKey));

		for (Map.Entry<DiaSemana, PeriodoTrabalho> entry : sortedEntries) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}
}
