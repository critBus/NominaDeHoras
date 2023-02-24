package com.example.rene.nominadehoras.BD;

import com.example.rene.nominadehoras.Logica.LogicaSeparada.ConjuntoDePlantillasDeTrabajador;
import com.example.rene.nominadehoras.Logica.LogicaSeparada.PlantillaTrabajador;
import com.example.rene.nominadehoras.Logica.Trabajador;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by Rene on 4/4/2022.
 */

public interface IBDActual {
    public  boolean existeTrabajador(String nombre) throws Exception;
    public List<ConjuntoDePlantillasDeTrabajador> getListConjuntoDePlantillasDeTrabajador()throws Exception;
    public Trabajador agregarTrabajador(String nombre)throws Exception;
    public PlantillaTrabajador elimniarDia(PlantillaTrabajador p, int indiceAEliminar)throws Exception;
    public  PlantillaTrabajador guardarPlantilla(PlantillaTrabajador p)throws Exception;
    public  PlantillaTrabajador agregarPlantilla(ConjuntoDePlantillasDeTrabajador c, DateTime d)throws Exception;
    public  ConjuntoDePlantillasDeTrabajador getConjuntoDePlantillasDeTrabajador(int indice)throws Exception;
    public  PlantillaTrabajador getPlantillaTrabajador(Trabajador t,int indice)throws Exception;
    public  ConjuntoDePlantillasDeTrabajador getConjuntoDePlantillasDeTrabajador(Trabajador t)throws Exception;
    public  void vaciarPlantillasDeTrabajador(Trabajador t)throws Exception;
    public  Trabajador editarTrabajador(Trabajador t)throws Exception;
    public  void eliminarTrabajador(Trabajador t)throws Exception;
    public  void eliminarPlantilla(Trabajador t,int indice)throws Exception;
}
