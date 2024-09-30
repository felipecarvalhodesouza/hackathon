package br.com.fiap.hackathon.adapters.out.mapper;

import br.com.fiap.hackathon.adapters.out.entity.ConsultaEntity;
import br.com.fiap.hackathon.domain.Consulta;

public class ConsultaMapper {
	
	private ConsultaMapper() {}

	public static Consulta toDomain(ConsultaEntity entity) {
		Consulta consulta = new Consulta();
		consulta.setId(entity.getId());
		consulta.setMedico(MedicoMapper.toDomain(entity.getMedico()));
		consulta.setPaciente(UsuarioMapper.toDomain(entity.getPaciente()));
		consulta.setDia(entity.getDia());
		consulta.setHorario(entity.getHorario());
		return consulta;
	}

	public static ConsultaEntity toEntity(Consulta consulta) {
		ConsultaEntity entity = new ConsultaEntity();
		entity.setId(consulta.getId());
		entity.setMedico(MedicoMapper.toEntity(consulta.getMedico()));
		entity.setPaciente(UsuarioMapper.toEntity(consulta.getPaciente()));
		entity.setDia(consulta.getDia());
		entity.setHorario(consulta.getHorario());
		return entity;
	}
}
