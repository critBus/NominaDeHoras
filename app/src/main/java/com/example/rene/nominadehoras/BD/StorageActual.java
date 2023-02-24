package com.example.rene.nominadehoras.BD;

import com.example.rene.nominadehoras.EstadoActual.EA;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.DiaDeTrabajo;
import com.example.rene.nominadehoras.Visual.ModoAgregar;
import com.example.rene.nominadehoras.Visual.UtilesProyecto;
import com.rene.android.reneandroid.Clases.Actividad;

/**
 * Created by Rene on 22/3/2022.
 */

public class StorageActual {
    public static final String KEY_MODO_AGREGAR="KEY_MODO_AGREGAR";
    public static final String KEY_DIA_DEFAULT_MAÑANA_ENTRADA="KEY_DIA_DEFAULT_MAÑANA_ENTRADA";
    public static final String KEY_DIA_DEFAULT_MAÑANA_SALIDA="KEY_DIA_DEFAULT_MAÑANA_SALIDA";
    public static final String KEY_DIA_DEFAULT_TARDE_ENTRADA="KEY_DIA_DEFAULT_TARDE_ENTRADA";
    public static final String KEY_DIA_DEFAULT_TARDE_SALIDA="KEY_DIA_DEFAULT_TARDE_SALIDA";
    public static final String KEY_VALOR_MULTIPLICADOR="KEY_VALOR_MULTIPLICADOR";
    public static final String KEY_INDICE_TRABAJADOR_CONJUNTO_PLANTILLA_SELECCIONADO="KEY_INDICE_TRABAJADOR_CONJUNTO_PLANTILLA_SELECCIONADO";
    public static final String KEY_HAY_QUE_CREAR_LA_BD="KEY_HAY_QUE_CREAR_LA_BD";


    private Actividad actividad;

    public StorageActual(Actividad actividad) {
        this.actividad = actividad;
    }


    public void saveHayQueCrearLaBD(boolean hayQueCrearLaBD){
        this.actividad.putSP(KEY_HAY_QUE_CREAR_LA_BD,hayQueCrearLaBD);
    }
    public boolean loadHayQueCrearLaBD(){
        return this.actividad.getBoolSP(KEY_HAY_QUE_CREAR_LA_BD,true);
    }
    public void saveIndiceTrabajadorConjuntoPlantillaSeleccionado(int indice){
        this.actividad.putSP(KEY_INDICE_TRABAJADOR_CONJUNTO_PLANTILLA_SELECCIONADO,indice);
    }
    public int loadIndiceTrabajadorConjuntoPlantillaSeleccionado(){
        return this.actividad.getIntSP(KEY_INDICE_TRABAJADOR_CONJUNTO_PLANTILLA_SELECCIONADO);
    }

    public void saveValorMultiplicador(double valor){
        this.actividad.putSP(KEY_VALOR_MULTIPLICADOR,valor);
    }
    public double loadValorMultiplicador(){
        return this.actividad.getDoubSP(KEY_VALOR_MULTIPLICADOR, EA.cnf.valor_a_multiplicar);
    }
    public void saveDiaDefualt(DiaDeTrabajo d){
        this.actividad.putSP(KEY_DIA_DEFAULT_MAÑANA_ENTRADA,d.getMañana().getEntradaStr());
        this.actividad.putSP(KEY_DIA_DEFAULT_MAÑANA_SALIDA,d.getMañana().getSalidaStr());
        this.actividad.putSP(KEY_DIA_DEFAULT_TARDE_ENTRADA,d.getTarde().getEntradaStr());
        this.actividad.putSP(KEY_DIA_DEFAULT_TARDE_SALIDA,d.getTarde().getSalidaStr());

    }

    public  DiaDeTrabajo loadDiaDefualt(){
        DiaDeTrabajo d=new DiaDeTrabajo(EA.cnf.diaDefault);
        String me=this.actividad.getStringSP(KEY_DIA_DEFAULT_MAÑANA_ENTRADA,d.getMañana().getEntradaStr());
        String ms=this.actividad.getStringSP(KEY_DIA_DEFAULT_MAÑANA_SALIDA,d.getMañana().getSalidaStr());
        String te=this.actividad.getStringSP(KEY_DIA_DEFAULT_TARDE_ENTRADA,d.getTarde().getEntradaStr());
        String ts=this.actividad.getStringSP(KEY_DIA_DEFAULT_TARDE_SALIDA,d.getTarde().getSalidaStr());
        d.getMañana().setEntrada(UtilesProyecto.getTimeDeStr(me));
        d.getMañana().setSalida(UtilesProyecto.getTimeDeStr(ms));
        d.getTarde().setEntrada(UtilesProyecto.getTimeDeStr(te));
        d.getTarde().setSalida(UtilesProyecto.getTimeDeStr(ts));
        return d;
    }

    public ModoAgregar loadModoAgregar(){
        String modoGuardado=this.actividad.getStringSP(KEY_MODO_AGREGAR,ModoAgregar.IGUAL_A_ANTERIOR+"");
        return ModoAgregar.valueOf(modoGuardado);
    }

    public void saveModoAgregar(ModoAgregar m){
        this.actividad.putSP(KEY_MODO_AGREGAR,m+"");
    }
}
