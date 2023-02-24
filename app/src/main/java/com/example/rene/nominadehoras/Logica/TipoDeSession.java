package com.example.rene.nominadehoras.Logica;

/**
 * Created by Rene on 28/12/2021.
 */

public class TipoDeSession {
    public static final TipoDeSession MAÑANA=new TipoDeSession("Mañana"),TARDE=new TipoDeSession("Tarde");
    private String nombre;

    public TipoDeSession(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
