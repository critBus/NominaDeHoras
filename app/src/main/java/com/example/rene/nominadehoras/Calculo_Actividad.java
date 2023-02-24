package com.example.rene.nominadehoras;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.rene.nominadehoras.EstadoActual.EA;
import com.example.rene.nominadehoras.Visual.ActividadDeProyectoN;
import com.example.rene.nominadehoras.Visual.ConfiguracionDiaActividad;
import com.example.rene.nominadehoras.Visual.UtilesProyecto;
import com.rene.android.reneandroid.Clases.Actividad;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.Calculador.CantidadDeTiempo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculo_Actividad extends ActividadDeProyectoN {


    EditText et_entrada,et_multiplicador;
    TextView tv_resultado;
    Switch sw_editar_multiplcador;
    ImageButton bt_guardar,bt_default_multiplicador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo__actividad);
        setVertical();
        setTitle("Cálculo de Horas");

        setMenu(R.menu.menu_actividad_calculo, new Utilizar2<Actividad, MenuItem>() {
            @Override
            public void utilizar(Actividad actividad, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mi_atras:
                        irActivity(MainActivity.class);
                        break;
                }
            }
        });

        et_entrada=(EditText)findViewById(R.id.et_valor_entrada);
        et_multiplicador=(EditText)findViewById(R.id.et_valor_multiplacador);
        tv_resultado=(TextView)findViewById(R.id.tv_resultado_del_calculo);
        sw_editar_multiplcador=(Switch)findViewById(R.id.sw_editar_multiplicador);
        bt_guardar=(ImageButton)findViewById(R.id.bt_guardar_multiplicador);
        bt_default_multiplicador=(ImageButton)findViewById(R.id.bt_default_multiplcador);

        et_multiplicador.setText(EA.cnf.valor_a_multiplicar+"");
        setEnable(sw_editar_multiplcador.isChecked(),et_multiplicador);

        sw_editar_multiplcador.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setEnable(isChecked,et_multiplicador);
            }
        });

        Utilizar<String> alCambiarUnTextoParaElCalculo= new Utilizar<String>() {
            @Override
            public void utilizar(String a) {
                a=et_entrada.getText().toString();
                String texto_valor_multiplicar=et_multiplicador.getText().toString();
                if(texto_valor_multiplicar.isEmpty()||a.isEmpty()){
                    tv_resultado.setText("");
                    return;
                }
                CantidadDeTiempo c= UtilesProyecto.getCantidadDeTiempo(a);
                //log("c="+c);
                //System.out.println("salida: c="+c);
                double resultado=getResultadoDelCalculo(c,dou(et_multiplicador));
                // log("resultado="+resultado);
                //System.out.println("salida: resultado="+resultado);
                tv_resultado.setText(String.format("%.2f",resultado));
            }
        };

        addOnTextChanged(et_entrada,alCambiarUnTextoParaElCalculo);
        addOnTextChanged(et_multiplicador,alCambiarUnTextoParaElCalculo);


        EA.actividadAnteriorAConfiguracion=MainActivity.class;
    }
    public void apretoDefaultMultiplicador(View v){
        showDialogAdvertenciaAceptarCancelar("Valor por Defecto del\nmultiplicador", new Utilizar2<DialogFragment, View>() {
            @Override
            public void utilizar(DialogFragment dialogFragment, View view) {
                EA.cnf.valor_a_multiplicar=new ConfiguracionDiaActividad().valor_a_multiplicar;
                st.saveValorMultiplicador(EA.cnf.valor_a_multiplicar);
                et_multiplicador.setText(EA.cnf.valor_a_multiplicar+"");
            }
        });
    }

    public void apretoGuardarMultiplicador(View v){
        final String texto_valor_multiplicar=et_multiplicador.getText().toString();
        if(texto_valor_multiplicar.isEmpty()){
            showDialogAdvertencia("El valor para multilpicar no puede estar vacio");
            return;
        }


        showDialogAdvertenciaAceptarCancelar("Guardar el valor del multiplicador", new Utilizar2<DialogFragment, View>() {
            @Override
            public void utilizar(DialogFragment dialogFragment, View view) {
                EA.cnf.valor_a_multiplicar=dou(texto_valor_multiplicar);
                st.saveValorMultiplicador(EA.cnf.valor_a_multiplicar);
            }
        });
    }
}
