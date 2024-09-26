package br.com.fiap.hackathon.main.utils;

public class ValidadorCpf {

	private String cpf;
	
	public ValidadorCpf(String cpf) {
		this.cpf = cpf;
	}

    public boolean isCpfValido() {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        return verificarDigito(cpf, 9) && verificarDigito(cpf, 10);
    }

	private boolean verificarDigito(String cpf, int posicao) {
		int soma = 0;
		int peso = posicao + 1;

		for (int i = 0; i < posicao; i++) {
			soma += (cpf.charAt(i) - '0') * (peso--);
		}

		int resto = soma % 11;

		int digitoVerificador = resto < 2 ? 0 : 11 - resto;

		return digitoVerificador == (cpf.charAt(posicao) - '0');
	}
}
