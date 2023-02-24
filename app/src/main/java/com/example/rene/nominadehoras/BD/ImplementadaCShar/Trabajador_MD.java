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
public class Trabajador_MD extends ModeloDeApiBD<BDAdmin> {
	public static final String TABLA_TRABAJADOR="TABLA_TRABAJADOR";
	public static final String COLUMNA_NOMBRE="COLUMNA_NOMBRE";
	
	public String nombre;
	
	public Trabajador_MD(String nombre,int idkey,BDAdmin apibd){
		super(idkey,apibd);
		this.nombre=nombre;
	}
	public Trabajador_MD(BDAdmin apibd,String nombre){
		this(nombre,-1,apibd);
	}
	public List<PlantillaTrabajador_MD> getPlantillaTrabajador_MD_All_Sort_Fecha_inicial() throws Exception {
		return this.apibd.getPlantillaTrabajador_MD_All_Idkey_trabajador_Sort_Fecha_inicial(this.idkey);
	}
	public PlantillaTrabajador_MD addPlantillaTrabajador_MD(PlantillaTrabajador_MD plantilla_trabajador) throws Exception {
		if (this.idkey==-1){
			this.idkey=this.apibd.insertarTrabajador_MD(this).idkey;
			plantilla_trabajador.idkey_trabajador=this.idkey;
		}
		if (plantilla_trabajador.idkey==-1){
			plantilla_trabajador=this.apibd.insertarPlantillaTrabajador_MD(plantilla_trabajador);
		}
		return plantilla_trabajador;
	}
	public Trabajador_MD s()throws Exception {
		if (this.idkey==-1){
			return this.apibd.insertarTrabajador_MD(this);
		}
		return this.apibd.updateTrabajador_MD(this);
	}
	public void d()throws Exception {
		if (this.idkey!=-1){
			this.apibd.deleteTrabajador_MD_ForId_CASCADE(this.idkey);
		}
	}
	public String getStr(String textoInicial){
		Trabajador_MD s = this;
		return textoInicial+"Trabajador_MD: idkey="+ s.idkey
			+" nombre="+s.nombre
		;
	}
	public String getStr(){ return getStr("");}
}
