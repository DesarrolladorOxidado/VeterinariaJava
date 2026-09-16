package com.guille.persistencia.dao;

import com.guille.modelos.Consulta;
import com.guille.modelos.Veterinario;
import com.guille.persistencia.ConexionBD;

import java.sql.*;

public class ConsultaDAO extends Dao{

    public ConsultaDAO(ConexionBD conexionBD){
        super(conexionBD);
    }

    public Consulta registrarConsulta(Consulta consulta, Connection connection) throws SQLException {
        Consulta consultaBD = null;

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

                    consultaBD = new Consulta(id,consulta.getFecha(),consulta.getMotivo(),consulta.getDiagnostico(),consulta.getTratamiento(),consulta.getObservaciones(),consulta.getVeterinario(),consulta.getIdHistoriaClinica());
                }
            }
        }

        return consultaBD;
    }

}
