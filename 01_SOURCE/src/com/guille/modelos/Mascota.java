package com.guille.modelos;

import com.guille.configuracion.FechaHora;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Mascota {

    public static int MAXIMA_EDAD = 50;
    public static double MAXIMO_PESO = 150.0;

    private int idMascota;
    private String nombre;
    private TipoMascota tipo;
    private String raza;
    private LocalDate fechaNacimiento;
    private double peso;
    private int idDuenio;
    private HistoriaClinica historiaClinica;
    private boolean activo;

    private final LocalDateTime fechaAlta;


    public Mascota(String nombre, TipoMascota tipo, int idDuenio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.idDuenio = idDuenio;
        this.fechaAlta = FechaHora.ahora();
        this.activo = true;
    }

    public Mascota(int idMascota, String nombre, TipoMascota tipo, String raza, LocalDate fechaNacimiento, double peso, int idDuenio, LocalDateTime fechaAlta, boolean activo, HistoriaClinica historiaClinica) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.idDuenio = idDuenio;
        this.fechaAlta = fechaAlta;
        this.activo = activo;
        this.historiaClinica = historiaClinica;
    }

    public int getIdMascota(){ return this.idMascota; }
    public String getNombre() {
        return nombre;
    }

    //A diferencia de las personas, las mascotas pueden cambiar de nombre
    //en especial, si fueron adoptadas pero siguen atendiendose en la misma
    //veterinaria
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoMascota getTipo() {
        return tipo;
    }

    //Este metodo permite corregir errores de carga de datos
    //no es porque un animal pueda cambiar de tipo. Su uso
    //está destinado exclusivamente ante un error
    public void setTipo(TipoMascota tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public LocalDate getFechaNacimiento(){
        return this.fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return Period.between(this.fechaNacimiento,LocalDate.now()).getYears();
    }


    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getIdDuenio(){ return this.idDuenio; }

    public void setIdDuenio( int idDuenio ){ this.idDuenio = idDuenio; }

    public LocalDateTime getFechaAlta(){ return this.fechaAlta;}

    public boolean getActivo(){ return this.activo; }

    public HistoriaClinica getHistoriaClinica(){ return this.historiaClinica; }

    @Override
    public String toString() {
        return "Mascota{ idMascota = '" + this.idMascota +
                "nombre='" + nombre + '\'' +
                ", tipo=" + tipo.getIdTipoMascota() + " - " + tipo.getDescripcionTipoMascota() + '\''+
                ", raza='" + raza + '\'' +
                ", fecha nacimiento = " + fechaNacimiento +
                ", edad=" + getEdad() +
                ", peso=" + peso +
                ", idDuenio = " + idDuenio +
                ", fechaAlta = " + fechaAlta +
                ", activo = " + activo +
                ", historiaClinica=" + historiaClinica +
                '}';
    }

}
