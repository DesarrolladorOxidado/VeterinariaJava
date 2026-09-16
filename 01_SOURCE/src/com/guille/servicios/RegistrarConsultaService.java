package com.guille.servicios;

import com.guille.modelos.Consulta;
import com.guille.persistencia.GestorTransacciones;
import com.guille.persistencia.dao.ConsultaDAO;
import com.guille.persistencia.dao.HistoriaClinicaDAO;

import java.sql.SQLException;

public class RegistrarConsultaService {

    private final ConsultaDAO consultaDAO;
    private final HistoriaClinicaDAO historiaClinicaDAO;
    private final GestorTransacciones gestorTransacciones;

    public RegistrarConsultaService(ConsultaDAO consultaDAO, HistoriaClinicaDAO historiaClinicaDAO, GestorTransacciones gestorTransacciones){
        this.consultaDAO = consultaDAO;
        this.historiaClinicaDAO = historiaClinicaDAO;
        this.gestorTransacciones = gestorTransacciones;
    }

    public Consulta registrarConsulta(Consulta consulta) throws SQLException {

        return gestorTransacciones.ejecutar( connection -> {
            Consulta consultaBD = consultaDAO.registrarConsulta(consulta,connection);
            historiaClinicaDAO.actualizarFecha(consultaBD.getIdHistoriaClinica(),consultaBD.getFecha(), connection);

            return consultaBD;
        });
    }
}
