package com.example.rene.nominadehoras.Logica.LogicaConjunta;

import java.util.HashMap;

/**
 * Created by Rene on 28/12/2021.
 */
import com.example.rene.nominadehoras.Logica.TipoDeSession;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.Calculador.CantidadDeTiempo;
public class DiaDeTrabajador {
    private HashMap<TipoDeSession,SessionDeTrabajador> sessionesTrabajadas;

    public DiaDeTrabajador(SessionDeTrabajador ... Sessiones) {
        sessionesTrabajadas=new HashMap<TipoDeSession,SessionDeTrabajador>();
        for (int i = 0; i <Sessiones.length ; i++) {
            SessionDeTrabajador s=Sessiones[i];
            sessionesTrabajadas.put(s.getTipo(),s);
        }
    }
    public SessionDeTrabajador get(TipoDeSession tipo){
        return sessionesTrabajadas.get(tipo);
    }
    public CantidadDeTiempo getCantidadDeHorasTrabajadas(){
        TipoDeSession keys[]=sessionesTrabajadas.keySet().toArray(new TipoDeSession[0]);
        CantidadDeTiempo total=null;
        for (int i = 0; i < keys.length; i++) {
            CantidadDeTiempo c=get(keys[i]).getTiempoTrabajado();
            if(i==0){
                total=c;
                continue;
            }
            total=total.add(c);
        }
        return total;
    }
}
