package com.example.rene.nominadehoras.Logica.LogicaSeparada;

import com.example.rene.nominadehoras.Logica.LogicaConModelo;
import com.example.rene.nominadehoras.Logica.Trabajador;
import com.example.rene.nominadehoras.Visual.UtilesProyecto;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.Calculador.CantidadDeTiempo;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.Tempus;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Rene on 9/1/2022.
 */

public class PlantillaTrabajador extends LogicaConModelo {
    private Trabajador trabajador;
    private DateTime FechaInicial;
    private List<DiaDeTrabajo> diasDeTrabajos;



    public PlantillaTrabajador(Trabajador trabajador, DateTime fechaInicial) {
        this.trabajador = trabajador;
        this.FechaInicial = fechaInicial;
        this.diasDeTrabajos=new LinkedList<>();
    }

    public List<DiaDeTrabajo> getDiasDeTrabajos() {
        return diasDeTrabajos;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public DateTime getFechaInicial() {
        return FechaInicial;
    }

    public String getFechaStr(int iDia){
//        System.out.println("salida: iDia="+iDia);

        DateTime d=getFecha(iDia);
//        System.out.println("salida: llego a "+iDia);
        //return Tempus.nombresDia[d.getDayOfWeek()-1].substring(0,3)+" "+d.toString(fmt);
        return UtilesProyecto.getFechaStr(d);
    }


//    public static String getFechaStr(DateTime d){
//
//        return Tempus.nombresDia[d.getDayOfWeek()-1].substring(0,3)+" "+d.toString(fmt);
//    }

    public DateTime getFecha(int iDia){
        DateTime d=FechaInicial;
        if(iDia>0){
//            System.out.println("salida: entro con "+iDia);
            d=FechaInicial.plusDays(iDia);
        }
        return d;
    }

    public void setFechaInicial(DateTime fechaInicial) {
        FechaInicial = fechaInicial;
    }

    public CantidadDeTiempo getCantidadDeHorasTrabajadasTotales() {
        CantidadDeTiempo c = new CantidadDeTiempo();

        for (int i = 0; i < diasDeTrabajos.size(); i++) {
            c = c.add(diasDeTrabajos.get(i).getCantidadDeHorasTrabajadas());
        }

        return c;

    }
    public String  getCantidadDeHorasTrabajadasTotalesStr() {
        CantidadDeTiempo c=getCantidadDeHorasTrabajadasTotales();
        //return String.format("%d:%02d",c.getHoras(),c.getMinutos());
        return UtilesProyecto.getHorasStr(c);
    }
    public CantidadDeTiempo getCantidadDeHorasTrabajadasTotales(int dia) {
        CantidadDeTiempo c = new CantidadDeTiempo();
        if (dia < diasDeTrabajos.size()) {
            for (int i = 0; i < dia+1; i++) {
                c = c.add(diasDeTrabajos.get(i).getCantidadDeHorasTrabajadas());
            }
        }
        return c;
    }
    public CantidadDeTiempo getCantidadDeHorasTrabajadas(int dia){
        CantidadDeTiempo c=new CantidadDeTiempo();
        if(dia<diasDeTrabajos.size()){
            return diasDeTrabajos.get(dia).getCantidadDeHorasTrabajadas();
        }

        return c;
    }
    public String getCantidadDeHorasTrabajadasTotalesStr(int dia) {
        CantidadDeTiempo c=getCantidadDeHorasTrabajadasTotales(dia);
        //return String.format("%d:%02d",c.getHoras(),c.getMinutos());
        return UtilesProyecto.getHorasStr(c);

    }
    public String getCantidadDeHorasTrabajadasStr(int dia) {
        CantidadDeTiempo c=getCantidadDeHorasTrabajadas(dia);
        //return String.format("%d:%02d",c.getHoras(),c.getMinutos());
        return UtilesProyecto.getHorasStr(c);

    }
    public void addPosteriorCopia(int indice,DiaDeTrabajo d){
        //DiaDeTrabajo anterior=getDiasDeTrabajos().get(indice);
        DiaDeTrabajo anterior=d;
        Time sme=anterior.getMañana().getEntrada();
        Time sms=anterior.getMañana().getSalida();
        Time ste=anterior.getTarde().getEntrada();
        Time sts=anterior.getTarde().getSalida();
        addPosterior(indice,sme.getHours(),sme.getMinutes()
                            ,sms.getHours(),sms.getMinutes()
                            ,ste.getHours(),ste.getMinutes()
                            ,sts.getHours(),sts.getMinutes());
    }
    public void add(DiaDeTrabajo d){
        getDiasDeTrabajos().add(d);
    }
    public void addVacio(){
        getDiasDeTrabajos().add(getNewDiaDeTrabajo(0,0
                ,0,0
                ,0,0
                ,0,0));
    }



    public void addPosterior(int indice,int horasEM,int minutosEM,int horasSM,int minutosSM,int horasET,int minutosET,int horasST,int minutosST){
        getDiasDeTrabajos().add(indice+1,getNewDiaDeTrabajo(horasEM, minutosEM, horasSM, minutosSM, horasET, minutosET,horasST, minutosST));
    }
    public void add(int horasEM,int minutosEM,int horasSM,int minutosSM,int horasET,int minutosET,int horasST,int minutosST){
        getDiasDeTrabajos().add(getNewDiaDeTrabajo(horasEM, minutosEM, horasSM, minutosSM, horasET, minutosET,horasST, minutosST));
    }
    public void addPosteriorVacio(int indice){
        addPosterior(indice,0,0
                ,0,0
                ,0,0
                ,0,0);
    }
    private DiaDeTrabajo getNewDiaDeTrabajo(int horasEM,int minutosEM,int horasSM,int minutosSM,int horasET,int minutosET,int horasST,int minutosST){

                return new DiaDeTrabajo(
                        new Session(
                                new Time(horasEM
                                        ,minutosEM
                                        ,0)
                                ,new Time(horasSM
                                ,minutosSM
                                ,0))
                        ,new Session(
                        new Time(horasET
                                ,minutosET
                                ,0)
                        ,new Time(horasST
                        ,minutosST
                        ,0))
                );

    }
}
