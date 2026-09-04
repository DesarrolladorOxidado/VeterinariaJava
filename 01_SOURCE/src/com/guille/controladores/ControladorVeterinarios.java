package com.guille.controladores;

import com.guille.modelos.TipoDocumento;
import com.guille.modelos.Veterinario;

import java.util.ArrayList;
import java.util.List;

public class ControladorVeterinarios {

    private List<Veterinario> veterinarios;

    public ControladorVeterinarios(){
        this.veterinarios = new ArrayList<>();
    }

    private Veterinario crearVeterinario( String nombre, String apellido, TipoDocumento tipoDocumento,String numeroDocumento, String telefono, String matricula){
        return new Veterinario(nombre,apellido,tipoDocumento,numeroDocumento,telefono,matricula);
    }

    public Veterinario registrarVeterinario(String nombre,String apellido,TipoDocumento tipoDocumento,String numeroDocumento, String telefono, String matricula){
        Veterinario resultado = crearVeterinario(nombre,apellido,tipoDocumento,numeroDocumento,telefono,matricula);
        this.veterinarios.add(resultado);
        return resultado;
    }

    public Veterinario obtenerVeterinarioConMatricula(String matricula){
        for (Veterinario veterinario : this.veterinarios){
            if ( veterinario.getMatricula().equalsIgnoreCase(matricula))
                return veterinario;
        }
        return null;
    }

    public Veterinario obtenerVeterinarioConDocumento(TipoDocumento tipoDocumento, String documento){
        for (Veterinario veterinario : this.veterinarios){
            if ( veterinario.getTipoDocumento() == tipoDocumento && veterinario.getNumeroDocumento().equals(documento))
                return veterinario;
        }
        return null;
    }

    public Veterinario obtenerVeterinarioPorIndice(int indice){
        return this.veterinarios.get(indice);
    }

    public int cantidadVeterinarios(){
        return this.veterinarios.size();
    }

    public boolean existenVeterinarios(){
        return !this.veterinarios.isEmpty();
    }

    public boolean existeVeterinarioConDocumento(TipoDocumento tipoDocumento, String documento){
        return obtenerVeterinarioConDocumento(tipoDocumento, documento) != null;
    }

    public boolean existeVeterinarioConMatricula(String matricula){
        return obtenerVeterinarioConMatricula(matricula) != null;
    }
}
