package br.com.fiap.hackathon.application.ports.out;

import br.com.fiap.hackathon.domain.Consulta;

public interface EnvioNotificacaoGateway {

	void enviarNotificacao(Consulta consulta);
}
