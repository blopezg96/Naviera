package com.naviera.domain.viaje;


import com.naviera.domain.valueObject.Puerto;
import com.naviera.domain.viaje.*;
import com.naviera.domain.navio.NavioTest;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ViajeTest {

    private static Puerto fastDestino(){
        return new Puerto("Puerto destino", "pais destino", 45.0, 100.0);
    }

    private static Puerto fastOrigen(){
        return new Puerto("Puerto Origen", "pais de origen", 35.0, 90.0);
    }

    private Viaje fastViaje() {

        return new Viaje(
                1L,
                NavioTest.fastPuerto(),
                fastDestino(),
                NavioTest.fastPosicion()

        );
    }


    // NO PUEDE INICIAR VIAJE DOS VECES


    @Test
    public void noIniciarViajeDosVeces(){

        Viaje viaje = fastViaje();

        viaje.iniciar();

        assertThrows(IllegalStateException.class, viaje::iniciar);

    }

    // VIAJE NO PUEDE FINALIZAR DOS VECES
    @Test
    public void noFinalizarDosVeces(){

        Viaje viaje = fastViaje();
        viaje.iniciar();
        viaje.finalizar();
        assertThrows(IllegalStateException.class, viaje::finalizar);
    }

    // NO PUEDE FINALIZAR SIN INICIAR
    @Test
    public void noFinalizarSinIniciar(){

        Viaje viaje = fastViaje();

        assertThrows(IllegalStateException.class, viaje::finalizar);

    }

    // NO PUEDE ACTUALIZAR POSICION SI NO ESTA EN CURSO
    @Test
    public void noActualizarPosicionSiNoEstaEnCurso(){

        Viaje viaje = fastViaje();

        assertThrows(IllegalStateException.class, ()-> viaje.actualizarPosicion(NavioTest.fastPosicion(),
                120.0,
                "Sin Observaciones"));

    }

    //AL INICIAR CAMBIA A EN CURSO
    @Test
    public void iniciarCambiaAEnCurso(){

        Viaje viaje = fastViaje();

        viaje.iniciar();

        assertSame(EstadoViaje.EN_CURSO, viaje.getEstado());

    }

    //AL FINALIZAR CAMBIA A FINALIZADO
    @Test
    public void finalizarCambiaAFinalizado(){
        Viaje viaje = fastViaje();
        viaje.iniciar();
        viaje.finalizar();

        assertSame(EstadoViaje.FINALIZADO, viaje.getEstado());
    }

    //VERIFICAR QUE SE CREA UNA ENTRADA EN LOGBOOK
    @Test
    public void comprobarCreacionDeEntrada(){

        Viaje viaje = fastViaje();
        viaje.iniciar();
        int lastSize = viaje.getLogbook().size();
        viaje.actualizarPosicion(NavioTest.fastPosicion(), 0.0, "Sin observaciones.");
        int newSize = viaje.getLogbook().size();

        assertEquals(lastSize + 1, newSize);

    }

    // NO ACTUALIZAR POSICION DESPUES DE FINALIZAR
    @Test
    public void noActualizarPosicionDespuesDeFinalizar(){

        Viaje viaje = fastViaje();
        viaje.iniciar();
        viaje.finalizar();

        assertThrows(IllegalStateException.class, () -> viaje.actualizarPosicion(
                        NavioTest.fastPosicion(),
                        0.0,
                        "Sin observaciones"));

    }

    // VIAJE NO PUEDE INICAR DOS VECES
    @Test
    public void viajeNoPuedeIniciarseDosVeces(){
        Viaje viaje = fastViaje();
        viaje.iniciar();
        LocalDateTime salidaValida = viaje.getSalida();
        assertThrows(IllegalStateException.class, viaje::iniciar); // verificar que lanza excepcion
        LocalDateTime verificarSalida = viaje.getSalida();

        assertEquals(salidaValida, verificarSalida);


    }

    // CALCULAR DISTANCIA ENTRE PUERTOS

    @Test
    public void distanciaEntrePuertos(){

        Puerto origen = fastOrigen();
        Puerto destino = fastDestino();

        Viaje viaje = new Viaje(1L, origen, destino, NavioTest.fastPosicion());
        viaje.calcularDistancia();       // este metodo asigna valor a la propiedad "distanciaDeViaje"
        assertTrue(viaje.getDistanciaDeViaje() > 0);    // getter accede a "distanciaDeViaje"

    }

}
