package domain.navio.estado;

import domain.navio.Navio;

public interface EstadoNavio {

    void iniciarViaje(Navio navio);

    void finalizarViaje(Navio navio);

    String nombre();

}
