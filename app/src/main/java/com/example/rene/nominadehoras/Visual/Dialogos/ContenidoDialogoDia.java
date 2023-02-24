package com.example.rene.nominadehoras.Visual.Dialogos;

import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.rene.nominadehoras.Logica.LogicaSeparada.DiaDeTrabajo;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.Session;
import com.example.rene.nominadehoras.R;
import com.example.rene.nominadehoras.Visual.Errores.DatosDiaIncorrectoException;
import com.example.rene.nominadehoras.Visual.UtilesProyecto;
import com.rene.android.reneandroid.Clases.Actividad;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Creador2;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;
import com.rene.android.reneandroid.VisualAndroid;

import java.sql.Time;
import java.util.concurrent.ScheduledExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import maskedEditText.MaskedEditText;
/**
 * Created by Rene on 10/1/2022.
 */

public class ContenidoDialogoDia {
    private TextInputLayout mtMañanaEntrada;
    private TextInputLayout mtMañanaSalida;
    private TextInputLayout mtTardeEntrada;
    private TextInputLayout mtTardeSalida;

    private Time mEntrada;
    private Time mSalida;
    private Time tEntrada;
    private Time tSalida;

    private boolean esVertical=false;
    //Pattern r = Pattern.compile("");



    public void actualizarVista(DiaDeTrabajo d){
        Session m=d.getMañana();
        Session t=d.getTarde();
        setTextoTiempo(mtMañanaEntrada,m.getEntrada());
        setTextoTiempo(mtMañanaSalida,m.getSalida());
        setTextoTiempo(mtTardeEntrada,t.getEntrada());
        setTextoTiempo(mtTardeSalida,t.getSalida());
    }

    public void actualizarDiaDesdeVista(DiaDeTrabajo d){
        d.getMañana().setEntrada(mEntrada);
        d.getMañana().setSalida(mSalida);
        d.getTarde().setEntrada(tEntrada);
        d.getTarde().setSalida(tSalida);
    }

    public DiaDeTrabajo getDiaDeTrabajo(){
        return new DiaDeTrabajo(new Session(mEntrada,mSalida),new Session(tEntrada,tSalida));

    }


    private void setTextoTiempo(TextInputLayout m,Time t){
        int h=t.getHours();
        int mi=t.getMinutes();
        if(h>0||mi>0){
            if(!(h>0)){
                //System.out.println("salida: "+String.format("00:%02d",mi));
                m.getEditText().setText(String.format("00:%02d",mi));
                return;
            }
            if(!(mi>0)){
                //System.out.println("salida: "+String.format("%02d:00",h));
                m.getEditText().setText(String.format("%02d:00",h));
                return;
            }
            //System.out.println("salida: "+String.format("%02d:%02d",h,mi));
            m.getEditText().setText(String.format("%02d:%02d",h,mi));
            return;
        }
        //System.out.println("salida: nada");
        m.getEditText().setText("");
    }

    private boolean intentaInicializar()throws DatosDiaIncorrectoException{
        mEntrada=iniTiempo(mtMañanaEntrada);
        mSalida=iniTiempo(mtMañanaSalida);

        tEntrada=iniTiempo(mtTardeEntrada);
        tSalida=iniTiempo(mtTardeSalida);
        Creador2<Time,Time,Boolean> esMenorAQueB=new Creador2<Time, Time, Boolean>() {
            @Override
            public Boolean crear(Time time, Time time2) {
                return (!UtilesProyecto.isEmptyAll(time,time2))&&esTimeAMenorQueB(time,time2);
            }
        };
        if(esMenorAQueB.crear(mSalida,mEntrada)){
        //if((!UtilesProyecto.isEmptyAll(mSalida,mEntrada))&&esTimeAMenorQueB(mSalida,mEntrada)){
            //System.out.println("salida: mSalida="+mSalida+" mEntrada="+mEntrada);
            throw new DatosDiaIncorrectoException("La Entrada en la Mañana no"+(esVertical?"\n":" ")+"puede ser Posterior\na la Salida en la Mañana");
        }
        if(esMenorAQueB.crear(tSalida,tEntrada)){
        //if(esTimeAMenorQueB(tSalida,tEntrada)){
            throw new DatosDiaIncorrectoException("La Entrada en la Tarde no"+(esVertical?"\n":" ")+"puede ser Posterior\na la Salida en la Tarde");
        }
        return true;
    }

    private boolean esTimeAMenorQueB(Time a,Time b){
        if(a.getHours()<b.getHours()){
            return true;
        }else{
            if(a.getHours()==b.getHours()&&a.getMinutes()<b.getMinutes()){
                return true;
            }
        }
        return false;
    }



    private Time iniTiempo(TextInputLayout met){
        //System.out.println("salida: met="+met.getEditText().getText());
        return UtilesProyecto.getTimeDeStr(met.getEditText().getText().toString());
    }




    public Time getmEntrada() {
        return mEntrada;
    }

    public Time getmSalida() {
        return mSalida;
    }

    public Time gettEntrada() {
        return tEntrada;
    }

    public Time gettSalida() {
        return tSalida;
    }

    public static void showDialogoAgregar(Actividad a, final Utilizar<ContenidoDialogoDia> alAceptar){
        showDialogoEditar(false,a,null,alAceptar);

    }

    public static void showDialogoEditarVertical(Actividad a, final Utilizar<ContenidoDialogoDia> alCrearse, final Utilizar<ContenidoDialogoDia> alAceptar){
        showDialogoEditar(true,a,alCrearse,alAceptar);
    }
    public static void showDialogoEditar(Actividad a, final Utilizar<ContenidoDialogoDia> alCrearse, final Utilizar<ContenidoDialogoDia> alAceptar){
        showDialogoEditar(false,a,alCrearse,alAceptar);
    }
    private static void showDialogoEditar(final boolean vertical, final Actividad a, final Utilizar<ContenidoDialogoDia> alCrearse, final Utilizar<ContenidoDialogoDia> alAceptar){
        int id = R.layout.fragment_dialogo_editar_dia;
        if(vertical){
            id=R.layout.fragment_dialogo_editar_dia_vertical;
        }
        VisualAndroid.showDialogAceptarCancelar(a,id, new Utilizar2<DialogFragment, View>() {
            @Override
            public void utilizar(DialogFragment dialogFragment, View view) {

                if(alCrearse!=null){
                    try {
                        ContenidoDialogoDia c = getContenidoDialogoDia(view,vertical);

                        alCrearse.utilizar(c);
                    }catch (DatosDiaIncorrectoException e){
                        VisualAndroid.showDialogAceptarAdvertencia(a,e.getLocalizedMessage());
                    }
                }

            }
        }, new Utilizar2<DialogFragment, View>() {
            @Override
            public void utilizar(DialogFragment dialogFragment, View view) {
                try {
                ContenidoDialogoDia c=getContenidoDialogoDia(view,vertical);

//                System.out.println("salida: 1 "+c.mtMañanaEntrada.getEditText().getText());
//                System.out.println("salida: 2 "+c.mtMañanaSalida.getEditText().getText());
//                System.out.println("salida: 3 "+c.mtTardeEntrada.getEditText().getText());
//                System.out.println("salida: 4 "+c.mtTardeSalida.getEditText().getText());
//                System.out.println("salida: 5 "+c.mEntrada);
//                System.out.println("salida: 6 "+c.mSalida);
//                System.out.println("salida: 7 "+c.tEntrada);
//                System.out.println("salida: 8 "+c.tSalida);
                alAceptar.utilizar(c);
                }catch (DatosDiaIncorrectoException e){
                    VisualAndroid.showDialogAceptarAdvertencia(a,e.getLocalizedMessage());
                }
            }
        });


    }

    private static ContenidoDialogoDia getContenidoDialogoDia(View v,boolean vertical)throws DatosDiaIncorrectoException{
        ContenidoDialogoDia c=new ContenidoDialogoDia();
        c.esVertical=vertical;
        c.mtMañanaEntrada=(TextInputLayout)v.findViewById(R.id.cME);
        c.mtMañanaSalida=(TextInputLayout)v.findViewById(R.id.cMS);
        c.mtTardeEntrada=(TextInputLayout)v.findViewById(R.id.cTE);
        c.mtTardeSalida=(TextInputLayout)v.findViewById(R.id.cTS);
        c.intentaInicializar();
        return c;
    }


}
