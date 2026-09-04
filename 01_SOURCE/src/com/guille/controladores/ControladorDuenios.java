package com.guille.controladores;

import com.guille.modelos.Duenio;
import com.guille.modelos.Mascota;
import com.guille.modelos.TipoDocumento;
import com.guille.persistencia.dao.DuenioDAO;

import java.sql.SQLException;
import java.util.List;

public class ControladorDuenios {

    private DuenioDAO duenioDAO;

    public ControladorDuenios(){
        this.duenioDAO = new DuenioDAO();
    }

    private Duenio crearDuenio( String nombre, String apellido, TipoDocumento tipoDocumento,String numeroDocumento, String telefono){
        return new Duenio(nombre,apellido,tipoDocumento,numeroDocumento,telefono);
    }

    public Duenio registrarDuenio(String nombre, String apellido,TipoDocumento tipoDocumento, String numeroDocumento, String telefono) throws SQLException{
        Duenio duenio = crearDuenio(nombre,apellido,tipoDocumento,numeroDocumento,telefono);
        return this.duenioDAO.registrarDuenio(duenio);

    }

    public boolean existeDuenioConDocumento(TipoDocumento tipoDocumento, String documento) throws SQLException{
        return obtenerDuenioPorDocumento(tipoDocumento,documento) != null;
    }

    public List<Duenio> obtenerDuenios() throws SQLException {
        return this.duenioDAO.obtenerDuenios();
    }

    public Duenio obtenerDuenioPorDocumento(TipoDocumento tipoDocumento, String numeroDocumento) throws SQLException{
        return this.duenioDAO.obtenerDuenioPorDocumento(tipoDocumento,numeroDocumento);
    }

    public void agregarMascota(Duenio duenio, Mascota mascota){
            duenio.agregarMascota(mascota);
    }
}
