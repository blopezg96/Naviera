package com.naviera.domain.navio;

import com.naviera.domain.tripulacion.RolTripulante;
import com.naviera.domain.tripulacion.Tripulante;
import com.naviera.domain.valueObject.Posicion;
import com.naviera.domain.valueObject.Puerto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NavioTest {

    // metodo Helper que contiene el constructor mas reciente de navio.
    private Navio navioBase(){
        return new Navio(
                1l,
                "Test",
                new Puerto("Mi Puerto", "Mi pais",20, 35)
        );
    }

    // Metodo  generico para construir rapidamente Posicion.
    public static Posicion fastPosicion(){

        Posicion myPosition = new Posicion(45, 120.0);
        return myPosition;

    }

    // Metodo generico para costruir rapidamente Puerto.
    public static Puerto fastPuerto(){

        Puerto myPuerto = new Puerto("Mi puerto", "Mi pais", 45, 50);
        return myPuerto;

    }

    public static Navio crearNavioValido(){
        Puerto puerto = new Puerto("Mi puerto", "Mi pais", 35, 70);
        return new Navio(2L, "Mi Navio", puerto);
    }



    @Test
    void navioDebeIniciarEnPuerto(){

        Navio navio = navioBase();

        assertTrue(navio.myEstado() instanceof EnPuerto);

        //assertInstanceOf(EnPuerto.class, navio.myEstado());
    }

    @Test
    void navioCambioAViajeCuandoIniciaViaje(){

        Navio navio = navioBase();
        navio.agregarTripulacion(new Tripulante(0123456L,"Primer Tripulante", RolTripulante.CAPITAN));
        navio.agregarTripulacion(new Tripulante(01234L,"Segundo tripulante", RolTripulante.OFICIAL));
        navio.agregarTripulacion(new Tripulante(123406L, "Tercer tripulante", RolTripulante.CAPITAN));
        navio.iniciarViaje(fastPosicion(),fastPuerto());

        assertInstanceOf(EnNavegacion.class, navio.myEstado());
    }

    @Test
    void navioValidaLaTripulacion(){

        Navio navio = navioBase();

        assertThrows(IllegalStateException.class, () -> navio.iniciarViaje(fastPosicion(),fastPuerto()));
    }

    @Test
    void navioTripulacionNoValida(){
        Navio navio = navioBase();

        IllegalStateException ex =
                assertThrows(IllegalStateException.class, () -> navio.iniciarViaje(fastPosicion(),fastPuerto()));

        assertEquals("El navio no tiene tripulacion asignada", ex.getMessage());
    }

    @Test
    void noIniciarViajeSinCapitan(){
        Navio navio = navioBase();
        navio.agregarTripulacion(new Tripulante(012345L, "my Oficial", RolTripulante.OFICIAL));

        assertThrows(IllegalStateException.class, () -> navio.iniciarViaje(fastPosicion(), fastPuerto()));
    }

    @Test
    void noIniciarSinOficial(){
        Navio navio = navioBase();
        navio.agregarTripulacion(new Tripulante(2L, "MyCapitan", RolTripulante.CAPITAN));

        assertThrows(IllegalStateException.class, () -> navio.iniciarViaje(fastPosicion(),fastPuerto()));
    }

    @Test
    void iniciarConCapitanYOficial(){
        Navio navio = navioBase();
        navio.agregarTripulacion(new Tripulante(12L, "MyCapitan", RolTripulante.CAPITAN));
        navio.agregarTripulacion(new Tripulante(1L, "MyOficial", RolTripulante.OFICIAL));

        navio.iniciarViaje(fastPosicion(), fastPuerto());

        assertTrue(navio.navegando());
    }

    // test de metodos de la clase EnNavegacion

    @Test
    void ENIniciarViajeTest(){
        // Metodo que testea que no se puede iniciar viaje si ya esta en navegacion.
        Navio navio = navioBase();
        navio.agregarTripulacion(new Tripulante(1L, "Capitan", RolTripulante.CAPITAN));
        navio.agregarTripulacion(new Tripulante(2L,"Oficial", RolTripulante.OFICIAL));

        navio.iniciarViaje(fastPosicion(), fastPuerto());

        assertThrows(IllegalStateException.class, () -> navio.iniciarViaje(fastPosicion(), fastPuerto()));

    }

    @Test
    void ENfinalizarViajeTest(){

        Navio navio = navioBase();
        navio.agregarTripulacion(new Tripulante(3L, "Capitan", RolTripulante.CAPITAN));
        navio.agregarTripulacion(new Tripulante(4L, "Oficial", RolTripulante.OFICIAL));

        navio.iniciarViaje(fastPosicion(),fastPuerto());
        navio.finalizarViaje(new Puerto("puerto", "pais", 21, 90));

        assertInstanceOf(EnPuerto.class, navio.myEstado());
    }

    @Test
    void ENaMantenimiento(){
        Navio navio = navioBase();
        navio.agregarTripulacion(new Tripulante(1L, "Oficial", RolTripulante.OFICIAL));
        navio.agregarTripulacion(new Tripulante(2L,"My Capitan", RolTripulante.CAPITAN));
        navio.iniciarViaje(fastPosicion(),fastPuerto());

        assertThrows(IllegalStateException.class, navio::aMantenimiento);
    }

    // test de metodos de la clase en Puerto

    @Test
    void EPfinalizarViajeTest(){
        Navio navio = navioBase();
        navio.agregarTripulacion(new Tripulante(1L, "Tripulante", RolTripulante.CAPITAN));
        navio.agregarTripulacion(new Tripulante(2L, "Oficial", RolTripulante.OFICIAL));

        navio.iniciarViaje(fastPosicion(),fastPuerto());
        navio.finalizarViaje(new Puerto("puerto", "pais", 27, 120));

        IllegalStateException ex =
        assertThrows(IllegalStateException.class, () -> navio.finalizarViaje(fastPuerto()));

        assertEquals("No hay ningun viaje activo", ex.getMessage());
    }

    // Test de metodos de la clase en Mantenimiento

    @Test
    void EMiniciarViaje(){

        Navio navio = navioBase();
        navio.aMantenimiento();

        assertThrows(IllegalStateException.class, () -> navio.iniciarViaje(fastPosicion(),fastPuerto()));

    }

    @Test
    void EMfinalizarViaje(){
        Navio navio = navioBase();
        navio.aMantenimiento();

        assertThrows(IllegalStateException.class, () -> navio.iniciarViaje(fastPosicion(),fastPuerto()));
    }

    @Test
    void EMaMantenimiento(){
        Navio navio = navioBase();
        navio.aMantenimiento();

        assertThrows(IllegalStateException.class, navio::aMantenimiento);
    }

    @Test
    void EMfinalizarMantenimiento(){
        Navio navio =  navioBase();
        navio.aMantenimiento();
        navio.finalizarMantenimiento();

        assertInstanceOf(EnPuerto.class, navio.myEstado());
    }






}
