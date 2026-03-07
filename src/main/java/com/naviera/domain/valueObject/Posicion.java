package com.naviera.domain.valueObject;

import java.util.Objects;

public final class Posicion {

    private final double latitud;
    private final double longitud;


    // Constructor de clase
    public Posicion(double latitud, double longitud){
        validarLatitud(latitud);
        validarLongitud(longitud);
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // Metodos

    private void validarLatitud(double latitud){
        if(latitud < -90 || latitud > 90){
            throw new IllegalArgumentException("Latitud fuera de rango (-90 a 90)" +
                    "Rectifique ubicacion nuevamente");
        }
    }

    private void validarLongitud(double longitud){
        if(longitud < -180 || longitud > 180){
            throw new IllegalArgumentException("Longitud fuera de rango (-180 a 180)" +
                    "Rectifique ubicacion nuevamente");
        }
    }

    public double getLatitud(){
        return latitud;
    }

    public double getLongitud(){
        return longitud;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Posicion)) return false;
        Posicion posicion = (Posicion) o;
        return Double.compare(posicion.latitud, latitud) == 0 &&
                Double.compare(posicion.longitud, longitud) == 0;
    }

    @Override
    public int hashCode(){
        return Objects.hash(latitud, longitud);
    }

    @Override
    public String toString(){
        return "Posicion {" +
                "latitud " + latitud +
                "longitud " + longitud +" }";

    }





}
