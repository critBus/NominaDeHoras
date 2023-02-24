package com.example.rene.nominadehoras.BD.EnRam;

import com.example.rene.nominadehoras.BD.IBDActual;
import com.example.rene.nominadehoras.EstadoActual.EA;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.ConjuntoDePlantillasDeTrabajador;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.PlantillaTrabajador;
import com.example.rene.nominadehoras.Logica.Trabajador;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by Rene on 4/4/2022.
 */

public class BDEnRam implements IBDActual {
    public  boolean existeTrabajador(String nombre)throws Exception{
        List<ConjuntoDePlantillasDeTrabajador> l= EA.listaConjuntoDePlantillasDeTrabajador;
        for (int i = 0; i <l.size() ; i++) {
            if(l.get(i).getTrabajador().getNombre().toLowerCase().equals(nombre)){
                return true;
            }
        }
        return false;
    }

    public  List<ConjuntoDePlantillasDeTrabajador> getListConjuntoDePlantillasDeTrabajador()throws Exception{
        return EA.listaConjuntoDePlantillasDeTrabajador;
    }

    public Trabajador agregarTrabajador(String nombre)throws Exception{
        Trabajador t=new Trabajador(nombre);
        EA.listaConjuntoDePlantillasDeTrabajador.add(new ConjuntoDePlantillasDeTrabajador(t));
        return t;
    }

    public PlantillaTrabajador elimniarDia(PlantillaTrabajador p, int indiceAEliminar)throws Exception{
        p.getDiasDeTrabajos().remove(indiceAEliminar);
        return p;
    }

    public  PlantillaTrabajador guardarPlantilla(PlantillaTrabajador p)throws Exception{
        return p;
    }

    public  PlantillaTrabajador agregarPlantilla(ConjuntoDePlantillasDeTrabajador c, DateTime d)throws Exception{
        PlantillaTrabajador p=new PlantillaTrabajador(c.getTrabajador(),d);
        c.getListPlantillaTrabajador().add(p);
        return p;
    }
    public  ConjuntoDePlantillasDeTrabajador getConjuntoDePlantillasDeTrabajador(int indice)throws Exception{
        return EA.listaConjuntoDePlantillasDeTrabajador.get(indice);
    }

    public  PlantillaTrabajador getPlantillaTrabajador(Trabajador t,int indice)throws Exception{
        ConjuntoDePlantillasDeTrabajador c=getConjuntoDePlantillasDeTrabajador(t);
        if(c!=null){
            return c.getListPlantillaTrabajador().get(indice);
        }
//        for (int i = 0; i < EA.listaConjuntoDePlantillasDeTrabajador.size(); i++) {
//            ConjuntoDePlantillasDeTrabajador c=EA.listaConjuntoDePlantillasDeTrabajador.get(i);
//            if(c.getTrabajador().getNombre().equals(t.getNombre())){
//                return c.getListPlantillaTrabajador().get(indice);
//            }
//        }
        return null;
    }
    public  ConjuntoDePlantillasDeTrabajador getConjuntoDePlantillasDeTrabajador(Trabajador t)throws Exception{
        for (int i = 0; i < EA.listaConjuntoDePlantillasDeTrabajador.size(); i++) {
            ConjuntoDePlantillasDeTrabajador c=EA.listaConjuntoDePlantillasDeTrabajador.get(i);
            if(c.getTrabajador().getNombre().equals(t.getNombre())){
                return c;
            }
        }
        return null;
    }
    public  void vaciarPlantillasDeTrabajador(Trabajador t)throws Exception{
        ConjuntoDePlantillasDeTrabajador c=getConjuntoDePlantillasDeTrabajador(t);
        if(c!=null){
            c.getListPlantillaTrabajador().clear();
        }
    }

    public  Trabajador editarTrabajador(Trabajador t)throws Exception{
        return t;
    }

    public  void eliminarTrabajador(Trabajador t)throws Exception{
        for (int i = 0; i < EA.listaConjuntoDePlantillasDeTrabajador.size(); i++) {
            ConjuntoDePlantillasDeTrabajador c=EA.listaConjuntoDePlantillasDeTrabajador.get(i);
            if(c.getTrabajador().getNombre().equals(t.getNombre())){
                EA.listaConjuntoDePlantillasDeTrabajador.remove(i);
            }
        }
    }

    public  void eliminarPlantilla(Trabajador t,int indice)throws Exception{//(PlantillaTrabajador p){
        for (int i = 0; i < EA.listaConjuntoDePlantillasDeTrabajador.size(); i++) {
            ConjuntoDePlantillasDeTrabajador c=EA.listaConjuntoDePlantillasDeTrabajador.get(i);
            if(c.getTrabajador().getNombre().equals(t.getNombre())){
                c.getListPlantillaTrabajador().remove(indice);
            }
        }
    }
}
