package br.com.fiap.hackathon.adapters.out.mapper;

import br.com.fiap.hackathon.adapters.out.entity.MedicoEntity;
import br.com.fiap.hackathon.domain.Medico;

public class MedicoMapper {
	
	private MedicoMapper() {}

	public static Medico toDomain(MedicoEntity entity) {
		Medico medico = new Medico();
		medico.setId(entity.getId());
		medico.setNome(entity.getNome());
		medico.setCpf(entity.getCpf());
		medico.setEmail(entity.getEmail());
		medico.setSenha(entity.getSenha());
		medico.setCrm(entity.getCrm());
		return medico;
	}

	public static MedicoEntity toEntity(Medico medico) {
		MedicoEntity entity = new MedicoEntity();
		entity.setId(medico.getId());
		entity.setNome(medico.getNome());
		entity.setCpf(medico.getCpf());
		entity.setEmail(medico.getEmail());
		entity.setSenha(medico.getSenha());
		entity.setCrm(medico.getCrm());
		return entity;
	}
}
