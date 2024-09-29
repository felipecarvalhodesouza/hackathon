package br.com.fiap.hackathon.domain.enums;

import java.time.DayOfWeek;

public enum DiaSemana {
	DOMINGO(0, "DOMINGO"), SEGUNDA(1, "SEGUNDA"), TERCA(2, "TERCA"), QUARTA(3, "QUARTA"), QUINTA(4, "QUINTA"),
	SEXTA(5, "SEXTA"), SABADO(6, "SABADO");

	private int codigo;
	private String descricao;

	private DiaSemana(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static DiaSemana from(DayOfWeek dayOfWeek) {
		return DiaSemana.values()[dayOfWeek.getValue()];
	}
}
