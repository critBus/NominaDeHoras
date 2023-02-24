package com.example.rene.nominadehoras;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rene.nominadehoras.EstadoActual.EA;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.DiaDeTrabajo;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.PlantillaTrabajador;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.Session;
import com.example.rene.nominadehoras.Logica.Trabajador;
import com.example.rene.nominadehoras.Visual.ConfiguracionDiaActividad;
import com.example.rene.nominadehoras.Visual.Dialogos.ContenidoDialogoDia;
import com.example.rene.nominadehoras.Visual.ModoAgregar;
import com.rene.android.reneandroid.ArchivoAndroid;
import com.rene.android.reneandroid.Clases.Actividad;
import com.rene.android.reneandroid.Clases.Apoyo.EquipoDeMenuPrincipal;
import com.rene.android.reneandroid.Clases.Apoyo.ListaAdaptable;
import com.rene.android.reneandroid.Clases.Dialogos.DialogoFragment;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar3ConException;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.Calculador.CantidadDeTiempo;
import com.rene.android.reneandroid.Utiles.MetodosUtiles.Archivo;
//import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar3ConException;

//import  Utiles.MetodosUtiles.MetodosUtiles;

//import java.util.function.Predicate;
import com.example.rene.nominadehoras.Visual.ActividadDeProyectoN;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.sql.Time;
import java.util.Date;

public class Dia_Actividad extends ActividadDeProyectoN {
//    private PlantillaTrabajador pla=new PlantillaTrabajador(new Trabajador("Uno"),new DateTime());
    //ConfiguracionDiaActividad cnf=new ConfiguracionDiaActividad();
    private ListaAdaptable<DiaDeTrabajo> listaDeDias;
    private TextView tv_calculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia__actividad);

        tv_calculo=(TextView)findViewById(R.id.tv_calculo);
        setTitle("Días");
        //toast(EA.cnf.diaDefault.getMañana().getEntradaStr());
        //System.out.println("salida: 0 EA.cnf.diaDefault.getMañana().getEntradaStr()="+EA.cnf.diaDefault.getMañana().getEntradaStr());
        setHorizontal();
        EA.hideProgresDialog();
        setMenuGeneral();








        DiaDeTrabajo D[]=EA.pla.getDiasDeTrabajos().toArray(new DiaDeTrabajo[0]);

        final Actividad estaActividad=this;

        listaDeDias=setListaAdaptable(R.id.lvSessiones,R.layout.item_header_sessiones_dia, R.layout.item_list_sessiones_dia, D, new Utilizar3ConException<View, DiaDeTrabajo, Integer>() {
                    @Override
                    public void utilizar(View view, final DiaDeTrabajo s, Integer integer) throws Exception {
                        TextView tvNumero = (TextView) view.findViewById(R.id.tvNumero);
                        TextView tvFecha = (TextView) view.findViewById(R.id.tvFecha);
                        TextView tvEntrada1 = (TextView) view.findViewById(R.id.tvEntrada1);
                        TextView tvSalida1 = (TextView) view.findViewById(R.id.tvSalida1);
                        TextView tvEntrada2 = (TextView) view.findViewById(R.id.tvEntrada2);
                        TextView tvSalida2 = (TextView) view.findViewById(R.id.tvSalida2);
                        TextView tvHoras = (TextView) view.findViewById(R.id.tvHoras);
                        TextView tvTotal = (TextView) view.findViewById(R.id.tvTotal);
                        ImageButton bEditar = (ImageButton) view.findViewById(R.id.bEditar);
                        ImageButton bBorrar = (ImageButton) view.findViewById(R.id.bBorrar);
                        ImageButton bAgregar = (ImageButton) view.findViewById(R.id.bAgregar);

                        tvNumero.setText((integer+1)+"");
                        tvFecha.setText(EA.pla.getFechaStr(integer));
                        tvEntrada1.setText(s.getMañana().getEntradaStr());
                        tvSalida1.setText(s.getMañana().getSalidaStr());
                        tvEntrada2.setText(s.getTarde().getEntradaStr());
                        tvSalida2.setText(s.getTarde().getSalidaStr());
                        tvHoras.setText(EA.pla.getCantidadDeHorasTrabajadasStr(integer));
                        tvTotal.setText(EA.pla.getCantidadDeHorasTrabajadasTotalesStr(integer));

                        DateTime d=EA.pla.getFecha(integer);
                        switch (d.getDayOfWeek()){
                            case 6:
                                view.setBackgroundResource(R.color.azulClaro);
                                break;
                            case 7:
                                view.setBackgroundResource(R.color.gris_claro);
                                break;
                            default:
                                view.setBackgroundResource(R.color.blanco);
                                break;

                        }
                        final int indice=integer;
                        bEditar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDialogoEditar(indice);


                            }
                        });
                        bBorrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDialogAdvertenciaAceptarCancelar("Borrar?", new Utilizar2<DialogFragment, View>() {
                                    @Override
                                    public void utilizar(DialogFragment dialogFragment, View view) {
                                        //EA.pla.getDiasDeTrabajos().remove(indice);
                                        try {
                                            EA.pla=bd.elimniarDia(EA.pla,indice);

                                        actualizarLista();
                                        actualizarCalculo();

                                            EA.pla=bd.guardarPlantilla(EA.pla);
                                        } catch (Exception e) {
                                            responderException(e);
                                        }
                                    }
                                });
                            }
                        });

                        bAgregar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (EA.cnf.modoAgregar== ModoAgregar.MOSTRAR_DIALOGO_NUEVO){
                                    ContenidoDialogoDia.showDialogoAgregar(estaActividad, new Utilizar<ContenidoDialogoDia>() {
                                        @Override
                                        public void utilizar(ContenidoDialogoDia a) {
                                           try{
                                            EA.pla.addPosteriorCopia(indice,a.getDiaDeTrabajo());

                                            actualizarLista();
                                            actualizarCalculo();
                                            //pla.getDiasDeTrabajos().add(indice,a.getDiaDeTrabajo());
                                            EA.pla=bd.guardarPlantilla(EA.pla);
                                        } catch (Exception e) {
                                            responderException(e);
                                        }
                                        }
                                    });
                                }else{
                                    showDialogAdvertenciaAceptarCancelar("Agregar?", new Utilizar2<DialogFragment, View>() {
                                        @Override
                                        public void utilizar(DialogFragment dialogFragment, View view) {
                                            try {  //System.out.println("salida: "+EA.cnf.modoAgregar);
                                            switch (EA.cnf.modoAgregar){
                                                case DEFAULT:
                                                    //System.out.println("salida: 1 EA.cnf.diaDefault.getMañana().getEntradaStr()="+EA.cnf.diaDefault.getMañana().getEntradaStr());
                                                    EA.pla.addPosteriorCopia(indice,EA.cnf.diaDefault);

                                                    break;
                                                case VACIO:
                                                    EA.pla.addPosteriorVacio(indice);

                                                    break;
                                                case IGUAL_A_ANTERIOR:
                                                    if (indice==0){
                                                        EA.pla.addPosteriorVacio(indice);

                                                        break;
                                                    }
                                                    DiaDeTrabajo anterior=EA.pla.getDiasDeTrabajos().get(indice);
                                                    EA.pla.addPosteriorCopia(indice,anterior);

                                                    break;

                                            }

                                                EA.pla=bd.guardarPlantilla(EA.pla);
                                            } catch (Exception e) {
                                                responderException(e);
                                                responderExcepionLog(e);
                                            }
                                        }
                                    });


                                }




                            }
                        });


                    }
                });
        actualizarCalculo();
        EA.actividadAnteriorAConfiguracion=Semana_Actividad.class;

    }

    public void presionoBotonEditarFecha(View v){

        DateTime dt=EA.pla.getFechaInicial();


        DatePickerDialog dp=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                try {
                EA.pla.setFechaInicial(new DateTime(year,month+1,dayOfMonth,0,0));

                    EA.pla=bd.guardarPlantilla(EA.pla);

                actualizarLista();
                } catch (Exception e) {
                    responderException(e);
                }
            }
        }, dt.year().get(), dt.monthOfYear().get()-1, dt.dayOfMonth().get());

        dp.show();
    }

    public void presionoBotonAgregarAlFinal(View v){
        if (EA.cnf.modoAgregar== ModoAgregar.MOSTRAR_DIALOGO_NUEVO){
            ContenidoDialogoDia.showDialogoAgregar(this, new Utilizar<ContenidoDialogoDia>() {
                @Override
                public void utilizar(ContenidoDialogoDia a) {
                    try {
                    EA.pla.getDiasDeTrabajos().add(a.getDiaDeTrabajo());
                    //log("01 EA.pla.getDiasDeTrabajos().size()="+EA.pla.getDiasDeTrabajos().size());
                    actualizarLista();
                    actualizarCalculo();
                    //log("02 EA.pla.getDiasDeTrabajos().size()="+EA.pla.getDiasDeTrabajos().size());
                    //pla.getDiasDeTrabajos().add(indice,a.getDiaDeTrabajo());

                        //log("03 EA.pla.getDiasDeTrabajos().size()="+EA.pla.getDiasDeTrabajos().size());
                        EA.pla=bd.guardarPlantilla(EA.pla);
                    } catch (Exception e) {
                        responderException(e);
                    }
                }
            });
        }else {
            showDialogAdvertenciaAceptarCancelar("Agregar?", new Utilizar2<DialogFragment, View>() {
                @Override
                public void utilizar(DialogFragment dialogFragment, View view) {
                    try{
                    switch (EA.cnf.modoAgregar){
                        case DEFAULT:

                            EA.pla.add(EA.cnf.diaDefault);
                            break;

                        case VACIO:

                            EA.pla.addVacio();
                            break;

                        case IGUAL_A_ANTERIOR:
                            if(EA.pla.getDiasDeTrabajos().isEmpty()){
                                EA.pla.add(EA.cnf.diaDefault);
                                break;
                            }

                            int indice=EA.pla.getDiasDeTrabajos().size()-1;
                            DiaDeTrabajo anterior=EA.pla.getDiasDeTrabajos().get(indice);
                            EA.pla.addPosteriorCopia(indice,anterior);

                            break;

                    }
                    actualizarLista();
                    actualizarCalculo();

                    EA.pla=bd.guardarPlantilla(EA.pla);
                } catch (Exception e) {
                    responderException(e);
                }
                }
            });
        }


    }

    public void presionoEliminarElUltimo(View v){
        if(!EA.pla.getDiasDeTrabajos().isEmpty()){
            showDialogAdvertenciaAceptarCancelar("Borrar?", new Utilizar2<DialogFragment, View>() {
                @Override
                public void utilizar(DialogFragment dialogFragment, View view) {
                    try {
                    EA.pla.getDiasDeTrabajos().remove(EA.pla.getDiasDeTrabajos().size()-1);
                       EA.pla=bd.guardarPlantilla(EA.pla);

                    actualizarLista();
                        actualizarCalculo();
                    } catch (Exception e) {
                        responderException(e);
                    }

                }
            });
        }
    }

//    private void agregarDiaAlFinal(){
//
//
//    }

//    private void mostrarDialogoParaAgregarALFinalPersonalizado(){
//
//    }

    private void actualizarCalculo(){
        CantidadDeTiempo c=EA.pla.getCantidadDeHorasTrabajadasTotales();

        tv_calculo.setText(getResultadoDelCalculo(c)+"");
    }

    public void actualizarLista(){
        listaDeDias.actualizar(EA.pla.getDiasDeTrabajos().toArray(new DiaDeTrabajo[0]));

    }

    public void showDialogoEditar(final int indice){
        final DiaDeTrabajo dia=EA.pla.getDiasDeTrabajos().get(indice);
        ContenidoDialogoDia.showDialogoEditar(this, new Utilizar<ContenidoDialogoDia>() {
            @Override
            public void utilizar(ContenidoDialogoDia a) {
                a.actualizarVista(dia);
            }
        }, new Utilizar<ContenidoDialogoDia>() {
            @Override
            public void utilizar(ContenidoDialogoDia a) {
                try {
                a.actualizarDiaDesdeVista(dia);

                    EA.pla=bd.guardarPlantilla(EA.pla);

//                toast("va actualizar");
//                System.out.println("salida: va a actualizar");
//                System.out.println("salida: actualizo "+ dia.getMañana().getEntrada().getHours()+":"+dia.getMañana().getEntrada().getMinutes());
//                toast("1");
                listaDeDias.actualizarFila(indice);
                actualizarCalculo();
//                toast("2");
//                System.out.println("salida: actualizo la fila="+indice);
//                toast("3");
                } catch (Exception e) {
                    responderException(e);
                    responderExcepionLog(e);
                }

            }
        });


    }
}
