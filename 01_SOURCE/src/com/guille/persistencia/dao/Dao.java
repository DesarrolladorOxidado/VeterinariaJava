package com.guille.persistencia.dao;

import com.guille.persistencia.ConexionBD;

public abstract class Dao {

    protected ConexionBD conexionBD;

    protected Dao(ConexionBD conexionBD){
        this.conexionBD = conexionBD;
    }
}
