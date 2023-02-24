package com.example.rene.nominadehoras.Logica.LogicaConjunta;


import com.example.rene.nominadehoras.Logica.Trabajador;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.Calculador.CantidadDeTiempo;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.TipoDeDia;

import java.util.HashMap;

/**
 * Created by Rene on 28/12/2021.
 */

public class NominaDeSemana {
    private String nombre;
    private HashMap<FechaDeNominaDeDia,NominaDeDia> diasDeNomina;
    public NominaDeSemana() {
        diasDeNomina=new HashMap<>();
    }
    public NominaDeDia get(FechaDeNominaDeDia f){
        return diasDeNomina.get(f);
    }
    public NominaDeDia get(TipoDeDia dia){
        FechaDeNominaDeDia keys[]=diasDeNomina.keySet().toArray(new FechaDeNominaDeDia[0]);
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].getTipo().equals(dia)){
                return get(keys[i]);
            }
        }
        return null;
    }
    public CantidadDeTiempo getCantidadDeHorasTrabajadas(Trabajador t){

        FechaDeNominaDeDia keys[]=diasDeNomina.keySet().toArray(new FechaDeNominaDeDia[0]);
        CantidadDeTiempo total=null;
        for (int i = 0; i < keys.length; i++) {
            CantidadDeTiempo c=get(keys[i]).getCantidadDeHorasTrabajadas(t);
            if(i==0){
                total=c;
                continue;
            }
            total=total.add(c);
        }
        return total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
