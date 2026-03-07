package domain.navio.estado;

import domain.navio.Navio;

/* Esta clase contiene la informacion de el estado En puerto
* y aqui mismo se valida si tiene o no tripulacion para comenzar el viaje.*/

public class EnPuerto implements EstadoNavio{

    @Override
    public void iniciarViaje(Navio navio){
         navio.validarTripulacion();
         navio.cambiarEstado(new EnNavegacion());

    }

    @Override
    public void finalizarViaje(Navio navio){
        throw new IllegalStateException("El navio ya esta en puerto");
    }

    @Override
    public String nombre(){
        return "EN_PUERTO";
    }

}
