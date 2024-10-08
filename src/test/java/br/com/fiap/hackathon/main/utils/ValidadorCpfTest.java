package br.com.fiap.hackathon.main.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidadorCpfTest {

    @Test
    void testCpfValido() {
        ValidadorCpf validador = new ValidadorCpf("123.456.789-09");
        assertTrue(validador.isCpfValido(), "O CPF deve ser válido");
    }

    @Test
    void testCpfInvalido() {
        ValidadorCpf validador = new ValidadorCpf("123.456.789-00");
        assertFalse(validador.isCpfValido(), "O CPF deve ser inválido");
    }

    @Test
    void testCpfComMenosDe11Digitos() {
        ValidadorCpf validador = new ValidadorCpf("123.456.789");
        assertFalse(validador.isCpfValido(), "O CPF com menos de 11 dígitos deve ser inválido");
    }

    @Test
    void testCpfComMaisDe11Digitos() {
        ValidadorCpf validador = new ValidadorCpf("123.456.789-091");
        assertFalse(validador.isCpfValido(), "O CPF com mais de 11 dígitos deve ser inválido");
    }

    @Test
    void testCpfComCaracteresEspeciais() {
        ValidadorCpf validador = new ValidadorCpf("123.456.789-09");
        assertTrue(validador.isCpfValido(), "O CPF válido com caracteres especiais deve ser aceito");
    }

    @Test
    void testCpfComTodosDigitosIguais() {
        ValidadorCpf validador = new ValidadorCpf("111.111.111-11");
        assertFalse(validador.isCpfValido(), "O CPF com todos os dígitos iguais deve ser inválido");
    }

    @Test
    void testCpfValidoSemMascara() {
        ValidadorCpf validador = new ValidadorCpf("12345678909");
        assertTrue(validador.isCpfValido(), "O CPF válido sem máscara deve ser aceito");
    }
}
