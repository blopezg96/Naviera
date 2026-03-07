package com.naviera.domain.viaje;

import com.naviera.domain.navio.Navio;
import com.naviera.domain.valueObject.Posicion;
import com.naviera.domain.valueObject.Puerto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Viaje {

    private final Long id;
    private final Puerto origen;
    private final Puerto destino;
    private final Posicion posicionInicial;
    private  Posicion posicionActual;
    private EstadoViaje estado;
    private int logbookSize;
    private double distanciaDeViaje;


    private LocalDateTime salida;
    private LocalDateTime llegada;

    private final List<EntradaLogbook> logbookList = new ArrayList<>();

    // CONSTRUCTOR

    public Viaje(Long id, Puerto origen, Puerto destino, Posicion posicionInicial){
        validar(id, origen, destino, posicionInicial);
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.posicionInicial = posicionInicial;
        this.posicionActual = posicionInicial;
        this.estado = EstadoViaje.PLANIFICADO;
    }

    public void validar(Long id, Puerto origen, Puerto destino, Posicion posicionInicial ){
        if(id == null){
            throw new IllegalArgumentException("El id no puede estar vacio");
        }

        if(origen == null){
            throw new IllegalArgumentException("El origen no puede estar vacio");
        }
        if (origen.equals(destino)) {
            throw new IllegalStateException("Origen y destino no pueden ser los mismos.");
        }
        if (destino == null){
            throw new IllegalArgumentException("El destino no puede estar vacio");
        }
        if (posicionInicial == null){
            throw new IllegalArgumentException("No se registro ninguna posicion inicial. ");
        }
    }

    // =============================
    // Ciclo de vida del viaje
    // =============================

    public void iniciar(){
        if(estado != EstadoViaje.PLANIFICADO){  //  Si ya ha partido el navio, no puede volver a iniciar.
            throw new IllegalStateException("El viaje ya fue iniciado");
        }
        this.estado = EstadoViaje.EN_CURSO;
        this.salida = LocalDateTime.now(); // se declara que la salida ha ocurrido justamente ahora

        registrarEntradaInterna("Saliendo del puerto de origen.", 0);

    }

    public void finalizar(){
        if(estado != EstadoViaje.EN_CURSO){  // si el navio no ha partido aun no puede finalizar nada
            throw new IllegalStateException("El navio no ha salido aun");
        }
        this.estado = EstadoViaje.FINALIZADO;
        this.llegada = LocalDateTime.now();  // se declara que el viaje ha finalizado ahora mismo.
        registrarEntradaInterna("Llegada a puerto de destino", 0);

    }

    public void actualizarPosicion(Posicion nuevaPosicion, double velocidad, String observaciones){
        if(estado != EstadoViaje.EN_CURSO){
            throw new IllegalStateException("No es posible actualizar posicion en un viaje inactivo");
        }

        if(nuevaPosicion == null){
            throw new IllegalArgumentException("No es posible determinar la posicion actual");
        }
        this.posicionActual = nuevaPosicion;
        EntradaLogbook actualizarEntrada = new EntradaLogbook(

                LocalDateTime.now(),
                nuevaPosicion,
                velocidad,
                observaciones
        );

        registrarEntrada(actualizarEntrada);

    }

    


    // ===================================
    //             LOGBOOK
    // ===================================

    public void registrarEntrada(EntradaLogbook entrada){
        if(estado != EstadoViaje.EN_CURSO){  // si el navio aun no ha salido
            throw new IllegalStateException("No se registrar entrada antes de comenzado el viaje");
        }
        if(estado == EstadoViaje.FINALIZADO){   // Si el navio ya llego...
            throw new IllegalStateException("No se puede registrar entrada despues de finalizado el viaje ");
        }
        logbookList.add(entrada);

    }


    public void registrarEntradaInterna(String observacion, double velocidad){

        EntradaLogbook entrada = new EntradaLogbook(
                LocalDateTime.now(),
                posicionActual,
                velocidad,
                observacion
        );

    }

    // ==============================
    // Metodo para calcular distancia
    // ==============================

    public void calcularDistancia(){
        double lat1 = origen.getLatitud();
        double lon1 = origen.getLongitud();

        double lat2 = destino.getLatitud();
        double lon2 = destino.getLongitud();

        double radioTierra = 6371; //Km

        double deltaLat = Math.toRadians(lat2 - lat1);
        double deltaLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(deltaLon/2) * Math.sin(deltaLon/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        this.distanciaDeViaje = radioTierra*c;

    }


    // ==============================
    // Getters de lectura
    // ==============================

    public Long getId(){
        return id;
    }

    public Puerto getOrigen(){
        return origen;
    }

    public Puerto getDestino(){
        return destino;
    }

    public LocalDateTime getLlegada(){
        return llegada;
    }

    public LocalDateTime getSalida(){
        return salida;
    }

    public List<EntradaLogbook> getLogbook(){
        return List.copyOf(logbookList);
    }

    public EstadoViaje getEstado(){
        return estado;
    }

    public double getDistanciaDeViaje() {
        return distanciaDeViaje;
    }
}
