package com.example.rene.nominadehoras.Logica.LogicaSeparada;

import com.example.rene.nominadehoras.Logica.LogicaConModelo;
import com.example.rene.nominadehoras.Logica.Trabajador;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.Calculador.CantidadDeTiempo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rene on 21/3/2022.
 */

public class ConjuntoDePlantillasDeTrabajador extends LogicaConModelo {
    private Trabajador trabajador;
    private List<PlantillaTrabajador> listPlantillaTrabajador;

    public ConjuntoDePlantillasDeTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
        this.listPlantillaTrabajador=new LinkedList<>();
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public List<PlantillaTrabajador> getListPlantillaTrabajador() {
        return listPlantillaTrabajador;
    }

    public void setListPlantillaTrabajador(List<PlantillaTrabajador> listPlantillaTrabajador) {
        this.listPlantillaTrabajador = listPlantillaTrabajador;
    }

    public CantidadDeTiempo getCantidadTiempoTotal(){
        CantidadDeTiempo c=new CantidadDeTiempo(0);
        for (int i = 0; i <listPlantillaTrabajador.size(); i++) {
            c=c.add(listPlantillaTrabajador.get(i).getCantidadDeHorasTrabajadasTotales());
        }
        return c;
    }

    @Override
    public String toString() {
        return trabajador.getNombre();
    }
}
