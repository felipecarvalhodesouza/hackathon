package br.com.fiap.hackathon.main.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConversorLocalDate {
	
	private ConversorLocalDate() {}
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static String getHoraFormatada(LocalTime data) {
        return data.format(formatter);
	}
	
	public static LocalTime getHoraLocalTime(String hora) {
        return LocalTime.parse(hora, formatter);
	}
	
	public static List<String> getHoraFormatada(List<LocalTime> datas) {
        return datas.stream().map(ConversorLocalDate::getHoraFormatada).toList();
	}

	public static String getDataFormatada(LocalDate data) {
		return data.format(dateFormatter);
	}
}
