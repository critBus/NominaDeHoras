package com.example.rene.nominadehoras.Logica.LogicaConjunta;

/**
 * Created by Rene on 28/12/2021.
 */
import com.example.rene.nominadehoras.Logica.TipoDeSession;
import com.example.rene.nominadehoras.Logica.Trabajador;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.Calculador.CantidadDeTiempo;
public class SessionDeTrabajador {
    private TipoDeSession tipo;
    private Trabajador trabajador;
    private CantidadDeTiempo tiempoTrabajado;

    public SessionDeTrabajador(TipoDeSession tipo, Trabajador trabajador, CantidadDeTiempo tiempoTrabajado) {
        this.tipo = tipo;
        this.trabajador = trabajador;
        this.tiempoTrabajado = tiempoTrabajado;
    }

    public TipoDeSession getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeSession tipo) {
        this.tipo = tipo;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public CantidadDeTiempo getTiempoTrabajado() {
        return tiempoTrabajado;
    }

    public void setTiempoTrabajado(CantidadDeTiempo tiempoTrabajado) {
        this.tiempoTrabajado = tiempoTrabajado;
    }
}
