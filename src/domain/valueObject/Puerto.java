package domain.valueObject;

import java.util.Objects;

public final class Puerto {

    private final String nombre;
    private final String pais;

    // constructor

    public Puerto(String nombre, String pais){

        this.nombre = nombre;
        this.pais = pais;

    }

    // metodo validar, debera incluirse en el constructor.

    public void validar(String nombre, String pais){
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre del puerto no puede se null ni estar vacio");
        }
        if(pais == null || pais.isBlank()){
            throw new IllegalArgumentException("El pais del puerto no puede ser null o estar vacio");
        }
    }

    // metodos getter y setter

    public String getNombre(){
        return nombre;
    }

    public String getPis(){
        return pais;
    }

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
