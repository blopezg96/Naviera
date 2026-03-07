package domain.navio;

import domain.navio.estado.EnPuerto;
import domain.navio.estado.EstadoNavio;
import domain.tripulacion.Tripulante;

import java.util.ArrayList;

public class Navio {
    // PROPIEDADES

    private final double id;
    private final String nombre;
    private EstadoNavio estado;
    private final ArrayList<Tripulante> tripulacion = new ArrayList<>();

    // CONSTRUCTOR

    public Navio(Long id, String nombre){
        if(id == null || nombre == null) {
            throw new IllegalArgumentException("Datos invalidos, verificar id o nombre");
        }
       this.id = id;
       this.nombre = nombre;
       this.estado = new EnPuerto(); //estado inicial del navio

    }

    public void iniciarViaje(){
        estado.iniciarViaje(this); // si el estado actual es en puerto, inciarViaje es valido.
    }

    public void finalizarViaje(){
        estado.finalizarViaje(this);
    }

    public void cambiarEstado(EstadoNavio nuevoEstado){
        this.estado = nuevoEstado;
    }

    public void agregarTripulacion(Tripulante tripulante){
        if(tripulante == null){
            throw new IllegalArgumentException("Tripulante invalido");
        }
        tripulacion.add(tripulante);

    }

    public void validarTripulacion(){
        if(tripulacion.isEmpty()){
            throw new IllegalStateException("El navio no tiene tripulacion asignada");
        }
    }

    public String estadoActual(){
        return estado.nombre();
    }
}
