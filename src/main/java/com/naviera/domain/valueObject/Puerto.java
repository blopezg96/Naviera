package com.naviera.domain.valueObject;

import java.util.Objects;

public final class Puerto {

    private final String nombre;
    private final String pais;
    private final double latitud;
    private final double longitud;
    // constructor

    public Puerto(String nombre, String pais, double latitud, double longitud){
        validar(nombre, pais, latitud, longitud);
        this.nombre = nombre.trim();
        this.pais = pais.trim();
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // metodo validar, debera incluirse en el constructor.

    private void validar(String nombre, String pais, double latitud, double longitud){
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre del puerto no puede se null ni estar vacio");
        }
        if(pais == null || pais.isBlank()){
            throw new IllegalArgumentException("El pais del puerto no puede ser null o estar vacio");
        }
        if(latitud < -90 || latitud > 90){
            throw new IllegalArgumentException("Latitud fuera de rango (-90 a 90)" +
                    "Rectifique ubicacion nuevamente");
        }
        if(longitud < -180 || longitud > 180){
            throw new IllegalArgumentException("Longitud fuera de rango (-180 a 180)" +
                    "Rectifique ubicacion nuevamente");
        }
    }

    // metodos getter

    public String getNombre(){
        return nombre;
    }

    public String getPais(){
        return pais;
    }

    public double getLatitud(){return latitud;}

    public double getLongitud(){return longitud;}

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Puerto)) return false;
        Puerto puerto = (Puerto) o;
        return nombre.equalsIgnoreCase(puerto.nombre) &&
                pais.equalsIgnoreCase(puerto.pais);
    }

    @Override
    public int hashCode(){
        return Objects.hash(nombre.toLowerCase(), pais.toLowerCase());
    }

    @Override
    public String toString(){
        return "Puerto {" + nombre + ", " + pais + "}";


    }
}
