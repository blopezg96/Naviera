package domain.viaje;

import domain.valueObject.Posicion;

import java.time.LocalDateTime;
import java.util.Objects;

public class EntradaLogbook {

    private final LocalDateTime fechaHora;
    private final Posicion posicion;
    private final double velocidad;
    private final String observaciones;

    // CONSTRUCTOR

    public EntradaLogbook(LocalDateTime fechaHora,
                          Posicion posicion,
                          double velocidad,
                          String observaciones){
        validar(fechaHora, posicion, velocidad);
        this.fechaHora = fechaHora;
        this.posicion = posicion;
        this.velocidad = velocidad;
        this.observaciones = observaciones;
    }

    // METODO VALIDAR
    private void validar(LocalDateTime fechaHora, Posicion posicion, double velocidad){
        if (fechaHora == null){
            throw new IllegalArgumentException("Fecha y hora deben ser obligatorias");
        }
        if (posicion == null){
            throw new IllegalArgumentException("La posiscion es obligatoria");
        }
        if (velocidad < 0){
            throw new IllegalArgumentException("La velocidad no puede ser negativa");
        }
    }

    // METODOS GETTER
    public LocalDateTime getFechaHora(){
        return fechaHora;

    }

    public Posicion getPosicion(){
        return posicion;
    }

    public double getVelocidad(){
        return velocidad;
    }

    public String getObservaciones(){
        return observaciones;
    }

    // METODO EQUALS

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (!(o instanceof EntradaLogbook)) return false;
        EntradaLogbook that = (EntradaLogbook) o;
        return fechaHora.equals(that.fechaHora);
    }

    @Override
    public int hashCode(){
        return Objects.hash(fechaHora);
    }
}
