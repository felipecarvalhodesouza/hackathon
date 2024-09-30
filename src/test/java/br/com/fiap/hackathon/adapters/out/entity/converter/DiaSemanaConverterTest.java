package br.com.fiap.hackathon.adapters.out.entity.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.hackathon.domain.enums.DiaSemana;

public class DiaSemanaConverterTest {

    private DiaSemanaConverter diaSemanaConverter;

    @BeforeEach
    public void setUp() {
        diaSemanaConverter = new DiaSemanaConverter();
    }

    @Test
    public void testConvertToDatabaseColumn() {
        String result = diaSemanaConverter.convertToDatabaseColumn(DiaSemana.SEGUNDA);
        assertThat(result).isEqualTo("SEGUNDA");

        result = diaSemanaConverter.convertToDatabaseColumn(null);
        assertThat(result).isNull();
    }

    @Test
    public void testConvertToEntityAttribute() {
        DiaSemana result = diaSemanaConverter.convertToEntityAttribute("SEGUNDA");
        assertThat(result).isEqualTo(DiaSemana.SEGUNDA);

        result = diaSemanaConverter.convertToEntityAttribute(null);
        assertThat(result).isNull();

        try {
            diaSemanaConverter.convertToEntityAttribute("INVALIDO");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageContaining("No enum constant");
        }
    }
}

