package br.com.fiap.hackathon.main.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ConversorLocalDateTest {

	@Test
	void testGetHoraFormatada_ValidTime() {
		LocalTime time = LocalTime.of(10, 30);
		String expected = "10:30";
		String actual = ConversorLocalDate.getHoraFormatada(time);
		assertEquals(expected, actual);
	}

	@Test
	void testGetHoraLocalTime_ValidString() {
		String timeString = "10:30";
		LocalTime expected = LocalTime.of(10, 30);
		LocalTime actual = ConversorLocalDate.getHoraLocalTime(timeString);
		assertEquals(expected, actual);
	}

	@Test
	void testGetHoraLocalTime_InvalidString() {
		String invalidTimeString = "25:00"; // Horário inválido
		assertThrows(java.time.format.DateTimeParseException.class, () -> {
			ConversorLocalDate.getHoraLocalTime(invalidTimeString);
		});
	}

	@Test
	void testGetHoraFormatada_ListOfLocalTime() {
		List<LocalTime> times = Arrays.asList(LocalTime.of(9, 0), LocalTime.of(12, 30));
		List<String> expected = Arrays.asList("09:00", "12:30");
		List<String> actual = ConversorLocalDate.getHoraFormatada(times);
		assertEquals(expected, actual);
	}

	@Test
	void testGetDataFormatada_ValidDate() {
		LocalDate date = LocalDate.of(2024, 10, 1); // 01/10/2024
		String expected = "01/10/2024";
		String actual = ConversorLocalDate.getDataFormatada(date);
		assertEquals(expected, actual);
	}

}
