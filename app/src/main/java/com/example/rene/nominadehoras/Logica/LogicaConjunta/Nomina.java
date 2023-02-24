package com.example.rene.nominadehoras.Logica.LogicaConjunta;

import com.example.rene.nominadehoras.Logica.Trabajador;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.Calculador.CantidadDeTiempo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rene on 28/12/2021.
 */

public class Nomina {
    List<NominaDeSemana> nominasDeSemana;

    public Nomina() {
        nominasDeSemana=new LinkedList<>();
    }

    public List<NominaDeSemana> getNominasDeSemana() {
        return nominasDeSemana;
    }
    public CantidadDeTiempo getCantidadDeHorasTrabajadas(Trabajador t){
        CantidadDeTiempo total=null;
        int leng=nominasDeSemana.size();
        for (int i = 0; i < leng; i++) {
            CantidadDeTiempo c=nominasDeSemana.get(i).getCantidadDeHorasTrabajadas(t);
            if(i==0){
                total=c;
                continue;
            }
            total=total.add(c);
        }
        return total;
    }
    private double getPorcentage60(double parte){
        if(parte>0){
            return parte/60;
        }
        return 0;
    }

    public double getCalculo_IgnorarSegundos(Trabajador t,double delta){
        CantidadDeTiempo c=getCantidadDeHorasTrabajadas(t);
//        double porcientoSegundos=getPorcentage60(c.getSegundos());
//        if(porcientoSegundos>0){porcientoSegundos/=100;}
        double porcientoMinutos=getPorcentage60(c.getMinutos());
        return (c.getHoras()+porcientoMinutos)*delta;
    }
}
