package br.com.fiap.hackathon.application.ports.out;

import br.com.fiap.hackathon.domain.Consulta;
import br.com.fiap.hackathon.domain.exception.HorarioIndisponivelException;

public interface ConsultaRepositoryPort {

	Consulta cadastrarConsulta(Consulta consulta) throws HorarioIndisponivelException;
}
