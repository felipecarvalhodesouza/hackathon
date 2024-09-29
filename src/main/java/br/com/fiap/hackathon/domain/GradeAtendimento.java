package br.com.fiap.hackathon.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class GradeAtendimento {

	private LocalDate data;
	private LocalTime horario;
	private String descricao;
	

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

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
