package br.com.fiap.hackathon.adapters.out.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.hackathon.adapters.out.entity.MedicoEntity;
import br.com.fiap.hackathon.adapters.out.mapper.MedicoMapper;
import br.com.fiap.hackathon.application.ports.out.MedicoRepositoryPort;
import br.com.fiap.hackathon.domain.Medico;

public class MedicoRepository implements MedicoRepositoryPort{
	
	private final JpaMedicoRepository jpaMedicoRepository;
	
	@Autowired
    public MedicoRepository(JpaMedicoRepository jpaMedicoRepository) {
        this.jpaMedicoRepository = jpaMedicoRepository;
    }

	@Override
	public Optional<Medico> findByEmail(String email) {
		Medico medico = null;
		MedicoEntity medicoEntity = jpaMedicoRepository.findByEmail(email);
		if(medicoEntity != null) {
			medico = MedicoMapper.toDomain(medicoEntity);
		}
		return Optional.ofNullable(medico);
	}

	@Override
	public Medico cadastrarMedico(Medico medico) {
		MedicoEntity usuarioPersistido = jpaMedicoRepository.save(MedicoMapper.toEntity(medico));
		return MedicoMapper.toDomain(usuarioPersistido);
	}

}
