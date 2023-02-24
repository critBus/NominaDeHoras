package com.example.rene.nominadehoras.BD.ImplementadaCShar;

import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.BDConexion;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.BDSesionStorage;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.BDUpdates;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.TipoDeDatoSQL;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.TipoDeClasificacionSQL;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.TipoDeOrdenamientoSQL;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.BasicoBD;
import com.rene.android.reneandroid.Utiles.ClasesUtiles.BasesDeDatos.ModeloDeApiBD;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.sql.Time;
import com.rene.android.reneandroid.BDAndroid;
import android.content.Context;
import org.joda.time.DateTime;
import com.rene.android.reneandroid.Clases.BD.BasicoBDAndroid;
import com.rene.android.reneandroid.ArchivoAndroid;
public class OnePlantillaTrabajadorManyDiaDeTrabajo_MD extends ModeloDeApiBD<BDAdmin> {
	public static final String TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO="TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO";
	public static final String COLUMNA_ID_TABLA_PLANTILLA_TRABAJADOR="COLUMNA_ID_TABLA_PLANTILLA_TRABAJADOR";
	public static final String COLUMNA_ID_TABLA_DIA_DE_TRABAJO="COLUMNA_ID_TABLA_DIA_DE_TRABAJO";
	
	public int idkey_plantilla_trabajador;
	public int idkey_dia_de_trabajo;
	
	public OnePlantillaTrabajadorManyDiaDeTrabajo_MD(int idkey_plantilla_trabajador,int idkey_dia_de_trabajo,int idkey,BDAdmin apibd){
		super(idkey,apibd);
		this.idkey_plantilla_trabajador=idkey_plantilla_trabajador;
		this.idkey_dia_de_trabajo=idkey_dia_de_trabajo;
	}
	public OnePlantillaTrabajadorManyDiaDeTrabajo_MD(BDAdmin apibd,int idkey_plantilla_trabajador,int idkey_dia_de_trabajo){
		this(idkey_plantilla_trabajador,idkey_dia_de_trabajo,-1,apibd);
	}
	public OnePlantillaTrabajadorManyDiaDeTrabajo_MD(BDAdmin apibd,PlantillaTrabajador_MD plantilla_trabajador,DiaDeTrabajo_MD dia_de_trabajo){
		this(plantilla_trabajador.idkey,dia_de_trabajo.idkey,-1,apibd);
	}
	public PlantillaTrabajador_MD getPlantilla_trabajador() throws Exception {
		return this.apibd.getPlantillaTrabajador_MD_id(this.idkey_plantilla_trabajador);
	}
	public DiaDeTrabajo_MD getDia_de_trabajo() throws Exception {
		return this.apibd.getDiaDeTrabajo_MD_id(this.idkey_dia_de_trabajo);
	}
	public OnePlantillaTrabajadorManyDiaDeTrabajo_MD s()throws Exception {
		if (this.idkey==-1){
			return this.apibd.insertarOnePlantillaTrabajadorManyDiaDeTrabajo_MD(this);
		}
		return this.apibd.updateOnePlantillaTrabajadorManyDiaDeTrabajo_MD(this);
	}
	public void d()throws Exception {
		if (this.idkey!=-1){
			this.apibd.deleteOnePlantillaTrabajadorManyDiaDeTrabajo_MD_ForId(this.idkey);
		}
	}
	public String getStr(String textoInicial){
		OnePlantillaTrabajadorManyDiaDeTrabajo_MD s = this;
		return textoInicial+"OnePlantillaTrabajadorManyDiaDeTrabajo_MD: idkey="+ s.idkey
			+" idkey_plantilla_trabajador="+s.idkey_plantilla_trabajador
			+" idkey_dia_de_trabajo="+s.idkey_dia_de_trabajo
		;
	}
	public String getStr(){ return getStr("");}
}
