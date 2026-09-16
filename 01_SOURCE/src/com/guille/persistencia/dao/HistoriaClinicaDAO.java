package com.guille.persistencia.dao;

import com.guille.modelos.Consulta;
import com.guille.modelos.HistoriaClinica;
import com.guille.modelos.TipoDocumento;
import com.guille.modelos.Veterinario;
import com.guille.persistencia.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoriaClinicaDAO extends Dao {

    public HistoriaClinicaDAO(ConexionBD conexionBD){
        super(conexionBD);
    }

    public HistoriaClinica registrarHistoriaClinica(HistoriaClinica historiaClinica, Connection connection) throws SQLException {
        HistoriaClinica historiaClinicaBD = null;

        String sql = "INSERT INTO historias_clinicas(" +
                "mascota_historia_clinica," +
                "fecha_creacion_historia_clinica," +
                "fecha_actualizacion_historia_clinica)" +
                "VALUES(?,?,?)" +
                "RETURNING id_historia_clinica";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
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

        String sql = "SELECT H.id_historia_clinica,\n" +
                "H.mascota_historia_clinica,\n" +
                "H.fecha_creacion_historia_clinica,\n" +
                "H.fecha_actualizacion_historia_clinica,\n" +
                "C.id_consulta,\n" +
                "C.fecha_consulta,\n" +
                "C.motivo_consulta,\n" +
                "C.diagnostico_consulta,\n" +
                "C.tratamiento_consulta,\n" +
                "C.observaciones_consulta,\n" +
                "C.veterinario_consulta,\n" +
                "V.nombre_veterinario,\n" +
                "V.apellido_veterinario,\n" +
                "V.telefono_veterinario,\n" +
                "V.tipo_documento_veterinario,\n" +
                "V.numero_documento_veterinario,\n" +
                "V.matricula_veterinario,\n" +
                "V.fecha_alta_veterinario\n" +
                "FROM historias_clinicas H\n" +
                "LEFT JOIN consultas C ON H.id_historia_clinica = C.historia_clinica_consulta\n" +
                "LEFT JOIN veterinarios V ON C.veterinario_consulta = V.id_veterinario\n" +
                "WHERE H.mascota_historia_clinica = ?\n" +
                "ORDER BY C.fecha_consulta DESC";

        try( Connection connection = conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,idMascota);
            try( ResultSet resultSet = statement.executeQuery()){

                if ( resultSet.next()){
                    int idHistoriaClinica = resultSet.getInt("id_historia_clinica");
                    LocalDateTime fechaCreacionHistoriaClinica = resultSet.getObject("fecha_creacion_historia_clinica", LocalDateTime.class);
                    LocalDateTime fechaActualizacionHistoriaClinica = resultSet.getObject("fecha_actualizacion_historia_clinica", LocalDateTime.class);

                    List<Consulta> consultas = new ArrayList<>();


                    do{

                        int idConsulta = resultSet.getInt("id_consulta");

                        if ( !resultSet.wasNull()) {
                            LocalDateTime fechaConsulta = resultSet.getObject("fecha_consulta", LocalDateTime.class);
                            String motivo = resultSet.getString("motivo_consulta");
                            String diagnostico = resultSet.getString("diagnostico_consulta");
                            String tratamiento = resultSet.getString("tratamiento_consulta");
                            String observaciones = resultSet.getString("observaciones_consulta");
                            int idVeterinario = resultSet.getInt("veterinario_consulta");
                            String nombreVeterinario = resultSet.getString("nombre_veterinario");
                            String apellidoVeterinario = resultSet.getString("apellido_veterinario");
                            String telefonoVeterinario = resultSet.getString("telefono_veterinario");
                            String tipoDocumentoST =  resultSet.getString("tipo_documento_veterinario");
                            String numeroDocumento = resultSet.getString("numero_documento_veterinario");
                            String matriculaVeterinario = resultSet.getString("matricula_veterinario");
                            LocalDateTime fechaAltaVeterinario = resultSet.getObject("fecha_alta_veterinario", LocalDateTime.class);

                            TipoDocumento tipoDocumento = TipoDocumento.obtenerTipoDocumento(tipoDocumentoST);

                            Veterinario veterinario = new Veterinario(idVeterinario,nombreVeterinario,apellidoVeterinario,tipoDocumento,numeroDocumento,telefonoVeterinario,fechaAltaVeterinario,matriculaVeterinario);

                            Consulta consulta = new Consulta(idConsulta,fechaConsulta,motivo,diagnostico,tratamiento,observaciones,veterinario,idHistoriaClinica);

                            consultas.add(consulta);
                        }

                    }while ( resultSet.next());

                    historiaClinicaBD = new HistoriaClinica(idHistoriaClinica, idMascota, fechaCreacionHistoriaClinica, fechaActualizacionHistoriaClinica, consultas);

                }
            }
        }

        return historiaClinicaBD;
    }

    public void actualizarFecha(int idHistoriaClinica, LocalDateTime fechaActualizacion, Connection connection) throws SQLException{

        String sql = "UPDATE historias_clinicas " +
                "SET fecha_actualizacion_historia_clinica = ? " +
                "WHERE id_historia_clinica = ?";

        try( PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setObject(1, fechaActualizacion);
            statement.setInt(2, idHistoriaClinica);

            int resultado = statement.executeUpdate();

            if ( resultado != 1){
                throw new SQLException("No se pudo actualizar la historia clinica");
            }
        }
    }

    public int obtenerIdHistoriaClinica(int idMascota) throws SQLException{
        String sql = "SELECT id_historia_clinica\n" +
                     "FROM historias_clinicas\n" +
                     "WHERE mascota_historia_clinica = ?";

        try(Connection connection = conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,idMascota);

            try(ResultSet resultSet = statement.executeQuery()){
                if ( resultSet.next()){
                    return resultSet.getInt("id_historia_clinica");
                }
            }
        }

        return -1;
    }
}
