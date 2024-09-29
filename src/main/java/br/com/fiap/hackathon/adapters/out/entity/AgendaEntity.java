package br.com.fiap.hackathon.adapters.out.entity;

import java.util.Map;

import br.com.fiap.hackathon.adapters.out.entity.converter.DiaSemanaConverter;
import br.com.fiap.hackathon.domain.enums.DiaSemana;
import br.com.fiap.hackathon.domain.enums.PeriodoTrabalho;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "agenda")
public class AgendaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ElementCollection
	@CollectionTable(name = "agenda_periodos", joinColumns = @JoinColumn(name = "agenda_id"))
	@MapKeyColumn(name = "dia_semana")
	@Convert(converter = DiaSemanaConverter.class)
	@Column(name = "periodo")
	@Enumerated(EnumType.STRING)
	private Map<DiaSemana, PeriodoTrabalho> diasComPeriodos;

	@OneToOne(mappedBy = "agenda")
	private MedicoEntity medico;

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

	public MedicoEntity getMedico() {
		return medico;
	}

	public void setMedico(MedicoEntity medico) {
		this.medico = medico;
	}
}
