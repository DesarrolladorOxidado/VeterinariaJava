package com.guille.persistencia.dao;

import com.guille.modelos.Consulta;
import com.guille.modelos.HistoriaClinica;
import com.guille.modelos.Mascota;
import com.guille.modelos.TipoMascota;
import com.guille.persistencia.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO extends Dao{

    public MascotaDAO(ConexionBD conexionBD){
        super(conexionBD);
    }

    public Mascota registrarMascota(Mascota mascota, Connection connection) throws SQLException {

        Mascota mascotaBD = null;

        String sql = "INSERT INTO mascotas(" +
                "nombre_mascota," +
                "tipo_mascota," +
                "raza_mascota," +
                "fecha_nacimiento_mascota," +
                "peso_mascota," +
                "id_duenio_mascota," +
                "fecha_alta_mascota)" +
                "VALUES(?,?,?,?,?,?,?) " +
                "RETURNING id_mascota";

        try ( PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,mascota.getNombre());
            statement.setString(2,mascota.getTipo().getCodigo());
            statement.setString(3,mascota.getRaza());
            statement.setObject(4,mascota.getFechaNacimiento());
            statement.setDouble(5,mascota.getPeso());
            statement.setInt(6,mascota.getIdDuenio());
            statement.setObject(7,mascota.getFechaAlta());

            try( ResultSet resultado = statement.executeQuery()){
                if ( resultado.next()){
                    int idMascota = resultado.getInt("id_mascota");

                    mascotaBD = new Mascota(idMascota,mascota.getNombre(),mascota.getTipo(),mascota.getRaza(),mascota.getFechaNacimiento(),mascota.getPeso(),mascota.getIdDuenio(),mascota.getFechaAlta(),null);
                }
            }
        }

        return mascotaBD;
    }

    public List<Mascota> obtenerMascotasDeUnDuenio( int idDuenio ) throws SQLException{

        List<Mascota> mascotas = new ArrayList<>();

        String sql = "SELECT id_mascota,\n" +
                "    nombre_mascota,\n" +
                "    tipo_mascota,\n" +
                "    raza_mascota,\n" +
                "    fecha_nacimiento_mascota,\n" +
                "    peso_mascota,\n" +
                "    id_duenio_mascota,\n" +
                "    fecha_alta_mascota\n" +
                "FROM mascotas \n" +
                "WHERE id_duenio_mascota = ? " +
                "ORDER BY nombre_mascota";

        try( Connection connection = conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, idDuenio);

            try(ResultSet resultado = statement.executeQuery()){
                while (resultado.next()){
                    int idMascota = resultado.getInt("id_mascota");
                    String nombre = resultado.getString("nombre_mascota");
                    TipoMascota tipo= TipoMascota.obtenerTipoMascota(resultado.getString("tipo_mascota"));
                    String raza = resultado.getString("raza_mascota");
                    LocalDate fechaNacimiento = resultado.getObject("fecha_nacimiento_mascota", LocalDate.class);
                    double peso = resultado.getDouble("peso_mascota");
                    LocalDateTime fechaAlta = resultado.getObject("fecha_alta_mascota", LocalDateTime.class);

                    Mascota mascota = new Mascota(idMascota,nombre,tipo,raza,fechaNacimiento,peso,idDuenio,fechaAlta,null);

                    mascotas.add(mascota);
                }
            }

        }

        return mascotas;
    }
}
