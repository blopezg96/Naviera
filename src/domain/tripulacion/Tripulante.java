package domain.tripulacion;

import java.util.Objects;

public class Tripulante {

private final Long id;
private final String nombre;
private final RolTripulante rol;

public Tripulante(Long id, String nombre, RolTripulante rol){
    validar(id, nombre, rol);
    this.id = id;
    this.nombre = nombre;
    this.rol = rol;

}

public void validar(Long id, String nombre, RolTripulante rol){
    if(id == null){
        throw new IllegalArgumentException("El id de tripulante no puede ser null o vacio");
    }
    if(nombre == null){
        throw new IllegalArgumentException("El nombre del tripulante no puede ser null o estar vacio");
    }
    if(rol == null){
        throw new IllegalArgumentException("El rol de tripulante no puede ser null o estar vacio");
    }
}

    ///  metodos getter

    public long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public RolTripulante getRol() {
        return rol;
    }



    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Tripulante)) return false;
        Tripulante that = (Tripulante) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, rol);
    }
}
