package com.guille.servicios;

import com.guille.modelos.HistoriaClinica;
import com.guille.modelos.Mascota;
import com.guille.persistencia.GestorTransacciones;
import com.guille.persistencia.dao.HistoriaClinicaDAO;
import com.guille.persistencia.dao.MascotaDAO;

import java.sql.SQLException;

public class RegistroMascotaService {

    private final MascotaDAO mascotaDAO;
    private final HistoriaClinicaDAO historiaClinicaDAO;
    private final GestorTransacciones gestorTransacciones;

    public RegistroMascotaService(MascotaDAO mascotaDAO, HistoriaClinicaDAO historiaClinicaDAO, GestorTransacciones gestorTransacciones){
        this.mascotaDAO = mascotaDAO;
        this.historiaClinicaDAO = historiaClinicaDAO;
        this.gestorTransacciones = gestorTransacciones;
    }

    public Mascota registrarMascota( Mascota mascota) throws SQLException{

        return gestorTransacciones.ejecutar( connection -> {
            Mascota mascotaBD = mascotaDAO.registrarMascota(mascota,connection);

            HistoriaClinica historiaClinica = new HistoriaClinica(mascotaBD.getIdMascota());

            HistoriaClinica historiaClinicaBD = historiaClinicaDAO.registrarHistoriaClinica(historiaClinica,connection);

            return new Mascota(mascotaBD.getIdMascota(),mascotaBD.getNombre(),mascotaBD.getTipo(),mascotaBD.getRaza(),mascotaBD.getFechaNacimiento(),mascotaBD.getPeso(),mascotaBD.getIdDuenio(),mascotaBD.getFechaAlta(),historiaClinicaBD);

        });
    }
}
