package br.com.fiap.hackathon.domain;

import java.time.LocalTime;

public class GradeAtendimento {

	private LocalTime horario;
	private String descricao;

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
