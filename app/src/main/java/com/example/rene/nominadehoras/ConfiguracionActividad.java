package com.example.rene.nominadehoras;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rene.nominadehoras.EstadoActual.EA;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.DiaDeTrabajo;
import com.example.rene.nominadehoras.Visual.ActividadDeProyectoN;
import com.example.rene.nominadehoras.Visual.ConfiguracionDiaActividad;
import com.example.rene.nominadehoras.Visual.Dialogos.ContenidoDialogoDia;
import com.example.rene.nominadehoras.Visual.ModoAgregar;
import com.rene.android.reneandroid.Clases.Actividad;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar;

import static com.example.rene.nominadehoras.Visual.ModoAgregar.*;

public class ConfiguracionActividad extends ActividadDeProyectoN {

    public static final String[] StrModosAgregar={"Igual al Anterior","Autocompletar","Vacia","Mostrar Dialogo Agregar"};

    private Spinner spModoAgregar;
    private EditText et_valor_a_multiplicar;

    private DiaDeTrabajo diaDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_actividad);

        setVertical();
        setTitle("Configuración");

        spModoAgregar=setAdapterSpinner(R.id.spModoAgregar, StrModosAgregar);
        ponerModoAgregarSeleccionado();
        et_valor_a_multiplicar=(EditText)findViewById(R.id.et_valor_a_multiplicar);
        et_valor_a_multiplicar.setText(EA.cnf.valor_a_multiplicar+"");


        EA.actividadAnteriorAConfiguracion=Dia_Actividad.class;
    }

    public void aceptar(View v){
        String texto_valr_multiplicar=et_valor_a_multiplicar.getText().toString();
        if(texto_valr_multiplicar.isEmpty()){
            showDialogAdvertencia("El valor para multilpicar no puede estar vacio");
            return;
        }

        EA.cnf.valor_a_multiplicar=dou(texto_valr_multiplicar);
        st.saveValorMultiplicador(EA.cnf.valor_a_multiplicar);
        ModoAgregar m=ModoAgregar.values()[spModoAgregar.getSelectedItemPosition()];
        EA.cnf.modoAgregar=m;
        st.saveModoAgregar(m);
        if(diaDefault!=null){
            st.saveDiaDefualt(diaDefault);
            EA.cnf.diaDefault=diaDefault;
        }

        volverAActividadAnterior();
        toast("Datos Guardados");
    }
    public void cancelar(View v){
        volverAActividadAnterior();
    }

    private void volverAActividadAnterior(){
        irActivity(EA.actividadAnteriorAConfiguracion);
    }

    public void alApretarEnConfigurarDiaDefault(View v){
        if(diaDefault==null){
            diaDefault=new DiaDeTrabajo(EA.cnf.diaDefault);
        }
        ContenidoDialogoDia.showDialogoEditarVertical(this, new Utilizar<ContenidoDialogoDia>() {
            @Override
            public void utilizar(ContenidoDialogoDia a) {
                a.actualizarVista(diaDefault);
            }
        }, new Utilizar<ContenidoDialogoDia>() {
            @Override
            public void utilizar(ContenidoDialogoDia a) {
                a.actualizarDiaDesdeVista(diaDefault);
            }
        });
    }


    public void ponerModoAgregarSeleccionado(){
        int indice=0;
        ModoAgregar m=st.loadModoAgregar();
        switch (m){
            case DEFAULT:
                indice=1;
                break;
            case IGUAL_A_ANTERIOR:
                indice=0;
                break;
            case VACIO:
                indice=2;
                break;
            case MOSTRAR_DIALOGO_NUEVO:
                indice=3;
                break;

        }
        spModoAgregar.setSelection(indice);
    }
}
