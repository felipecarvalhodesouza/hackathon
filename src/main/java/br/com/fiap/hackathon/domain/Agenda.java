package br.com.fiap.hackathon.domain;

import java.util.Map;

import br.com.fiap.hackathon.domain.enums.DiaSemana;
import br.com.fiap.hackathon.domain.enums.PeriodoTrabalho;

public class Agenda {

	private Long id;
	private Map<DiaSemana, PeriodoTrabalho> diasComPeriodos;
	private Long medicoId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<DiaSemana, PeriodoTrabalho> getDiasComPeriodos() {
		return diasComPeriodos;
	}

	public void setDiasComPeriodos(Map<DiaSemana, PeriodoTrabalho> diasComPeriodos) {
		this.diasComPeriodos = diasComPeriodos;
	}
	
	public Long getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(Long medicoId) {
		this.medicoId = medicoId;
	}
}
