package com.naviera.domain.navio;


import com.naviera.domain.valueObject.Posicion;
import com.naviera.domain.valueObject.Puerto;

public class EnPuerto implements EstadoNavio {

    @Override
    public void validarInicioViaje(Navio navio){
        if(navio.getPuertoActual() == null){
            throw new IllegalStateException("El navio no tiene ningun puerto asignado.");
        }
    }

    @Override
    public void validarFinViaje(Navio navio) {

    }

    /*
    @Override
    public void iniciarViaje(Navio navio, Posicion posicionInicial) {
        navio.validarTripulacion();
        navio.cambiarEstado(new EnNavegacion());
        navio.salirDePuerto(posicionInicial);

    } */

    @Override
    public void finalizarViaje(Navio navio, Puerto puertoDestino) {
        throw new IllegalStateException("El navio ya esta en viaje");
    }



    @Override
    public String nombre() {
        return "EN_PUERTO";
    }

    @Override
    public void enviarAMantenimiento(Navio navio){
        navio.cambiarEstado(new EnMantenimiento());
    }

    @Override
    public void volverDeMantenimiento(Navio navio){

            throw new IllegalStateException("El navio ya ha vuelto de mantenimiento");



    }


}
