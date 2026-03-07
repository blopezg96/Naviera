package com.naviera.domain.navio;

import com.naviera.domain.valueObject.Posicion;
import com.naviera.domain.valueObject.Puerto;

public class EnNavegacion implements EstadoNavio {


    @Override
    public void validarInicioViaje(Navio navio){
        throw new IllegalStateException("El navio ya esta en navegacion");
    }

    @Override
    public void validarFinViaje(Navio navio) {
        if (navio.myEstado() == null){
            throw new IllegalStateException("El navio no esta en ningun viaje activo.");
        }
    }

    public void iniciarViaje(Navio navio, Posicion posicionInicial) {
        throw new IllegalStateException("El navio ya esta en navegacion");
    }


    public void finalizarViaje(Navio navio, Puerto puertoDestino) {
        navio.cambiarEstado(new EnPuerto());
        navio.llegarAPuerto(puertoDestino);
    }


    public String nombre() {
        return "EN_NAVEGACION";
    }

    @Override
    public void enviarAMantenimiento(Navio navio) {
        throw new IllegalStateException("No es posible entrar a mantenimiento mientras" +
                " se encuentre en navegacion. ");
    }

    @Override
    public void volverDeMantenimiento(Navio navio){
        throw new IllegalStateException("El navio no se encuentra en Mantenimiento");
    }
}
