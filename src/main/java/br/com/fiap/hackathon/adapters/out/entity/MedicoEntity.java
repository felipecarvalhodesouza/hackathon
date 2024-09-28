package br.com.fiap.hackathon.adapters.out.entity;

import java.util.Arrays;
import java.util.List;

import br.com.fiap.hackathon.domain.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "medico")
public class MedicoEntity extends UsuarioEntity {

	private static final long serialVersionUID = -904676590989070181L;

	@Column(name = "cd_crm")
	private String crm;

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

}
