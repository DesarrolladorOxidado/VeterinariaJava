package com.guille.controladores;

import com.guille.modelos.Mascota;
import com.guille.modelos.TipoMascota;
import com.guille.persistencia.dao.MascotaDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ControladorMascotas {

    private final MascotaDAO mascotaDAO;

    public ControladorMascotas(MascotaDAO mascotaDAO){
        this.mascotaDAO = mascotaDAO;
    }

    private Mascota crearMascota(String nombre, TipoMascota tipoMascota, String raza, LocalDate fechaNacimiento, double peso, int idDuenio){

        Mascota mascota = new Mascota(nombre, tipoMascota, idDuenio);
        mascota.setRaza(raza);
        mascota.setFechaNacimiento(fechaNacimiento);
        mascota.setPeso(peso);


        return mascota;
    }

    public Mascota registrarMascota(String nombre, TipoMascota tipo, String raza, LocalDate fechaNacimiento, double peso, int idDuenio) throws SQLException {
        Mascota mascota = crearMascota(nombre,tipo,raza,fechaNacimiento,peso, idDuenio);
        return this.mascotaDAO.registrarMascota(mascota);

    }

    public List<Mascota> obtenerMascotasDeUnDuenio(int idDuenio ) throws SQLException{
        return this.mascotaDAO.obtenerMascotasDeUnDuenio(idDuenio);
    }
}
