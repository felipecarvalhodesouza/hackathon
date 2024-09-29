package br.com.fiap.hackathon.application.usecases;

import br.com.fiap.hackathon.application.ports.out.EnvioNotificacaoGateway;
import br.com.fiap.hackathon.domain.Consulta;

public class EnviarNotificacaoUseCase {

	private final EnvioNotificacaoGateway gateway;

	public EnviarNotificacaoUseCase(EnvioNotificacaoGateway gateway) {
		this.gateway = gateway;
	}
	
	public void enviarNotificacao(Consulta consulta) {
		gateway.enviarNotificacao(consulta);
	}
}
