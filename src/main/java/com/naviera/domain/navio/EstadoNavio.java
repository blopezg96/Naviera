package com.naviera.domain.navio;

import com.naviera.domain.valueObject.Posicion;
import com.naviera.domain.valueObject.Puerto;

public interface EstadoNavio {

    void validarInicioViaje(Navio navio);

    void validarFinViaje(Navio navio);

    // void iniciarViaje(Navio navio, Posicion posicionInicial);

    void finalizarViaje(Navio navio, Puerto puertoDestino);

    void enviarAMantenimiento(Navio navio);

    void volverDeMantenimiento(Navio navio);

    String nombre();


}
