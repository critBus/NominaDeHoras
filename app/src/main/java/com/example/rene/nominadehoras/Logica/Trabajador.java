package com.example.rene.nominadehoras.Logica;

/**
 * Created by Rene on 28/12/2021.
 */

public class Trabajador extends LogicaConModelo {
    private String nombre;

    public Trabajador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
