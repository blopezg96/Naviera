package domain.navio.estado;

import domain.navio.Navio;

public class EnMantenimiento implements EstadoNavio{

    @Override
    public void iniciarViaje(Navio navio){
        throw new IllegalArgumentException("El navio esta en mantenimiento");
    }

    @Override
    public void finalizarViaje(Navio navio){
        throw new IllegalStateException("El navio esta en mantenimiento");
    }

    @Override
    public String nombre(){
        return "EN_MANTENIMIENTO";
    }
}
