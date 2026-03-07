package com.naviera.domain.navio;

import com.naviera.domain.valueObject.Posicion;
import com.naviera.domain.valueObject.Puerto;

public class EnMantenimiento implements EstadoNavio {


    @Override
    public void validarInicioViaje(Navio navio){
        throw new IllegalStateException("El navio se encuentra en mantenimiento");
    }

    @Override
    public void validarFinViaje(Navio navio){
        throw new IllegalStateException("El navio se encuentra en mantenimiento. ");
    }

    public void iniciarViaje(Navio navio, Posicion posicionInicial) {
        throw new IllegalStateException("El navio esta en mantenimiento");
    }



    public void finalizarViaje(Navio navio, Puerto puertoDestino) {
        throw new IllegalStateException("El navio esta en mantenimiento");
    }

    @Override
    public void volverDeMantenimiento(Navio navio) {
        navio.cambiarEstado(new EnPuerto());
    }

    @Override
    public String nombre() {
        return "EN_MANTENIMIENTO";
    }

    @Override
    public void enviarAMantenimiento(Navio navio){
        throw new IllegalStateException("El navio ya esta en mantenimiento");
    }


}
