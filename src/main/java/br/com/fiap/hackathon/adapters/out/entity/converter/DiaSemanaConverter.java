package br.com.fiap.hackathon.adapters.out.entity.converter;

import br.com.fiap.hackathon.domain.enums.DiaSemana;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DiaSemanaConverter implements AttributeConverter<DiaSemana, String> {

    @Override
    public String convertToDatabaseColumn(DiaSemana diaSemana) {
        return diaSemana != null ? diaSemana.name() : null;
    }

    @Override
    public DiaSemana convertToEntityAttribute(String dbData) {
        return dbData != null ? DiaSemana.valueOf(dbData) : null;
    }
}
