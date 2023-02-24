package com.example.rene.nominadehoras;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;


import com.example.rene.nominadehoras.BD.BDActual;
import com.example.rene.nominadehoras.EstadoActual.EA;
import com.example.rene.nominadehoras.Visual.ConfiguracionDiaActividad;
import com.rene.android.reneandroid.ArchivoAndroid;
import com.rene.android.reneandroid.Clases.Actividad;
import com.example.rene.nominadehoras.Visual.ActividadDeProyectoN;
import com.rene.android.reneandroid.Clases.RetainedFragment;
import com.rene.android.reneandroid.Clases.Tipos.TipoDeFuente;
import com.rene.android.reneandroid.Clases.Tipos.TipoDePermiso;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Creador;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;
import com.rene.android.reneandroid.VisualAndroid;

import java.io.File;



public class MainActivity extends ActividadDeProyectoN {
    public boolean seInicio=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        System.out.println("salida: uno");
        super.onCreate(savedInstanceState);

//        System.out.println("salida: dos");
        setContentView(R.layout.activity_main);
//        System.out.println("salida: tres");
        //System.out.println("salida:  al crearse");
        //log("paso por la creacion");
        //
//        System.out.println("salida: cuatro");
        //toast("22");
        vaciarLogYErrors();




        //toast("11");
        setEA(new Creador<RetainedFragment>() {
            @Override
            public RetainedFragment crear() {
                EA e=new EA();
                EA.cnf.modoAgregar=st.loadModoAgregar();
                EA.cnf.diaDefault=st.loadDiaDefualt();
                EA.cnf.valor_a_multiplicar=st.loadValorMultiplicador();
                return e;
            }
        });
//        System.out.println("salida: llego aqui");
//        //irActivity(ConfiguracionActividad.class);
//        //irActivity(Dia_Actividad.class);
        //irActivity(Semana_Actividad.class);

        final Actividad actividadActual=this;
        Spinner spModoAgregar=setAdapterSpinner(R.id.spn_tiposDeLetras, TipoDeFuente.values(), new Utilizar2<View, Integer>() {
            @Override
            public void utilizar(View view, Integer integer) {
//                if(seInicio){
                    ponerEstilos(integer);
                    System.out.println("salida: se selecciono");
                    putSP("iTipoDeLetra",integer);
//                }else{
//                    seInicio=true;
//                }

            }
        });
        int indiceTipoDeletra=getIntSP("iTipoDeLetra",0);
        ponerEstilos(indiceTipoDeletra);
        spModoAgregar.setSelection(indiceTipoDeletra);

//        SeekBar sek=(SeekBar)findViewById(R.id.seekBar);

        int tamañoDeLetra=getIntSP("TamañoDeLetra",24);
        setOnProgres(R.id.seekBar, tamañoDeLetra, new Utilizar2<SeekBar, Integer>() {
            @Override
            public void utilizar(SeekBar seekBar, Integer integer) {
                ponerTamañosDeLetra(integer);
                putSP("TamañoDeLetra",integer);
            }
        });
//        sek.setProgress(tamañoDeLetra);
//        sek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            final boolean[] primeraSeleccion={false};
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
////                if(primeraSeleccion[0]){
//                ponerTamañosDeLetra(progress);
//                putSP("TamañoDeLetra",progress);
////                }else{
////                    primeraSeleccion[0]=true;
////                }
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
        ponerTamañosDeLetra(tamañoDeLetra);
        //seInicio=true;
    }

    public  void ponerEstilos(int indice){
        Button b=(Button)findViewById(R.id.bt_calculo);
        Button b2=(Button)findViewById(R.id.bt_ver_nominas);
        Button B[]={b,b2};
        for (int i = 0; i < B.length; i++) {
            TipoDeFuente.setTypeface(this,B[i],TipoDeFuente.values()[indice]);
        }
//        b.setBackgroundColor(Color.BLUE);
//
//        ColorDrawable cd=new ColorDrawable(Color.BLUE);
//        cd.get
//        RoundedBitmapDrawableFactory rbf=RoundedBitmapDrawableFactory.create(R.layout.action_bar_personalizado,Bi);
//
//        b.setBackground);

    }

    public  void ponerTamañosDeLetra(int tamaño){
        Button b=(Button)findViewById(R.id.bt_calculo);
        Button b2=(Button)findViewById(R.id.bt_ver_nominas);
        Button B[]={b,b2};
        for (int i = 0; i < B.length; i++) {
            setTextSise(B[i],tamaño);
        }
    }

    public void apretoEnNominas(View v){
        irASemanas();
    }
    public void apretoEnCalcular(View v){
        irActivity(Calculo_Actividad.class);
    }
}
