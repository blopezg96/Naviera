package domain.navio.estado;

import domain.navio.Navio;

public class EnNavegacion implements EstadoNavio{


    public void iniciarViaje(Navio navio){
        throw new IllegalStateException("El navio ya esta en navegacion");
    }


    public void finalizarViaje(Navio navio) {
        navio.cambiarEstado(new EnPuerto());
    }


    public String nombre(){
        return "EN_NAVEGACION";
    }
}
