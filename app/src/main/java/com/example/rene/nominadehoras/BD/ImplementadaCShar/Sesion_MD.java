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
public class Sesion_MD extends ModeloDeApiBD<BDAdmin> {
	public static final String TABLA_SESION="TABLA_SESION";
	public static final String COLUMNA_ENTRADA="COLUMNA_ENTRADA";
	public static final String COLUMNA_SALIDA="COLUMNA_SALIDA";
	
	public Time entrada;
	public Time salida;
	
	public Sesion_MD(Time entrada,Time salida,int idkey,BDAdmin apibd){
		super(idkey,apibd);
		this.entrada=entrada;
		this.salida=salida;
	}
	public Sesion_MD(BDAdmin apibd,Time entrada,Time salida){
		this(entrada,salida,-1,apibd);
	}
	public Sesion_MD s()throws Exception {
		if (this.idkey==-1){
			return this.apibd.insertarSesion_MD(this);
		}
		return this.apibd.updateSesion_MD(this);
	}
	public void d()throws Exception {
		if (this.idkey!=-1){
			this.apibd.deleteSesion_MD_ForId_CASCADE(this.idkey);
		}
	}
	public String getStr(String textoInicial){
		Sesion_MD s = this;
		return textoInicial+"Sesion_MD: idkey="+ s.idkey
			+" entrada="+s.entrada
			+" salida="+s.salida
		;
	}
	public String getStr(){ return getStr("");}
}
