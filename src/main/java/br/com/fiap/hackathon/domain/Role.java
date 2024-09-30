package br.com.fiap.hackathon.domain;

public enum Role {

	MEDICO(1, "MEDICO"), PACIENTE(2, "PACIENTE");

	private int codigo;
	private String descricao;

	private Role(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

}
