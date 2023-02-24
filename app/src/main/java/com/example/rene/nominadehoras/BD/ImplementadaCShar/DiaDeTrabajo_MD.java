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
public class DiaDeTrabajo_MD extends ModeloDeApiBD<BDAdmin> {
	public static final String TABLA_DIA_DE_TRABAJO="TABLA_DIA_DE_TRABAJO";
	public static final String COLUMNA_SESION_MANANA="COLUMNA_SESION_MANANA";
	public static final String COLUMNA_SESION_TARDE="COLUMNA_SESION_TARDE";
	
	public int idkey_sesion_manana;
	public int idkey_sesion_tarde;
	
	public DiaDeTrabajo_MD(int idkey_sesion_manana,int idkey_sesion_tarde,int idkey,BDAdmin apibd){
		super(idkey,apibd);
		this.idkey_sesion_manana=idkey_sesion_manana;
		this.idkey_sesion_tarde=idkey_sesion_tarde;
	}
	public DiaDeTrabajo_MD(BDAdmin apibd,int idkey_sesion_manana,int idkey_sesion_tarde){
		this(idkey_sesion_manana,idkey_sesion_tarde,-1,apibd);
	}
	public DiaDeTrabajo_MD(BDAdmin apibd,Sesion_MD sesion_manana,Sesion_MD sesion_tarde){
		this(sesion_manana.idkey,sesion_tarde.idkey,-1,apibd);
	}
	public Sesion_MD getSesion_manana() throws Exception {
		return this.apibd.getSesion_MD_id(this.idkey_sesion_manana);
	}
	public Sesion_MD getSesion_tarde() throws Exception {
		return this.apibd.getSesion_MD_id(this.idkey_sesion_tarde);
	}
	public DiaDeTrabajo_MD s()throws Exception {
		if (this.idkey==-1){
			return this.apibd.insertarDiaDeTrabajo_MD(this);
		}
		return this.apibd.updateDiaDeTrabajo_MD(this);
	}
	public void d()throws Exception {
		if (this.idkey!=-1){
			this.apibd.deleteDiaDeTrabajo_MD_ForId_CASCADE(this.idkey);
		}
	}
	public String getStr(String textoInicial){
		DiaDeTrabajo_MD s = this;
		return textoInicial+"DiaDeTrabajo_MD: idkey="+ s.idkey
			+" idkey_sesion_manana="+s.idkey_sesion_manana
			+" idkey_sesion_tarde="+s.idkey_sesion_tarde
		;
	}
	public String getStr(){ return getStr("");}
}
