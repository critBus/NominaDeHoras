package com.example.rene.nominadehoras.EstadoActual;

import android.app.ProgressDialog;

import com.example.rene.nominadehoras.Logica.LogicaSeparada.ConjuntoDePlantillasDeTrabajador;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.DiaDeTrabajo;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.PlantillaTrabajador;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.Session;
import com.example.rene.nominadehoras.Logica.Trabajador;
import com.example.rene.nominadehoras.MainActivity;
import com.example.rene.nominadehoras.Visual.ConfiguracionDiaActividad;
import com.rene.android.reneandroid.Clases.RetainedFragment;


import org.joda.time.DateTime;

import java.io.File;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rene on 6/7/2020.
 */

public class EA extends RetainedFragment {
    public static final String CARPETA="TodoApp",CARPETA_=CARPETA+ File.separator;

    public static ConfiguracionDiaActividad cnf=new ConfiguracionDiaActividad();

    public static Class actividadAnteriorAConfiguracion= MainActivity.class;
    public static PlantillaTrabajador pla;

    public static ProgressDialog pd;

    public static void hideProgresDialog(){
        if(EA.pd!=null){
            EA.pd.dismiss();
            EA.pd=null;
        }
    }


    //public static BDAdmin bdAdmin;

    // va a ser eliminado despues
//    public static List<PlantillaSemanasConjuntoDeTrabajadores> listaDePlantillaSemanasConjuntoDeTrabajadores;
    public static List<ConjuntoDePlantillasDeTrabajador> listaConjuntoDePlantillasDeTrabajador;


    static {
//        DateTime d=DateTime.now();
//        Trabajador t1=new Trabajador("Uno");
//        Trabajador t2=new Trabajador("Dos");
//
//        PlantillaTrabajador pla=new PlantillaTrabajador(t1,d);
//        pla.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,11,0),new Time(1,2,0)),new Session(new Time(1,3,0),new Time(5,2,0))));
//        pla.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(12,2,0)),new Session(new Time(1,2,0),new Time(5,2,0))));
//        pla.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(3,2,0))));
//        pla.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(10,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(5,2,0))));
//        pla.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(2,2,0),new Time(5,2,0))));
//        pla.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(6,2,0))));
//        pla.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(6,2,0))));
//
//        PlantillaTrabajador pla2=new PlantillaTrabajador(t2,d.plusDays(1));
//        pla2.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,12,0),new Time(1,2,0)),new Session(new Time(1,3,0),new Time(5,2,0))));
//        pla2.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(12,2,0)),new Session(new Time(1,2,0),new Time(5,2,0))));
//        pla2.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(3,2,0))));
//        pla2.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(10,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(5,2,0))));
//        pla2.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(2,2,0),new Time(5,2,0))));
//        pla2.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(6,2,0))));
//        pla2.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(6,2,0))));
//
//
//
//
//        PlantillaTrabajador pla3=new PlantillaTrabajador(t1,d.plusDays(2));
//        pla3.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(1,3,0),new Time(5,2,0))));
//        pla3.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(12,2,0)),new Session(new Time(1,2,0),new Time(5,2,0))));
//        pla3.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(3,2,0))));
//        pla3.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(10,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(5,2,0))));
//        pla3.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(2,2,0),new Time(5,2,0))));
//        pla3.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(6,2,0))));
//        pla3.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(6,2,0))));
//
//        PlantillaTrabajador pla4=new PlantillaTrabajador(t2,d.plusDays(4));
//        pla4.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,14,0),new Time(1,2,0)),new Session(new Time(1,3,0),new Time(5,2,0))));
//        pla4.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(12,2,0)),new Session(new Time(1,2,0),new Time(5,2,0))));
//        pla4.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(3,2,0))));
//        pla4.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(10,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(5,2,0))));
//        pla4.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(2,2,0),new Time(5,2,0))));
//        pla4.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(6,2,0))));
//        pla4.getDiasDeTrabajos().add(new DiaDeTrabajo(new Session(new Time(9,13,0),new Time(1,2,0)),new Session(new Time(1,2,0),new Time(6,2,0))));
//
//
//
//        ConjuntoDePlantillasDeTrabajador c1=new ConjuntoDePlantillasDeTrabajador(t1);
//        c1.getListPlantillaTrabajador().add(pla);
//        c1.getListPlantillaTrabajador().add(pla3);
//
//        ConjuntoDePlantillasDeTrabajador c2=new ConjuntoDePlantillasDeTrabajador(t2);
//        c2.getListPlantillaTrabajador().add(pla2);
//        c2.getListPlantillaTrabajador().add(pla4);

        listaConjuntoDePlantillasDeTrabajador=new LinkedList<>();
//        listaConjuntoDePlantillasDeTrabajador.add(c1);
//        listaConjuntoDePlantillasDeTrabajador.add(c2);
    }
//public static EAInformacion eaInformacion;
//    public static EAPaquetesInternos eaPaquetesInternos;
}
