package com.guille.persistencia.dao;

import com.guille.modelos.Consulta;
import com.guille.modelos.HistoriaClinica;
import com.guille.persistencia.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class HistoriaClinicaDAO extends Dao {

    public HistoriaClinicaDAO(ConexionBD conexionBD){
        super(conexionBD);
    }

    public HistoriaClinica registrarHistoriaClinica(HistoriaClinica historiaClinica) throws SQLException {
        HistoriaClinica historiaClinicaBD = null;

        String sql = "INSERT INTO historias_clinicas(" +
                "mascota_historia_clinica," +
                "fecha_creacion_historia_clinica," +
                "fecha_actualizacion_historia_clinica)" +
                "VALUES(?,?,?)" +
                "RETURNING id_historia_clinica";

        try(Connection connection = conexionBD.obtenerConexion();PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,historiaClinica.getIdMascota());
            statement.setObject(2, historiaClinica.getFechaCreacion());
            statement.setObject(3,historiaClinica.getFechaActualizacion());

            try(ResultSet resultado = statement.executeQuery()){
                if ( resultado.next()){
                    int id = resultado.getInt("id_historia_clinica");

                    historiaClinicaBD = new HistoriaClinica(id,historiaClinica.getIdMascota(),historiaClinica.getFechaCreacion(),historiaClinica.getFechaActualizacion(), Collections.emptyList());
                }
            }
        }

        return historiaClinicaBD;
    }

    public HistoriaClinica obtenerHistoriaClinica( int idMascota ) throws SQLException{
        HistoriaClinica historiaClinicaBD = null;

        String sql = "SELECT * FROM historias_clinicas WHERE mascota_historia_clinica = ?";

        ConsultaDAO consultaDAO = new ConsultaDAO(conexionBD);

        try( Connection connection = conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,idMascota);
            try( ResultSet resultSet = statement.executeQuery()){
                if ( resultSet.next()){
                    int idHistoriaClinica = resultSet.getInt("id_historia_clinica");
                    LocalDateTime fechaCreacion = resultSet.getObject("fecha_creacion_historia_clinica",LocalDateTime.class);
                    LocalDateTime fechaActualizacion = resultSet.getObject("fecha_actualizacion_historia_clinica",LocalDateTime.class);

                    List<Consulta> consultas = consultaDAO.obtenerConsultas(idHistoriaClinica);

                    historiaClinicaBD = new HistoriaClinica(idHistoriaClinica,idMascota,fechaCreacion,fechaActualizacion,consultas);
                }
            }
        }

        return historiaClinicaBD;
    }

    public void actualizarFecha(HistoriaClinica historiaClinica) throws SQLException{

        String sql = "UPDATE historias_clinicas " +
                "SET fecha_actualizacion_historia_clinica = ? " +
                "WHERE id_historia_clinica = ?";

        try( Connection connection = conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setObject(1, historiaClinica.getFechaActualizacion());
            statement.setInt(2, historiaClinica.getId());

            int resultado = statement.executeUpdate();

            if ( resultado != 1){
                throw new SQLException("No se pudo actualizar la historia clinica");
            }
        }
    }
}
