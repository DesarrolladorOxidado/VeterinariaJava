package com.guille.controladores;

import com.guille.modelos.TipoDocumento;
import com.guille.modelos.Veterinario;
import com.guille.persistencia.dao.VeterinarioDAO;

import java.sql.SQLException;
import java.util.List;

public class ControladorVeterinarios {

    private final VeterinarioDAO veterinarioDAO;

    public ControladorVeterinarios(VeterinarioDAO veterinarioDAO){
        this.veterinarioDAO = veterinarioDAO;
    }

    private Veterinario crearVeterinario( String nombre, String apellido, TipoDocumento tipoDocumento,String numeroDocumento, String telefono, String matricula){
        return new Veterinario(nombre,apellido,tipoDocumento,numeroDocumento,telefono,matricula);
    }

    public Veterinario registrarVeterinario(String nombre,String apellido,TipoDocumento tipoDocumento,String numeroDocumento, String telefono, String matricula) throws SQLException {
        Veterinario veterinario = crearVeterinario(nombre, apellido, tipoDocumento, numeroDocumento, telefono, matricula);
        return this.veterinarioDAO.registrarVeterinario(veterinario);
    }

    public List<Veterinario> obtenerVeterinarios() throws SQLException{
        return this.veterinarioDAO.obtenerVeterinarios();
    }

    public Veterinario obtenerVeterinarioConMatricula(String matricula) throws SQLException{
        return this.veterinarioDAO.obtenerVeterinarioPorMatricula(matricula);
    }

    public Veterinario obtenerVeterinarioConDocumento(TipoDocumento tipoDocumento, String numeroDocumento) throws SQLException{
        return this.veterinarioDAO.obtenerVeterinarioPorDocumento(tipoDocumento,numeroDocumento);
    }

    public boolean existeVeterinarioConDocumento(TipoDocumento tipoDocumento, String documento) throws SQLException{
        return obtenerVeterinarioConDocumento(tipoDocumento, documento) != null;
    }

    public boolean existeVeterinarioConMatricula(String matricula) throws SQLException{
        return obtenerVeterinarioConMatricula(matricula) != null;
    }

    public boolean existenVeterinarios() throws SQLException{
        return this.veterinarioDAO.existenVeterinarios();
    }
}
