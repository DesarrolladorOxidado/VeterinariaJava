package com.guille.modelos;

import java.time.LocalDate;
import java.time.Period;

public class Mascota {

    private String nombre;
    private TipoMascota tipo;
    private String raza;
    private LocalDate fechaNacimiento;
    private double peso;
    private HistoriaClinica historiaClinica;

    public Mascota(String nombre, TipoMascota tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.historiaClinica = new HistoriaClinica();
    }

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

    public HistoriaClinica getHistoriaClinica(){ return this.historiaClinica; }

    @Override
    public String toString() {
        return "Mascota{" +
                "nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", raza='" + raza + '\'' +
                ", fecha nacimiento = " + fechaNacimiento +
                ", edad=" + getEdad() +
                ", peso=" + peso +
                ", historiaClinica=" + historiaClinica +
                '}';
    }
}
