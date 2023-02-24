package com.example.rene.nominadehoras.Visual;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.rene.nominadehoras.BD.BDActual;
import com.example.rene.nominadehoras.BD.StorageActual;
import com.example.rene.nominadehoras.ConfiguracionActividad;
import com.example.rene.nominadehoras.Dia_Actividad;
import com.example.rene.nominadehoras.EstadoActual.EA;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.DiaDeTrabajo;
import com.example.rene.nominadehoras.R;
import com.example.rene.nominadehoras.Semana_Actividad;
import com.rene.android.reneandroid.ArchivoAndroid;
import com.rene.android.reneandroid.Clases.Actividad;
import com.rene.android.reneandroid.Clases.Apoyo.EquipoDeMenuPrincipal;
import com.rene.android.reneandroid.Clases.Tipos.TipoDePermiso;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.Calculador.CantidadDeTiempo;

import java.io.File;

/**
 * Created by Rene on 1/1/2022.
 */

public class ActividadDeProyectoN extends Actividad{

    public BDActual bd;
    public StorageActual st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pedirPermiso(TipoDePermiso.ESCRITURA,TipoDePermiso.ESCRITURA);
        ArchivoAndroid.crearCarpetaInterna("LeyenApps/NominaDeHoras");
//        toast("1");
//        System.out.println("salida: uno0");
        this.st=new StorageActual(this);
//        toast("2");
        try {
//            System.out.println("salida: va a crear la bd");
            bd=new BDActual(this);
//            System.out.println("salida: creo la bd");
            if(st.loadHayQueCrearLaBD()){
                bd.crearTablas();
                st.saveHayQueCrearLaBD(false);
            }
//            System.out.println("salida: termino el primer proceso");
//            toast("3");
        } catch (Exception e) {
//            toast("4");
//            System.out.println("salida: fue error el la acitvidad general");
            e.printStackTrace();
            responderExcepionLog(e);
            responderException(e);

        }
    }


    public void setMenuGeneral(){
        setMenu(new EquipoDeMenuPrincipal(R.menu.menu_general, new Utilizar2<Actividad, MenuItem>() {
            @Override
            public void utilizar(Actividad actividad, MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mi_configuracion:
                        irAConfiguracion();
                        break;
                    case R.id.mi_atras:
                        irAtras();
                        break;
                    //case R.id.mi_fecha:

                }
            }
        }));
    }
    public static double getResultadoDelCalculo(CantidadDeTiempo c,double multiplicador){
        double horas=c.getHoras();
        double minutos=c.getMinutos();
        //System.out.println("salida: horas"+horas);
        //System.out.println("salida: minutos"+minutos);
        double resultado=multiplicador*horas;
        //System.out.println("salida: EA.cnf.valor_a_multiplicar*horas"+(EA.cnf.valor_a_multiplicar*horas));
        resultado+=multiplicador*(minutos/60);
        //System.out.println("salida: (minutos/60)"+(minutos/60));
        //System.out.println("salida: EA.cnf.valor_a_multiplicar*(minutos/60)"+(EA.cnf.valor_a_multiplicar*(minutos/60)));
        return resultado;

    }
    public static double getResultadoDelCalculo(CantidadDeTiempo c){
        return  getResultadoDelCalculo(c,EA.cnf.valor_a_multiplicar);
//        double horas=c.getHoras();
//        double minutos=c.getMinutos();
//        //System.out.println("salida: horas"+horas);
//        //System.out.println("salida: minutos"+minutos);
//        double resultado=EA.cnf.valor_a_multiplicar*horas;
//        //System.out.println("salida: EA.cnf.valor_a_multiplicar*horas"+(EA.cnf.valor_a_multiplicar*horas));
//        resultado+=EA.cnf.valor_a_multiplicar*(minutos/60);
//        //System.out.println("salida: (minutos/60)"+(minutos/60));
//        //System.out.println("salida: EA.cnf.valor_a_multiplicar*(minutos/60)"+(EA.cnf.valor_a_multiplicar*(minutos/60)));
//        return resultado;
    }

//    public static File log(String mensaje) {
//        File f=null;
//        try{f=ArchivoAndroid.escribirLogInternoLApp("NominaDeHoras","log",mensaje);}catch (Exception ex){}
//        return f;
//    }
//    public static File logError(String mensaje) throws Exception{
//
//        return ArchivoAndroid.escribirLogInternoLApp("NominaDeHoras","logError",mensaje);
//    }


    public void irAConfiguracion(){
       // EA.actividadAnteriorAConfiguracion=this.getClass();
        irActivity(ConfiguracionActividad.class);
    }
    public void irASemanas(){
        //EA.actividadAnteriorAConfiguracion=this.getClass();
        irActivity(Semana_Actividad.class);
    }
    public void irADias(){
       // EA.actividadAnteriorAConfiguracion=this.getClass();
        irActivity(Dia_Actividad.class);
    }
    public void irAtras(){
        Class anterior=EA.actividadAnteriorAConfiguracion;
        //EA.actividadAnteriorAConfiguracion=this.getClass();
        irActivity(anterior);
    }


}
