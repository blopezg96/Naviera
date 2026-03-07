package com.naviera.domain.tripulacion;

import com.naviera.domain.tripulacion.Tripulante;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TripulanteTest {

    @Test
    void crearTripulacionCorrectamente(){
        Tripulante t = new Tripulante(2L, "Nombre", RolTripulante.CAPITAN);

        assertEquals(2L, t.getId());
        assertEquals("Nombre", t.getNombre());
        assertEquals(RolTripulante.CAPITAN, t.getRol());

    }

    @Test
    void avoidIdNotNull(){
        assertThrows(IllegalArgumentException.class,
                () -> new Tripulante(null, "Nombre", RolTripulante.CAPITAN));
    }

    @Test
    void avoidNameNull(){
        assertThrows(IllegalArgumentException.class,
                () -> new Tripulante(2L, null, RolTripulante.CAPITAN));
    }

    @Test
    void avoidRolNull(){
        assertThrows(IllegalArgumentException.class,
                () -> new Tripulante(1L, "Nombre", null));
    }


    @Test
    void idNotEquals(){

        Tripulante t1 = new Tripulante(1L, "Nombre", RolTripulante.CAPITAN);
        Tripulante t2 = new Tripulante(2L, "Nombre", RolTripulante.OFICIAL);

        assertNotEquals(t1, t2);
    }
}
