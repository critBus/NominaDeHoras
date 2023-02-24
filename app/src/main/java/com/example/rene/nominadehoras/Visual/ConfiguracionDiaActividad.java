package com.example.rene.nominadehoras.Visual;

import com.example.rene.nominadehoras.Logica.LogicaSeparada.DiaDeTrabajo;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.Session;

import java.sql.Time;

/**
 * Created by Rene on 10/1/2022.
 */

public class ConfiguracionDiaActividad {
    //public enum ModoAgregar{IGUAL_A_ANTERIOR,DEFAULT,VACIO}
    public ModoAgregar modoAgregar=ModoAgregar.DEFAULT;
    public DiaDeTrabajo diaDefault=new DiaDeTrabajo(new Session(new Time(9,0,0),new Time(1,0,0)),new Session(new Time(1,0,0),new Time(5,0,0)));
    public double valor_a_multiplicar=1.7;
}
