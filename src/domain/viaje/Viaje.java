package domain.viaje;

import domain.valueObject.Puerto;
import domain.navio.Navio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Viaje {

    private final Long id;
    private final Navio navio;
    private final Puerto origen;
    private final Puerto destino;

    private LocalDateTime salida;
    private LocalDateTime llegada;

    private final List<EntradaLogbook> logbookList = new ArrayList<>();

    // CONSTRUCTOR

    public Viaje(Long id, Navio navio, Puerto origen, Puerto destino){
        validar(id, navio, origen, destino);
        this.id = id;
        this.navio = navio;
        this.origen = origen;
        this.destino = destino;
    }

    public void validar(Long id, Navio navio, Puerto origen, Puerto destino ){
        if(id == null){
            throw new IllegalArgumentException("El id no puede estar vacio");
        }
        if(navio == null){
            throw new IllegalArgumentException("Nombre de el navio no puede estar vacio");
        }
        if(origen == null){
            throw new IllegalArgumentException("El origen no puede estar vacio");
        }
        if (destino == null){
            throw new IllegalArgumentException("El destino no puede estar vacio");
        }
    }

    // =============================
    // Ciclo de vida del viaje
    // =============================

    public void iniciar(){
        if(salida != null){                                            //  Si ya ha partido el navio...
            throw new IllegalStateException("El viaje ya fue iniciado");
        }
        navio.iniciarViaje();
        this.salida = LocalDateTime.now(); // se declara que la salida ha ocurrido justamente ahora
    }

    public void finalizar(){
        if(salida == null){                                            // si el navio no ha partido aun
            throw new IllegalStateException("El navio no ha salido aun");
        }
        navio.finalizarViaje();
        this.llegada = LocalDateTime.now();  // se declara que el viaje ha finalizado ahora mismo.
    }


    // ===================================
    //             LOGBOOK
    // ===================================

    public void registrarEntrada(EntradaLogbook entrada){
        if(salida == null){  // si el navio aun no ha salido
            throw new IllegalStateException("No se registrar entrada antes de comenzado el viaje");
        }
        if(llegada != null){   // Si el navio ya llego...
            throw new IllegalStateException("No se puede registrar entrada despues de finalizado el viaje ");
        }
    }

    // ==============================
    // Getters de lectura
    // ==============================

    public Navio getNavio(){
        return navio;
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



}
