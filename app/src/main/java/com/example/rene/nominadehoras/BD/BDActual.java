package com.example.rene.nominadehoras.BD;
//package com.example.rene.nominadehoras.BD.ImplementadaCShar;

import com.example.rene.nominadehoras.BD.ImplementadaCShar.*;
import com.example.rene.nominadehoras.EstadoActual.EA;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.ConjuntoDePlantillasDeTrabajador;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.DiaDeTrabajo;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.PlantillaTrabajador;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.Session;
import com.example.rene.nominadehoras.Logica.Trabajador;
import com.rene.android.reneandroid.Clases.Actividad;
import com.rene.android.reneandroid.Clases.BasicoAndroid;
import com.rene.android.reneandroid.UtilAndroid;

import org.joda.time.DateTime;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rene on 22/3/2022.
 */

public class BDActual extends BasicoAndroid implements IBDActual {

    public BDAdmin bdAdmin;

    public BDActual(Actividad a)throws Exception {
        bdAdmin=new BDAdmin(a);
    }

    public File getFileBD(){
        return new File(bdAdmin.getUrlBD());
    }
    public void crearTablas()throws Exception{
        bdAdmin.crearTodasLasTablas();
    }
    public Trabajador getTrabajador(Trabajador_MD t_md){
        Trabajador t=new Trabajador(t_md.nombre);
        t.setIdkey(t_md.idkey);
        return t;
    }
    public  boolean existeTrabajador(String nombre)throws Exception{
        return bdAdmin.existeTrabajador_MD(nombre);
    }
    public  ConjuntoDePlantillasDeTrabajador getConjuntoDePlantillasDeTrabajador(Trabajador_MD t_md)throws Exception{
        Trabajador t=getTrabajador(t_md);
        ConjuntoDePlantillasDeTrabajador c=new ConjuntoDePlantillasDeTrabajador(t);
        List<PlantillaTrabajador_MD> lp=t_md.getPlantillaTrabajador_MD_All_Sort_Fecha_inicial();
        for (int j = 0; j < lp.size(); j++) {//
            c.getListPlantillaTrabajador().add(getPlantillaTrabajador(lp.get(j),t));
        }
        return c;
    }
    public  ConjuntoDePlantillasDeTrabajador getConjuntoDePlantillasDeTrabajador(int indice)throws Exception{
        Trabajador_MD t_md=bdAdmin.getTrabajador_MD_All().get(indice);
        return  getConjuntoDePlantillasDeTrabajador(t_md);
    }

    public  List<ConjuntoDePlantillasDeTrabajador> getListConjuntoDePlantillasDeTrabajador()throws Exception{
        List<Trabajador_MD> lt=bdAdmin.getTrabajador_MD_All();
        List<ConjuntoDePlantillasDeTrabajador> lc=new ArrayList<>();
        for (int i = 0; i < lt.size(); i++) {
            lc.add(getConjuntoDePlantillasDeTrabajador(lt.get(i)));
        }

        return lc;
    }
    private PlantillaTrabajador getPlantillaTrabajador(PlantillaTrabajador_MD p_md) throws Exception {
        Trabajador t=getTrabajador(p_md.getTrabajador());
        return getPlantillaTrabajador(p_md,t);
    }
    private PlantillaTrabajador getPlantillaTrabajador(PlantillaTrabajador_MD p_md,Trabajador t) throws Exception {
        PlantillaTrabajador p=new PlantillaTrabajador(t,p_md.fecha_inicial );//UtilAndroid.getDateTime(p_md.fecha_inicial)
        p.setIdkey(p_md.getIdkey());
        List<DiaDeTrabajo_MD> ld=p_md.getDiaDeTrabajo_MD_All();
        for (int k = 0; k < ld.size(); k++) {
            Sesion_MD sm_md=ld.get(k).getSesion_manana();
            Sesion_MD st_md=ld.get(k).getSesion_tarde();

            Session sm=getSession(sm_md);
            Session st=getSession(st_md);

            DiaDeTrabajo d=new DiaDeTrabajo(sm,st);
            d.setIdkey(ld.get(k).idkey);
            p.add(d);
        }
        return p;
    }
//    private Time getTime(Date d){
//        return new Time(d.getHours(),d.getMinutes(),0);
//    }
//    private Date getDate(Time t){
//        Date d=new Date();
//        d.setHours(t.getHours());
//        d.setMinutes(t.getMinutes());
//        return
//    }

    private Session getSession(Sesion_MD s_md){
        Session s=new Session(s_md.entrada,s_md.salida);
        s.setIdkey(s_md.idkey);
        return s;
    }
    private Sesion_MD getSession_MD(Session s) throws Exception {
        Sesion_MD s_md=new Sesion_MD(bdAdmin,s.getEntrada(),s.getSalida());
        return s_md;
    }
    private Sesion_MD getSession_MD_s(Session s) throws Exception {
        return getSession_MD(s).s();
    }

    public  Trabajador agregarTrabajador(String nombre)throws Exception{

        Trabajador_MD t_md=new Trabajador_MD(bdAdmin,nombre).s();
        Trabajador t=getTrabajador(t_md);

        return t;
    }

    public  PlantillaTrabajador elimniarDia(PlantillaTrabajador p, int indiceAEliminar)throws Exception{
        PlantillaTrabajador_MD p_md=bdAdmin.getPlantillaTrabajador_MD_id(p.getIdkey());
        p_md.getDiaDeTrabajo_MD_All().get(indiceAEliminar).d();

        //p.getDiasDeTrabajos().remove(indiceAEliminar);

        return getPlantillaTrabajador(p_md);
    }

    public  PlantillaTrabajador guardarPlantilla(PlantillaTrabajador p)throws Exception{
        PlantillaTrabajador_MD p_md=bdAdmin.getPlantillaTrabajador_MD_id(p.getIdkey());
        //System.out.println("salida: va a guardar");
        //System.out.println("salida: 0 p.getDiasDeTrabajos().size()="+p.getDiasDeTrabajos().size());
        //log("va a guardar");
        //log("0 p.getDiasDeTrabajos().size()="+p.getDiasDeTrabajos().size());
        p_md.d();
        p_md=new PlantillaTrabajador_MD(bdAdmin,bdAdmin.getTrabajador_MD_For_Nombre(p.getTrabajador().getNombre()),p.getFechaInicial()).s();
        for (int i = 0; i < p.getDiasDeTrabajos().size(); i++) {
           // System.out.println("salida: va a guarduar a "+i);
           // log(" va a guarduar a "+i);
            DiaDeTrabajo d=p.getDiasDeTrabajos().get(i);
            p_md.addDiaDeTrabajo_MD(
                    new DiaDeTrabajo_MD(bdAdmin,
                            getSession_MD_s(d.getMañana()),
                            getSession_MD_s(d.getTarde())
                    ).s()
            );
        }
        p=getPlantillaTrabajador(p_md);
        //log("p.getDiasDeTrabajos().size()="+p.getDiasDeTrabajos().size());
       // System.out.println("salida: p.getDiasDeTrabajos().size()="+p.getDiasDeTrabajos().size());
        return p;
    }

    public  PlantillaTrabajador agregarPlantilla(ConjuntoDePlantillasDeTrabajador c, DateTime d)throws Exception{
        PlantillaTrabajador_MD p_md=new PlantillaTrabajador_MD(bdAdmin,bdAdmin.getTrabajador_MD_For_Nombre(c.getTrabajador().getNombre()),d).s();

        PlantillaTrabajador p=new PlantillaTrabajador(c.getTrabajador(),d);
        p.setIdkey(p_md.getIdkey());
        c.getListPlantillaTrabajador().add(p);
        return p;
    }


    public  PlantillaTrabajador getPlantillaTrabajador(Trabajador t,int indice)throws Exception{
        //Trabajador_MD t_md=bdAdmin.getTrabajador_MD_All().get(indice);
        Trabajador_MD t_md=bdAdmin.getTrabajador_MD_For_Nombre(t.getNombre());
        return getPlantillaTrabajador(t_md.getPlantillaTrabajador_MD_All_Sort_Fecha_inicial().get(indice));
//        ConjuntoDePlantillasDeTrabajador c=getConjuntoDePlantillasDeTrabajador(t);
//        if(c!=null){
//            return c.getListPlantillaTrabajador().get(indice);
//        }
//        for (int i = 0; i < EA.listaConjuntoDePlantillasDeTrabajador.size(); i++) {
//            ConjuntoDePlantillasDeTrabajador c=EA.listaConjuntoDePlantillasDeTrabajador.get(i);
//            if(c.getTrabajador().getNombre().equals(t.getNombre())){
//                return c.getListPlantillaTrabajador().get(indice);
//            }
//        }
       // return null;
    }
    public  ConjuntoDePlantillasDeTrabajador getConjuntoDePlantillasDeTrabajador(Trabajador t)throws Exception{
        return getConjuntoDePlantillasDeTrabajador(bdAdmin.getTrabajador_MD_For_Nombre(t.getNombre()));
//        for (int i = 0; i < EA.listaConjuntoDePlantillasDeTrabajador.size(); i++) {
//            ConjuntoDePlantillasDeTrabajador c=EA.listaConjuntoDePlantillasDeTrabajador.get(i);
//            if(c.getTrabajador().getNombre().equals(t.getNombre())){
//                return c;
//            }
//        }
//        return null;
    }
    public  void vaciarPlantillasDeTrabajador(Trabajador t)throws Exception{
        Trabajador_MD t_md=bdAdmin.getTrabajador_MD_For_Nombre(t.getNombre());
        List<PlantillaTrabajador_MD> lp=t_md.getPlantillaTrabajador_MD_All_Sort_Fecha_inicial();
        for (int i = 0; i < lp.size(); i++) {
            lp.get(i).d();
        }
//        ConjuntoDePlantillasDeTrabajador c=getConjuntoDePlantillasDeTrabajador(t);
//        if(c!=null){
//             c.getListPlantillaTrabajador().clear();
//        }
    }

    public  Trabajador editarTrabajador(Trabajador t)throws Exception{
        Trabajador_MD t_md=bdAdmin.getTrabajador_MD_id(t.getIdkey());
        t_md.nombre=t.getNombre();
        t_md.s();
        return getTrabajador(t_md);
    }
    public  void eliminarTrabajador(int indice)throws Exception{
        bdAdmin.getDiaDeTrabajo_MD_All().get(indice).d();

    }
    public  void eliminarTrabajador(Trabajador t)throws Exception{
        bdAdmin.getTrabajador_MD_For_Nombre(t.getNombre()).d();
//        for (int i = 0; i < EA.listaConjuntoDePlantillasDeTrabajador.size(); i++) {
//            ConjuntoDePlantillasDeTrabajador c=EA.listaConjuntoDePlantillasDeTrabajador.get(i);
//            if(c.getTrabajador().getNombre().equals(t.getNombre())){
//                EA.listaConjuntoDePlantillasDeTrabajador.remove(i);
//            }
//        }
    }

    public  void eliminarPlantilla(Trabajador t,int indice)throws Exception{//(PlantillaTrabajador p){
        bdAdmin.getTrabajador_MD_For_Nombre(t.getNombre()).getPlantillaTrabajador_MD_All_Sort_Fecha_inicial().get(indice).d();
//        for (int i = 0; i < EA.listaConjuntoDePlantillasDeTrabajador.size(); i++) {
//            ConjuntoDePlantillasDeTrabajador c=EA.listaConjuntoDePlantillasDeTrabajador.get(i);
//            if(c.getTrabajador().getNombre().equals(t.getNombre())){
//                c.getListPlantillaTrabajador().remove(indice);
//            }
//        }
    }

}
