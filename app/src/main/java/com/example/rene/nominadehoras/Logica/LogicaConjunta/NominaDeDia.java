package com.example.rene.nominadehoras.Logica.LogicaConjunta;

import java.util.HashMap;

/**
 * Created by Rene on 28/12/2021.
 */
import com.example.rene.nominadehoras.Logica.Trabajador;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.Calculador.CantidadDeTiempo;

public class NominaDeDia {
    private FechaDeNominaDeDia fecha;
    private HashMap<Trabajador,DiaDeTrabajador> diasDeTrabajadores;

    public NominaDeDia() {
        diasDeTrabajadores=new HashMap<>();
    }

    public void add(Trabajador t,DiaDeTrabajador d){
        diasDeTrabajadores.put(t,d);
    }
    public DiaDeTrabajador get(Trabajador t){
        return diasDeTrabajadores.get(t);
    }

    public CantidadDeTiempo getCantidadDeHorasTrabajadas(Trabajador t){
        return get(t).getCantidadDeHorasTrabajadas();
    }

    public FechaDeNominaDeDia getFecha() {
        return fecha;
    }

    public void setFecha(FechaDeNominaDeDia fecha) {
        this.fecha = fecha;
    }
}
