package com.guille.persistencia.dao;

import com.guille.modelos.Consulta;
import com.guille.modelos.Veterinario;
import com.guille.persistencia.ConexionBD;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO extends Dao{

    public ConsultaDAO(ConexionBD conexionBD){
        super(conexionBD);
    }

    public Consulta registrarConsulta(Consulta consulta, Connection connection) throws SQLException {
        Consulta consultaBD = null;
        VeterinarioDAO veterinarioDAO = new VeterinarioDAO(conexionBD);

        String sql = "INSERT INTO consultas(fecha_consulta," +
                "motivo_consulta," +
                "diagnostico_consulta," +
                "tratamiento_consulta," +
                "observaciones_consulta," +
                "veterinario_consulta, " +
                "historia_clinica_consulta)" +
                "VALUES(?,?,?,?,?,?,?)" +
                "RETURNING id_consulta";

        try( PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setObject(1,consulta.getFecha());
            statement.setString(2,consulta.getMotivo());
            statement.setString(3,consulta.getDiagnostico());
            statement.setString(4,consulta.getTratamiento());
            statement.setString(5,consulta.getObservaciones());
            statement.setInt(6, consulta.getVeterinario().getIdVeterinario());
            statement.setInt(7, consulta.getIdHistoriaClinica());

            try(ResultSet resultado = statement.executeQuery()){
                if ( resultado.next()){
                    int id = resultado.getInt("id_consulta");
                    Veterinario veterinario = veterinarioDAO.obtenerVeterinarioPorId(consulta.getVeterinario().getIdVeterinario());

                    consultaBD = new Consulta(id,consulta.getFecha(),consulta.getMotivo(),consulta.getDiagnostico(),consulta.getTratamiento(),consulta.getObservaciones(),veterinario,consulta.getIdHistoriaClinica());
                }
            }
        }

        return consultaBD;
    }

    public List<Consulta> obtenerConsultas(int idHistoriaClinica) throws SQLException{
        List<Consulta> consultas = new ArrayList<>();

        String sql = "SELECT * FROM consultas WHERE historia_clinica_consulta = ? ORDER BY fecha_consulta DESC";

        try(Connection connection = conexionBD.obtenerConexion();PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idHistoriaClinica);

            VeterinarioDAO veterinarioDAO = new VeterinarioDAO(conexionBD);

            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    int idConsulta = resultSet.getInt("id_consulta");
                    LocalDateTime fechaConsulta = resultSet.getObject("fecha_consulta",LocalDateTime.class);
                    String motivo = resultSet.getString("motivo_consulta");
                    String diagnostico = resultSet.getString("diagnostico_consulta");
                    String tratamiento = resultSet.getString("tratamiento_consulta");
                    String observaciones = resultSet.getString("observaciones_consulta");
                    int idVeterinario = resultSet.getInt("veterinario_consulta");

                    Veterinario veterinario = veterinarioDAO.obtenerVeterinarioPorId(idVeterinario);
                    Consulta consulta = new Consulta(idConsulta,fechaConsulta,motivo,diagnostico,tratamiento,observaciones,veterinario,idHistoriaClinica);

                    consultas.add(consulta);
                }
            }
        }

        return consultas;
    }
}
