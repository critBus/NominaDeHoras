package com.example.rene.nominadehoras.Logica.LogicaSeparada;

import com.example.rene.nominadehoras.Logica.LogicaConModelo;
import com.example.rene.nominadehoras.Logica.TipoDeSession;

import java.sql.Time;

/**
 * Created by Rene on 9/1/2022.
 */

public class Session extends LogicaConModelo {
    //private TipoDeSession tipo;
    private Time entrada;
    private Time salida;

    public Session(Session s) {
        this.entrada = new Time(s.getEntrada().getHours(),s.getEntrada().getMinutes(),0);
        this.salida = new Time(s.getSalida().getHours(),s.getSalida().getMinutes(),0);
    }
    public Session(Time entrada, Time salida) {
        this.entrada = entrada;
        this.salida = salida;
    }

    public Time getEntrada() {
        return entrada;
    }

    public void setEntrada(Time entrada) {
        this.entrada = entrada;
    }

    public Time getSalida() {
        return salida;
    }

    public void setSalida(Time salida) {
        this.salida = salida;
    }
    public String getEntradaStr(){
        return getTimeStr(entrada);
    }
    public String getSalidaStr(){
        return getTimeStr(salida);
    }
    private String getTimeStr(Time t){
        return String.format("%d:%02d",t.getHours(),t.getMinutes());
    }
}
