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
public class PlantillaTrabajador_MD extends ModeloDeApiBD<BDAdmin> {
	public static final String TABLA_PLANTILLA_TRABAJADOR="TABLA_PLANTILLA_TRABAJADOR";
	public static final String COLUMNA_ID_TABLA_TRABAJADOR="COLUMNA_ID_TABLA_TRABAJADOR";
	public static final String COLUMNA_FECHA_INICIAL="COLUMNA_FECHA_INICIAL";
	
	public int idkey_trabajador;
	public DateTime fecha_inicial;
	
	public PlantillaTrabajador_MD(int idkey_trabajador,DateTime fecha_inicial,int idkey,BDAdmin apibd){
		super(idkey,apibd);
		this.idkey_trabajador=idkey_trabajador;
		this.fecha_inicial=fecha_inicial;
	}
	public PlantillaTrabajador_MD(BDAdmin apibd,int idkey_trabajador,DateTime fecha_inicial){
		this(idkey_trabajador,fecha_inicial,-1,apibd);
	}
	public PlantillaTrabajador_MD(BDAdmin apibd,Trabajador_MD trabajador,DateTime fecha_inicial){
		this(trabajador.idkey,fecha_inicial,-1,apibd);
	}
	public Trabajador_MD getTrabajador() throws Exception {
		return this.apibd.getTrabajador_MD_id(this.idkey_trabajador);
	}
	public List<DiaDeTrabajo_MD> getDiaDeTrabajo_MD_All() throws Exception {
		return this.apibd.getDiaDeTrabajo_MD_All_Idkey_plantilla_trabajador(this.idkey);
	}
	public DiaDeTrabajo_MD addDiaDeTrabajo_MD(DiaDeTrabajo_MD dia_de_trabajo) throws Exception {
		if (this.idkey==-1){
			this.idkey=this.apibd.insertarPlantillaTrabajador_MD(this).idkey;
		}
		if (dia_de_trabajo.idkey==-1){
			dia_de_trabajo=this.apibd.insertarDiaDeTrabajo_MD(dia_de_trabajo);
		}
		if (!this.apibd.existePlantillaTrabajador_MD_And_DiaDeTrabajo_MD(this.idkey,dia_de_trabajo.idkey)){
			OnePlantillaTrabajadorManyDiaDeTrabajo_MD one_plantilla_trabajador_many_dia_de_trabajo=new OnePlantillaTrabajadorManyDiaDeTrabajo_MD(this.apibd,this,dia_de_trabajo);
			this.apibd.insertarOnePlantillaTrabajadorManyDiaDeTrabajo_MD(one_plantilla_trabajador_many_dia_de_trabajo);
			return dia_de_trabajo;
		}
		return dia_de_trabajo;
	}
	public PlantillaTrabajador_MD s()throws Exception {
		if (this.idkey==-1){
			return this.apibd.insertarPlantillaTrabajador_MD(this);
		}
		return this.apibd.updatePlantillaTrabajador_MD(this);
	}
	public void d()throws Exception {
		if (this.idkey!=-1){
			this.apibd.deletePlantillaTrabajador_MD_ForId_CASCADE(this.idkey);
		}
	}
	public String getStr(String textoInicial){
		PlantillaTrabajador_MD s = this;
		return textoInicial+"PlantillaTrabajador_MD: idkey="+ s.idkey
			+" idkey_trabajador="+s.idkey_trabajador
			+" fecha_inicial="+s.fecha_inicial
		;
	}
	public String getStr(){ return getStr("");}
}
