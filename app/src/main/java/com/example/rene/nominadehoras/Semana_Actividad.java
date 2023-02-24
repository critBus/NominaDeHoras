package com.example.rene.nominadehoras;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rene.nominadehoras.EstadoActual.EA;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.ConjuntoDePlantillasDeTrabajador;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.PlantillaTrabajador;
import com.example.rene.nominadehoras.Logica.Trabajador;
import com.example.rene.nominadehoras.Visual.ActividadDeProyectoN;
import com.example.rene.nominadehoras.Visual.UtilesProyecto;
import com.rene.android.reneandroid.Clases.Actividad;
import com.rene.android.reneandroid.Clases.Apoyo.ListaAdaptable;
import com.rene.android.reneandroid.Clases.Dialogos.DialogoAceptarCancelarInputLine;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar3;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar3ConException;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Tiempo.Calculador.CantidadDeTiempo;

import org.joda.time.DateTime;

import java.util.List;

public class Semana_Actividad extends ActividadDeProyectoN {

    private Spinner spn_semanas;

    //private ConjuntoDePlantillasDeTrabajador conjuntoSeleccionado;
    private ListaAdaptable<PlantillaTrabajador> listaAdaptable;

    private TextView tv_calculo,tv_cantidad_de_horas;
    private Boolean yaInicioLaApp;

    //private ProgressDialog cargandoIrAActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //System.out.println("paso el on create en semana actividad");
        setContentView(R.layout.activity_semana__actividad);
//        if(cargandoIrAActividad!=null&&cargandoIrAActividad.isShowing()){
//            cargandoIrAActividad.hide();
//        }
        setTitle("Semanas");
        setVertical();
        setMenuGeneral();
        hideProgressDialog();

        EA.hideProgresDialog();
        LinearLayout l;

//        System.out.println("salida: entro en el onCreate");
        tv_calculo=(TextView)findViewById(R.id.tv_calculo);
        tv_cantidad_de_horas=(TextView)findViewById(R.id.tv_cantidad_de_horas);
        actualizarListaDeTrabajadores();
        actualizarLista();
        actualizarCalulo();
        yaInicioLaApp=true;
        EA.actividadAnteriorAConfiguracion=MainActivity.class;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(yaInicioLaApp!=null&&yaInicioLaApp){
            actualizarLista();
            actualizarCalulo();
        }

    }

    public void actualizarListaDeTrabajadores(){
        //spn_semanas=(Spinner)findViewById(R.id.sp_semanas);
        ConjuntoDePlantillasDeTrabajador CO[]= new ConjuntoDePlantillasDeTrabajador[0];
        try {
            CO = bd.getListConjuntoDePlantillasDeTrabajador().toArray(new ConjuntoDePlantillasDeTrabajador[0]);


        spn_semanas=setAdapterSpinner(R.id.sp_semanas, CO, new Utilizar2<View, Integer>() {
            @Override
            public void utilizar(View view, Integer integer) {
                try {
                    actualizarLista(bd.getListConjuntoDePlantillasDeTrabajador().get(integer));

                st.saveIndiceTrabajadorConjuntoPlantillaSeleccionado(integer);
                    actualizarCalulo();
                    //System.out.println("salida: selecciono "+integer);
                } catch (Exception e) {
                    responderException(e);
                    responderExcepionLog(e);
                }

            }
        });
            if(CO.length>0){
                int indiceAnterior=st.loadIndiceTrabajadorConjuntoPlantillaSeleccionado();
                if(indiceAnterior<0||(!(indiceAnterior<CO.length))){
                    indiceAnterior=0;
                }
                spn_semanas.setSelection(indiceAnterior);
                actualizarLista(CO[indiceAnterior]);
            }else{
                //System.out.println("salida: conjunto vacio");
            }


        } catch (Exception e) {
            responderException(e);
            responderExcepionLog(e);
        }

    }

    public void actualizarLista(){
        try {
            if(listaAdaptable!=null){

                List<ConjuntoDePlantillasDeTrabajador> listaConjuntoDePlantillasDeTrabajador= null;

                listaConjuntoDePlantillasDeTrabajador = bd.getListConjuntoDePlantillasDeTrabajador();

                int indiceAnterior=st.loadIndiceTrabajadorConjuntoPlantillaSeleccionado();
                int cantidadActual=listaConjuntoDePlantillasDeTrabajador.size();
                if(cantidadActual>0){
                    if (indiceAnterior<0||(!(indiceAnterior<cantidadActual))){
                        indiceAnterior=0;
                    }
                    if(indiceAnterior!=0){
                        spn_semanas.setSelection(indiceAnterior);
                    }
                    ConjuntoDePlantillasDeTrabajador co=listaConjuntoDePlantillasDeTrabajador.get(indiceAnterior);

                    actualizarLista(co);
                    //System.out.println("salida:  actualizo con "+co.getListPlantillaTrabajador().size());
                }else{
                    listaAdaptable.actualizar(new PlantillaTrabajador[0]);
                    //System.out.println("salida: la plantiila estaba vacia");
                }
            }else{
                //System.out.println("salida: lista fue null");
            }


        } catch (Exception e) {
            //System.out.println("salida: errorrrrrrrrrrrrrrrrrrrrrrr");
            //System.out.println("salida: "+e.getMessage());
            //System.out.println("salida: "+e.getLocalizedMessage());
            e.printStackTrace();
            responderException(e);
            responderExcepionLog(e);
        }

    }
    public void actualizarLista(ConjuntoDePlantillasDeTrabajador conjuntoSeleccionado){
        if(conjuntoSeleccionado!=null){
            PlantillaTrabajador C[]= conjuntoSeleccionado.getListPlantillaTrabajador().toArray(new PlantillaTrabajador[0]);
            final Actividad estaActividad=this;
            if(listaAdaptable==null){
                listaAdaptable=setListaAdaptable(R.id.lvSessiones, R.layout.item_semana, C, new Utilizar3ConException<View, PlantillaTrabajador, Integer>() {
                    @Override
                    public void utilizar(View view, PlantillaTrabajador plantillaTrabajador, Integer integer) throws Exception {
                        TextView tv_fecha=(TextView) view.findViewById(R.id.tvNombre);
                        TextView tv_horas_trabajadas=(TextView) view.findViewById(R.id.tv_horas_trabajadas);
                        TextView tv_calculo=(TextView) view.findViewById(R.id.tv_calculo);

                        ImageButton boton_editar=(ImageButton)view.findViewById(R.id.imb_editar);
                        ImageButton boton_eliminar=(ImageButton)view.findViewById(R.id.imb_eliminar);

                        tv_fecha.setText(UtilesProyecto.getFechaStr(plantillaTrabajador.getFechaInicial()));
                        tv_horas_trabajadas.setText(plantillaTrabajador.getCantidadDeHorasTrabajadasTotalesStr());
                        //System.out.println("salida: paso por actualizar "+String.format("%.02f",getResultadoDelCalculo(plantillaTrabajador.getCantidadDeHorasTrabajadasTotales())));
                        tv_calculo.setText(String.format("%.02f",getResultadoDelCalculo(plantillaTrabajador.getCantidadDeHorasTrabajadasTotales())));


                        final int indice=integer;
                        final PlantillaTrabajador plan=plantillaTrabajador;

                        boton_eliminar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDialogAdvertenciaAceptarCancelar("Borrar esta Plantilla", new Utilizar2<DialogFragment, View>() {
                                    @Override
                                    public void utilizar(DialogFragment dialogFragment, View view) {
                                        try {
                                            bd.eliminarPlantilla(plan.getTrabajador(),indice);

                                        actualizarLista();
                                        actualizarCalulo(); } catch (Exception e) {
                                            responderException(e);
                                            responderExcepionLog(e);
                                        }
                                    }
                                });
                            }
                        });
                        boton_editar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //System.out.println("salida: apreto en ver detalles "+indice);
                                //EA.pla=bd.getPlantillaTrabajador(plan.getTrabajador(),indice);
                                EA.pla=plan;
                                estaActividad.subProcesosEnVisual(new Realizar() {
                                                                      @Override
                                                                      public void realizar() {
                                                                          EA.pd= estaActividad.showProgressDialog("Cargando..");
                                                                      }
                                                                  },
                                        new Realizar() {
                                            @Override
                                            public void realizar() {
                                                irActivity(Dia_Actividad.class);

                                            }
                                        });


                                //irActivity(Dia_Actividad.class);



                            }
                        });
                    }
                });
            }else{
                listaAdaptable.actualizar(C);
            }


        }

    }

    public void alAPretarEnAgregarTrabajador(View v){
        final Actividad estaActividad=this;
        showDialogoAceptarCancelarInputLine("Nombre del Trabajador", "Nombre", new Utilizar3<DialogoAceptarCancelarInputLine, View, String>() {
            @Override
            public void utilizar(DialogoAceptarCancelarInputLine dialogFragment, View view, String s) {
                if(s.isEmpty()){
                    showDialogAdvertencia("El Nombre no puede estar vacio");
                    return;
                }
                try {
                    if(bd.existeTrabajador(s)){
                        showDialogAdvertencia("El Trabajador ya Existe");
                        return;
                    }


                    bd.agregarTrabajador(s);

                int indice= 0;

                    indice = bd.getListConjuntoDePlantillasDeTrabajador().size()-1;

                st.saveIndiceTrabajadorConjuntoPlantillaSeleccionado(indice);
                actualizarListaDeTrabajadores();
                actualizarLista();
                } catch (Exception e) {
                    responderException(e);
                    responderExcepionLog(e);
                }
            }
        });
    }

    public void alAPretarEnEditarTrabajador(View v){
        //System.out.println("salida: cantidad en spiner="+spn_semanas.getCount());
        if(spn_semanas.getCount()==0){
            showDialogAdvertencia("Primero agregue un trabajador");
            return;
        }

        final Actividad estaActividad=this;
        int indice=getIndiceTrabajadorSeleccionado();
        try {
        final ConjuntoDePlantillasDeTrabajador c;

            c = bd.getConjuntoDePlantillasDeTrabajador(indice);

        final Trabajador t=c.getTrabajador();


        showDialogoAceptarCancelarInputLine("Nombre del Trabajador", "Nombre", new Utilizar2<DialogoAceptarCancelarInputLine, View>() {
            @Override
            public void utilizar(DialogoAceptarCancelarInputLine dialogFragment, View view) {
                dialogFragment.setTexto(t.getNombre());
            }
        }, new Utilizar3<DialogoAceptarCancelarInputLine, View, String>() {
            @Override
            public void utilizar(DialogoAceptarCancelarInputLine dialogFragment, View view, String s) {
                if (s.isEmpty()) {
                    showDialogAdvertencia("El Nombre no puede estar vacio");
                    return;
                }
                try {
                    if ((!s.equals(t.getNombre()))&&bd.existeTrabajador(s)) {
                        showDialogAdvertencia("El Trabajador ya Existe");
                        return;
                    }

                t.setNombre(s);

                    bd.editarTrabajador(t);



                actualizarListaDeTrabajadores();
                int indiceAnterior=st.loadIndiceTrabajadorConjuntoPlantillaSeleccionado();
                spn_semanas.setSelection(indiceAnterior);
                } catch (Exception e) {
                    responderException(e);
                    responderExcepionLog(e);
                }
                //actualizarLista();
            }
        });
        } catch (Exception e) {
            responderException(e);
            responderExcepionLog(e);
        }
    }

    public void alAPretarEnEliminarTrabajador(View v){
        if(spn_semanas.getCount()==0){
            showDialogAdvertencia("Primero agregue un trabajador");
            return;
        }
        final Actividad estaActividad=this;
        final int indice=getIndiceTrabajadorSeleccionado();
        try {
        final ConjuntoDePlantillasDeTrabajador c;

            c = bd.getConjuntoDePlantillasDeTrabajador(indice);

        final Trabajador t=c.getTrabajador();

        showDialogAdvertenciaAceptarCancelar("Eliminar este Trabajador", new Utilizar2<DialogFragment, View>() {
            @Override
            public void utilizar(DialogFragment dialogFragment, View view) {
                try {
                    bd.eliminarTrabajador(indice);

                int indice=bd.getListConjuntoDePlantillasDeTrabajador().size()-1;
                st.saveIndiceTrabajadorConjuntoPlantillaSeleccionado(indice);
                actualizarListaDeTrabajadores();
                actualizarLista();
                actualizarCalulo();
                } catch (Exception e) {
                    e.printStackTrace();
                    responderExcepionLog(e);
                }

            }
        });
        } catch (Exception e) {
            e.printStackTrace();
            responderExcepionLog(e);
        }

    }

    public void apretoEnAgregarPlantilla(View v){
        //System.out.println("salida: cantidad en spiner="+spn_semanas.getCount());
        if(spn_semanas.getCount()==0){
            showDialogAdvertencia("Primero agregue un trabajador");
            return;
        }
        showDialogDate_DateTime(DateTime.now().minusDays(7), new Utilizar2<View, DateTime>() {
            @Override
            public void utilizar(View view, DateTime dateTime) {
                int indice=getIndiceTrabajadorSeleccionado();

                try {
                    bd.agregarPlantilla(bd.getConjuntoDePlantillasDeTrabajador(indice),dateTime);

                actualizarLista();
                } catch (Exception e) {
                    e.printStackTrace();
                    responderExcepionLog(e);
                }
            }
        });

    }

    public void apretoEnVaciarLista(View v){
        //System.out.println("salida: cantidad en spiner="+spn_semanas.getCount());
        if(spn_semanas.getCount()==0){
            showDialogAdvertencia("Primero agregue un trabajador");
            return;
        }
        int indice=getIndiceTrabajadorSeleccionado();
        try {
        final ConjuntoDePlantillasDeTrabajador c;

            c = bd.getConjuntoDePlantillasDeTrabajador(indice);

        if(c.getListPlantillaTrabajador().isEmpty()){
            showDialogAdvertencia("Primero agregue una plantilla");
            return;
        }

        showDialogAdvertenciaAceptarCancelar("Eliminar todas las plantillas\nde este trabajador", new Utilizar2<DialogFragment, View>() {
            @Override
            public void utilizar(DialogFragment dialogFragment, View view) {
                try {
                    bd.vaciarPlantillasDeTrabajador(c.getTrabajador());

                actualizarLista();
                actualizarCalulo(); } catch (Exception e) {
                    responderException(e);
                    responderExcepionLog(e);
                }
            }
        });
        } catch (Exception e) {
            responderException(e);
            responderExcepionLog(e);
        }
    }


    public void actualizarCalulo(){
        try {
        String textoAPonerEnCalculo="";
        String textoAPonerEnCantidadDeHoras="";
        //System.out.println("salida: spn_semanas.getCount()=="+spn_semanas.getCount());
        if(spn_semanas.getCount()>0){
            CantidadDeTiempo c= null;

                c = bd.getConjuntoDePlantillasDeTrabajador(spn_semanas.getSelectedItemPosition()).getCantidadTiempoTotal();

            //System.out.println("salida: calculo="+getResultadoDelCalculo(c));
            textoAPonerEnCalculo=getResultadoDelCalculo(c)+"";
            textoAPonerEnCantidadDeHoras=UtilesProyecto.getHorasStr(c);

        }

        tv_cantidad_de_horas.setText(textoAPonerEnCantidadDeHoras);
        tv_calculo.setText(textoAPonerEnCalculo);
        } catch (Exception e) {
            responderException(e);
            responderExcepionLog(e);
        }
    }

    private int getIndiceTrabajadorSeleccionado(){
        return  spn_semanas.getSelectedItemPosition();
    }
}
