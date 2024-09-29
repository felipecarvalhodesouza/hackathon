package br.com.fiap.hackathon.main.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ConversorLocalDate {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

	public static String getHoraFormatada(LocalTime data) {
        return data.format(formatter);
	}
	
	public static LocalTime getHoraLocalTime(String hora) {
        return LocalTime.parse(hora, formatter);
	}
	
	public static List<String> getHoraFormatada(List<LocalTime> datas) {
        return datas.stream().map(ConversorLocalDate::getHoraFormatada).collect(Collectors.toList());
	}
}
