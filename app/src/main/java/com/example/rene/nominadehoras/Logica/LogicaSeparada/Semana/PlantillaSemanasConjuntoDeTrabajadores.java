package com.example.rene.nominadehoras.Logica.LogicaSeparada.Semana;

import com.example.rene.nominadehoras.Logica.LogicaSeparada.PlantillaTrabajador;

import org.joda.time.DateTime;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rene on 21/3/2022.
 */

public class PlantillaSemanasConjuntoDeTrabajadores {
    private DateTime fechaInicial;
    private List<PlantillaTrabajador> listaPLantillasTrabajadores;

    public PlantillaSemanasConjuntoDeTrabajadores(DateTime fechaInicial) {
        this.fechaInicial = fechaInicial;
        listaPLantillasTrabajadores=new LinkedList<>();
    }

    public DateTime getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(DateTime fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public List<PlantillaTrabajador> getListaPLantillasTrabajadores() {
        return listaPLantillasTrabajadores;
    }

    public void setListaPLantillasTrabajadores(List<PlantillaTrabajador> listaPLantillasTrabajadores) {
        this.listaPLantillasTrabajadores = listaPLantillasTrabajadores;
    }
}
