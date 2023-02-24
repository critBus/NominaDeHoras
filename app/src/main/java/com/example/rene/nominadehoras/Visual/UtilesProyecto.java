package com.example.rene.nominadehoras.Visual;

import android.support.design.widget.TextInputLayout;

import com.rene.android.reneandroid.Clases.BasicoAndroid;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.Calculador.CantidadDeTiempo;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.Tempus;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rene on 20/3/2022.
 */

public class UtilesProyecto extends BasicoAndroid {
    public static final Pattern PATRON_HORA_MINUTOS=Pattern.compile("^((?:[01]?[0-9])|(?:2[0-3]))(?:[:]((?:[0-5])?[0-9]))?$");//("^((?:[01]?[0-9])|(?:2[0-3]))(?:[:]([0-5][0-9]))?$");
    public static final Pattern PATRON_CANTIDAD_DE_HORAS=Pattern.compile("^((?:[0-9]){1,3})(?:[:]((?:[0-5])?[0-9]))?$");
    private final static DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");

    public static CantidadDeTiempo getCantidadDeTiempo(String texto){
        Matcher m=PATRON_CANTIDAD_DE_HORAS.matcher(texto);
        boolean find=m.find();
        if(find&&m.groupCount()>0){
            //log("m.group(1)="+m.group(1));
            //System.out.println("salida: m.group(1)="+m.group(1));
            int horas=Integer.parseInt(m.group(1));
            //log("horas="+horas);
            //System.out.println("salida: horas="+horas);
            int minutos=0;
//            System.out.println("salida: horas="+horas);
//            System.out.println("m.groupCount()="+m.groupCount());
            if(m.groupCount()>1&&m.group(2)!=null){
                //log("m.group(2)="+m.group(2));
                //System.out.println("salida: m.group(2)="+m.group(2));
                minutos=Integer.parseInt(m.group(2));
                //log("minutos="+minutos);
                //System.out.println("salida: minutos="+minutos);
//                System.out.println("salida: minutos="+minutos);
            }
            return new CantidadDeTiempo(horas,minutos,0);
        }
        return new CantidadDeTiempo();
    }

    public static Time getTimeDeStr(String text){
        log("text="+text);
        System.out.println("salida: text="+text);
        Matcher m=PATRON_HORA_MINUTOS.matcher(text);
        boolean find=m.find();
//        System.out.println("salida: "+find);
//        System.out.println("salida: m.groupCount()="+m.groupCount());
//        System.out.println("salida: m.groupCount()>0="+(m.groupCount()>0));
//        System.out.println("salida: find&&m.groupCount()>0="+(find&&m.groupCount()>0));
        if(find&&m.groupCount()>0){
            //log("m.group(1)="+m.group(1));
            //System.out.println("salida: m.group(1)="+m.group(1));
            int horas=Integer.parseInt(m.group(1));
            //log("horas="+horas);
            //System.out.println("salida: horas="+horas);
            int minutos=0;
//            System.out.println("salida: horas="+horas);
//            System.out.println("m.groupCount()="+m.groupCount());
            if(m.groupCount()>1&&m.group(2)!=null){
                //log("m.group(2)="+m.group(2));
                //System.out.println("salida: m.group(2)="+m.group(2));
                minutos=Integer.parseInt(m.group(2));
                //log("minutos="+minutos);
                //System.out.println("salida: minutos="+minutos);
//                System.out.println("salida: minutos="+minutos);
            }
            return new Time(horas,minutos,0);
        }
        return new Time(0,0,0);
    }
    public static String getFechaStr(DateTime d){

        return Tempus.nombresDia[d.getDayOfWeek()-1].substring(0,3)+" "+d.toString(fmt);
    }

    public static String getHorasStr(CantidadDeTiempo c){
        return String.format("%d:%02d",c.getHoras(),c.getMinutos());
    }
    public static boolean isEmptyOR(Time ... T){
        for (int i = 0; i < T.length; i++) {
            if(isEmpty(T[i])){
                return true;
            }
        }
        return false;
    }
    public static boolean isEmptyAll(Time ... T){
        for (int i = 0; i < T.length; i++) {
            if(!isEmpty(T[i])){
               return false;
            }
        }
        return true;
    }
    public static boolean isEmpty(Time t){
        return t.getHours()==0&&t.getMinutes()==0&&t.getSeconds()==0;
    }
}
