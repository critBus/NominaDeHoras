package com.example.rene.nominadehoras.Logica.LogicaSeparada;

import com.example.rene.nominadehoras.Logica.LogicaConModelo;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.Calculador.CantidadDeTiempo;

import java.sql.Time;

/**
 * Created by Rene on 9/1/2022.
 */

public class DiaDeTrabajo extends LogicaConModelo {
    private Session mañana;
    private Session tarde;

    public DiaDeTrabajo(DiaDeTrabajo d) {
        this.mañana = new Session(d.getMañana());
        this.tarde = new Session(d.getTarde());
    }

    public DiaDeTrabajo(Session mañana, Session tarde) {
        this.mañana = mañana;
        this.tarde = tarde;
    }

    public Session getMañana() {
        return mañana;
    }

    public void setMañana(Session mañana) {
        this.mañana = mañana;
    }

    public Session getTarde() {
        return tarde;
    }

    public void setTarde(Session tarde) {
        this.tarde = tarde;
    }

    public CantidadDeTiempo getCantidadDeHorasTrabajadas(){
        Time sme=mañana.getEntrada();
        //System.out.println("salida: sme="+sme);
        CantidadDeTiempo m=new CantidadDeTiempo();
        if((!isEmpty(mañana.getEntrada()))&&(!isEmpty(mañana.getSalida()))){
            m=new CantidadDeTiempo(
                    sme.getHours(),
                    sme.getMinutes(),
                    sme.getSeconds());
//            System.out.println("salida: m="+m);
            Time sms=mañana.getSalida();
//            System.out.println("salida: sms="+sms);
            int horasMañanaSalida=sms.getHours();
            if(horasMañanaSalida<4){
                horasMañanaSalida+=12;
            }
            //System.out.println("salida: horasMañanaSalida="+horasMañanaSalida);
            m=new CantidadDeTiempo(
                    horasMañanaSalida,
                    sms.getMinutes(),
                    sms.getSeconds()).sub(m);
            //System.out.println("salida: 2m="+m);
        }
        if((!isEmpty(tarde.getEntrada()))&&(!isEmpty(tarde.getSalida()))){
            Time ste=tarde.getEntrada();
            Time sts=tarde.getSalida();
//            System.out.println("salida: ste="+ste);
//            System.out.println("salida: sts="+sts);

            m=m.add(new CantidadDeTiempo(
                    sts.getHours(),
                    sts.getMinutes(),
                    sts.getSeconds()).sub(new CantidadDeTiempo(
                    ste.getHours(),
                    ste.getMinutes(),
                    ste.getSeconds())));

        }
       return m;

    }

    private boolean isEmpty(Time t){
        return t.getHours()==0&&t.getMinutes()==0&&t.getSeconds()==0;
    }


}
