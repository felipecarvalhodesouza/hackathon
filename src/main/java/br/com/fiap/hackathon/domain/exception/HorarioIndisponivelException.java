package br.com.fiap.hackathon.domain.exception;

public class HorarioIndisponivelException extends Exception{

	private static final long serialVersionUID = -2689778043351953661L;

	public HorarioIndisponivelException(String mensagem) {
		super(mensagem);
	}
}
