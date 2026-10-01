package com.guille.configuracion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;

public class FechaHora {

    private static final DateTimeFormatter FORMATO_ENTRADA_FECHA = DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter FORMATO_SALIDA_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_SALIDA_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    //Máximo 6 dígitos de precisión
    public static LocalDateTime ahora() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.MICROS);
    }

    public static LocalDate parsearFecha( String fecha ){
        return LocalDate.parse(fecha, FORMATO_ENTRADA_FECHA);
    }

    public static String formatearFechaHora(LocalDateTime fechaHora ){
        return fechaHora.format(FORMATO_SALIDA_FECHA_HORA);
    }
    public static String formatearFecha(LocalDate fecha) {
        return fecha.format(FORMATO_SALIDA_FECHA);
    }

}
