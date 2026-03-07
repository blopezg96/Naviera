package com.naviera.domain.navio;

import com.naviera.domain.tripulacion.RolTripulante;
import com.naviera.domain.tripulacion.Tripulante;
import com.naviera.domain.valueObject.Posicion;
import com.naviera.domain.valueObject.Puerto;
import com.naviera.domain.viaje.*;

import java.util.ArrayList;
import java.util.List;

public class Navio {
    // PROPIEDADES

    private long contadorViajes = 0;
    private Viaje viajeActual;
    private Puerto puertoActual;
    private Posicion posicionActual;
    private final Long id;
    private final String nombre;
    private EstadoNavio estado;
    private final ArrayList<Tripulante> tripulacion = new ArrayList<>();
    private final List<Viaje> historial = new ArrayList<>();

    // CONSTRUCTOR

    public Navio(Long id, String nombre, Puerto puertoInicial) {
        if (id == null || nombre == null) {
            throw new IllegalArgumentException("Datos invalidos, verificar id o nombre");
        }
        this.id = id;
        this.nombre = nombre;
        this.estado = new EnPuerto(); //estado inicial del navio
        this.puertoActual = puertoInicial;
        this.posicionActual = null;

    }

    public Puerto getPuertoActual(){
        return puertoActual;
    }

    public void iniciarViaje(Posicion posicionInicial, Puerto destino){
        validarTripulacion();
        estado.validarInicioViaje(this);
        Viaje nuevoViaje = new Viaje(
                generarID(),
                this.puertoActual,
                destino,
                posicionInicial
        );

        this.viajeActual = nuevoViaje;
        this.posicionActual = posicionInicial;
        this.puertoActual = null;
        this.estado = new EnNavegacion();

        nuevoViaje.iniciar();

    }

    /*
    public void iniciarViaje(Posicion posicionInicial, Puerto destino) {

        if(viajeActual != null){
            throw new IllegalStateException("Ya existe un viaje en curso.");
        }
        validarTripulacion();

        estado.validarInicioViaje(this);
        Viaje nuevoViaje = new Viaje(
                2L,
                this.puertoActual,
                destino,
                posicionInicial
        );
        estado.iniciarViaje(this, posicionInicial); // si el estado actual es en puerto, inciarViaje es valido.
        nuevoViaje.iniciar();
        this.viajeActual = nuevoViaje;

    } */

    public void finalizarViaje(Puerto puertoDestino) {

        estado.validarFinViaje(this);
        viajeActual.finalizar();
        historial.add(viajeActual);
        this.viajeActual = null;
        this.estado = new EnPuerto();

    }


    void cambiarEstado(EstadoNavio nuevoEstado) {
        this.estado = nuevoEstado;
    }


    public void agregarTripulacion(Tripulante tripulante) {
        if (tripulante == null) {
            throw new IllegalArgumentException("Tripulante invalido");
        }
        tripulacion.add(tripulante);

    }

    public void validarTripulacion() {
        if (tripulacion.isEmpty()) {
            throw new IllegalStateException("El navio no tiene tripulacion asignada");
        }

        boolean validarCapitan = tripulacion.stream()
                .anyMatch(t -> t.getRol() == RolTripulante.CAPITAN);

        boolean validarOficial = tripulacion.stream()
                .anyMatch(t -> t.getRol() == RolTripulante.OFICIAL);

        // Pendiente una validacion para numero de tripulantes dependiendo tamaño de la embarcacion.

        if(!validarCapitan || !validarOficial){
            throw new IllegalStateException("Tripulacion incompleta para iniciar viaje");
        }
    }

    public String estadoActual() {

        return estado.nombre();
    }

    public EstadoNavio myEstado(){
        return estado;
    }

    public boolean navegando(){
        return estado instanceof EnNavegacion;
    }

    public void aMantenimiento(){
        estado.enviarAMantenimiento(this);
    }

    public void finalizarMantenimiento(){
        estado.volverDeMantenimiento(this);
    }

    void salirDePuerto(Posicion posicionInicial){
        this.puertoActual = null;
        this.posicionActual = posicionInicial;

    }

    void llegarAPuerto(Puerto puertoDestino){
        this.puertoActual = puertoDestino;
        this.posicionActual = null;
    }

    public void actualizarPosicion(Posicion nuevaPosicion, double velocidad, String observaciones){
        if(!navegando()){
            throw new IllegalStateException("El navio no esta navegando");
        }
        this.posicionActual = nuevaPosicion;
        viajeActual.actualizarPosicion(
                nuevaPosicion,
                velocidad,
                observaciones);
    }

    public Long generarID(){
        return ++contadorViajes;
    }
}
