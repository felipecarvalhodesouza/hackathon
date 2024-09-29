package br.com.fiap.hackathon.adapters.out.entity;

import java.util.Arrays;
import java.util.List;

import br.com.fiap.hackathon.domain.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medico")
public class MedicoEntity extends UsuarioEntity {

	private static final long serialVersionUID = -904676590989070181L;

	@Column(name = "cd_crm")
	private String crm;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agenda_id", referencedColumnName = "id")
    private AgendaEntity agenda;

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	@Override
	public List<Role> getRoles() {
		return Arrays.asList(Role.MEDICO);
	}
	
    public AgendaEntity getAgenda() {
        return agenda;
    }

    public void setAgenda(AgendaEntity agenda) {
        this.agenda = agenda;
    }

}
