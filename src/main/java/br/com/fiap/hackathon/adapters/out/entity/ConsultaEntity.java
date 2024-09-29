package br.com.fiap.hackathon.adapters.out.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "consulta", uniqueConstraints = { @UniqueConstraint(columnNames = { "dia", "horario", "medico_id" }) })
public class ConsultaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate dia;

	@Column(nullable = false)
	private LocalTime horario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medico_id", nullable = false)
	private MedicoEntity medico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id", nullable = false)
	private UsuarioEntity paciente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public MedicoEntity getMedico() {
		return medico;
	}

	public void setMedico(MedicoEntity medico) {
		this.medico = medico;
	}

	public UsuarioEntity getPaciente() {
		return paciente;
	}

	public void setPaciente(UsuarioEntity paciente) {
		this.paciente = paciente;
	}
}
