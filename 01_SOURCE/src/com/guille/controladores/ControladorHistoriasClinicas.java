package com.guille.controladores;

import com.guille.modelos.HistoriaClinica;
import com.guille.persistencia.dao.HistoriaClinicaDAO;

import java.sql.SQLException;

public class ControladorHistoriasClinicas {

    private final HistoriaClinicaDAO historiaClinicaDAO;

    public ControladorHistoriasClinicas( HistoriaClinicaDAO historiaClinicaDAO){
        this.historiaClinicaDAO = historiaClinicaDAO;
    }

    public HistoriaClinica obtenerHistoriaClinica( int idMascota ) throws SQLException{
        return this.historiaClinicaDAO.obtenerHistoriaClinica(idMascota);
    }

    public int obtenerIdHistoriaClinica( int idMascota ) throws SQLException{
        return this.historiaClinicaDAO.obtenerIdHistoriaClinica(idMascota);
    }
}
