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

public class BDAdmin extends BasicoBDAndroid{
	private String urlBD;
	private BDConexion BD;
	private BDUpdates __Upd;
	private boolean usarUpdater;
	public BDAdmin(Context context) throws Exception {
		this.urlBD="LeyenApps/NominaDeHoras/bdNominaDeHoras.sqlite";
		
		File f= ArchivoAndroid.getFileInterno(this.urlBD);
		if(!f.exists()){
			BDAndroid.crearArchivoSQLite(context,f);
		}
		this.BD =BDAndroid.getDB_SQLite(context,f);
		this.__Upd =new BDUpdates(this.BD);
		this.usarUpdater=false;
	}
	public String getUrlBD(){
		return  this.urlBD;
	}
	public BDAdmin crearTablaTrabajador_MD() throws Exception {
		 this.BD.crearTablaYBorrarSiExiste(Trabajador_MD.TABLA_TRABAJADOR
						,Trabajador_MD.COLUMNA_NOMBRE,TipoDeClasificacionSQL.UNIQUE
						);
		return this;
	}
	public Trabajador_MD getTrabajador_MD_Args(Object[] listaDeArgumentos) throws Exception {
		return new Trabajador_MD(to_String(listaDeArgumentos[1])
				,toInt(listaDeArgumentos[0])
				,this
				);
		}
	public Object[] __content_Trabajador_MD(Trabajador_MD trabajador) throws Exception {
		Object[] lista = {new Object[]{Trabajador_MD.COLUMNA_NOMBRE,trabajador.nombre}
			};
		return lista;
		}
	public Trabajador_MD getTrabajador_MD_id(int id) throws Exception {
		Object[] O = this.BD.select_forID(Trabajador_MD.TABLA_TRABAJADOR, id);
		if (O == null){
			return null;}
		return this.getTrabajador_MD_Args(O);
		}
	public Trabajador_MD insertarTrabajador_MD(Trabajador_MD trabajador) throws Exception {
		if (trabajador.idkey==-1){
			int id=this.BD.insertar(Trabajador_MD.TABLA_TRABAJADOR
					,trabajador.nombre
					).id;
			return this.getTrabajador_MD_id(id);
		}else{
			this.BD.insertar_SinIdAutomatico(Trabajador_MD.TABLA_TRABAJADOR,trabajador.idkey
					,trabajador.nombre
					);
			return this.getTrabajador_MD_id(trabajador.idkey);}
		}
	public List<Trabajador_MD> getTrabajador_MD_All() throws Exception {
			List<Trabajador_MD> lista=new ArrayList<>();
			Object [][]O=this.BD.select_Todo(Trabajador_MD.TABLA_TRABAJADOR);
			if (O!=null){
				for(int i=0;i<O.length;i++){
					lista.add(getTrabajador_MD_Args(O[i]));
				}
			}
			return lista;
	}
	public Trabajador_MD updateTrabajador_MD(Trabajador_MD trabajador) throws Exception {
			this.BD.update_Id(Trabajador_MD.TABLA_TRABAJADOR,trabajador.idkey
						 , Trabajador_MD.COLUMNA_NOMBRE , trabajador.nombre);
			return getTrabajador_MD_id(trabajador.idkey);
	}
	public void deleteTrabajador_MD_ForId(int id) throws Exception {
			this.BD.delete_id(Trabajador_MD.TABLA_TRABAJADOR,id);
	}
	public void deleteTrabajador_MD_ForId(Trabajador_MD trabajador) throws Exception {
			deleteTrabajador_MD_ForId(trabajador.idkey);
	}
	public void deleteTrabajador_MD_ForId_CASCADE(int idkey_trabajador) throws Exception {
		deleteTrabajador_MD_ForId_CASCADE(idkey_trabajador,null);
	}
	public void deleteTrabajador_MD_ForId_CASCADE(int idkey_trabajador,Object modeloQueLoLlamo) throws Exception {
		Trabajador_MD trabajador=getTrabajador_MD_id(idkey_trabajador);
		if(modeloQueLoLlamo!=null&& modeloQueLoLlamo instanceof PlantillaTrabajador_MD){
			deletePlantillaTrabajador_MD_For_Idkey_trabajador(idkey_trabajador);
		}else{
			deletePlantillaTrabajador_MD_For_Idkey_trabajador_CASCADE(idkey_trabajador,trabajador);
		}
		deleteTrabajador_MD_ForId(idkey_trabajador);
	}
	public BDAdmin crearTablaSesion_MD() throws Exception {
		 this.BD.crearTablaYBorrarSiExiste(Sesion_MD.TABLA_SESION
						,Sesion_MD.COLUMNA_ENTRADA,TipoDeDatoSQL.TIME
						,Sesion_MD.COLUMNA_SALIDA,TipoDeDatoSQL.TIME
						);
		return this;
	}
	public Sesion_MD getSesion_MD_Args(Object[] listaDeArgumentos) throws Exception {
		return new Sesion_MD(toTime(listaDeArgumentos[1])
				,toTime(listaDeArgumentos[2])
				,toInt(listaDeArgumentos[0])
				,this
				);
		}
	public Object[] __content_Sesion_MD(Sesion_MD sesion) throws Exception {
		Object[] lista = {new Object[]{Sesion_MD.COLUMNA_ENTRADA,sesion.entrada}
			,new Object[]{Sesion_MD.COLUMNA_SALIDA,sesion.salida}
			};
		return lista;
		}
	public Sesion_MD getSesion_MD_id(int id) throws Exception {
		Object[] O = this.BD.select_forID(Sesion_MD.TABLA_SESION, id);
		if (O == null){
			return null;}
		return this.getSesion_MD_Args(O);
		}
	public Sesion_MD insertarSesion_MD(Sesion_MD sesion) throws Exception {
		if (sesion.idkey==-1){
			int id=this.BD.insertar(Sesion_MD.TABLA_SESION
					,sesion.entrada
					,sesion.salida
					).id;
			return this.getSesion_MD_id(id);
		}else{
			this.BD.insertar_SinIdAutomatico(Sesion_MD.TABLA_SESION,sesion.idkey
					,sesion.entrada
					,sesion.salida
					);
			return this.getSesion_MD_id(sesion.idkey);}
		}
	public List<Sesion_MD> getSesion_MD_All() throws Exception {
			List<Sesion_MD> lista=new ArrayList<>();
			Object [][]O=this.BD.select_Todo(Sesion_MD.TABLA_SESION);
			if (O!=null){
				for(int i=0;i<O.length;i++){
					lista.add(getSesion_MD_Args(O[i]));
				}
			}
			return lista;
	}
	public Sesion_MD updateSesion_MD(Sesion_MD sesion) throws Exception {
			this.BD.update_Id(Sesion_MD.TABLA_SESION,sesion.idkey
						 , Sesion_MD.COLUMNA_ENTRADA , sesion.entrada
						 , Sesion_MD.COLUMNA_SALIDA , sesion.salida);
			return getSesion_MD_id(sesion.idkey);
	}
	public void deleteSesion_MD_ForId(int id) throws Exception {
			this.BD.delete_id(Sesion_MD.TABLA_SESION,id);
	}
	public void deleteSesion_MD_ForId(Sesion_MD sesion) throws Exception {
			deleteSesion_MD_ForId(sesion.idkey);
	}
	public void deleteSesion_MD_ForId_CASCADE(int idkey_sesion) throws Exception {
		deleteSesion_MD_ForId_CASCADE(idkey_sesion,null);
	}
	public void deleteSesion_MD_ForId_CASCADE(int idkey_sesion,Object modeloQueLoLlamo) throws Exception {
		Sesion_MD sesion=getSesion_MD_id(idkey_sesion);
		if(modeloQueLoLlamo!=null&& modeloQueLoLlamo instanceof DiaDeTrabajo_MD){
			deleteDiaDeTrabajo_MD_For_Idkey_sesion_manana(idkey_sesion);
		}else{
			deleteDiaDeTrabajo_MD_For_Idkey_sesion_manana_CASCADE(idkey_sesion,sesion);
		}
		deleteSesion_MD_ForId(idkey_sesion);
	}
	public BDAdmin crearTablaDiaDeTrabajo_MD() throws Exception {
		 this.BD.crearTablaYBorrarSiExiste(DiaDeTrabajo_MD.TABLA_DIA_DE_TRABAJO
						,DiaDeTrabajo_MD.COLUMNA_SESION_MANANA,TipoDeDatoSQL.INTEGER
						,DiaDeTrabajo_MD.COLUMNA_SESION_TARDE,TipoDeDatoSQL.INTEGER
						);
		return this;
	}
	public DiaDeTrabajo_MD getDiaDeTrabajo_MD_Args(Object[] listaDeArgumentos) throws Exception {
		return new DiaDeTrabajo_MD(toInt(listaDeArgumentos[1])
				,toInt(listaDeArgumentos[2])
				,toInt(listaDeArgumentos[0])
				,this
				);
		}
	public Object[] __content_DiaDeTrabajo_MD(DiaDeTrabajo_MD dia_de_trabajo) throws Exception {
		Object[] lista = {new Object[]{DiaDeTrabajo_MD.COLUMNA_SESION_MANANA,dia_de_trabajo.idkey_sesion_manana}
			,new Object[]{DiaDeTrabajo_MD.COLUMNA_SESION_TARDE,dia_de_trabajo.idkey_sesion_tarde}
			};
		return lista;
		}
	public DiaDeTrabajo_MD getDiaDeTrabajo_MD_id(int id) throws Exception {
		Object[] O = this.BD.select_forID(DiaDeTrabajo_MD.TABLA_DIA_DE_TRABAJO, id);
		if (O == null){
			return null;}
		return this.getDiaDeTrabajo_MD_Args(O);
		}
	public DiaDeTrabajo_MD insertarDiaDeTrabajo_MD(DiaDeTrabajo_MD dia_de_trabajo) throws Exception {
		if (dia_de_trabajo.idkey==-1){
			int id=this.BD.insertar(DiaDeTrabajo_MD.TABLA_DIA_DE_TRABAJO
					,dia_de_trabajo.idkey_sesion_manana
					,dia_de_trabajo.idkey_sesion_tarde
					).id;
			return this.getDiaDeTrabajo_MD_id(id);
		}else{
			this.BD.insertar_SinIdAutomatico(DiaDeTrabajo_MD.TABLA_DIA_DE_TRABAJO,dia_de_trabajo.idkey
					,dia_de_trabajo.idkey_sesion_manana
					,dia_de_trabajo.idkey_sesion_tarde
					);
			return this.getDiaDeTrabajo_MD_id(dia_de_trabajo.idkey);}
		}
	public List<DiaDeTrabajo_MD> getDiaDeTrabajo_MD_All() throws Exception {
			List<DiaDeTrabajo_MD> lista=new ArrayList<>();
			Object [][]O=this.BD.select_Todo(DiaDeTrabajo_MD.TABLA_DIA_DE_TRABAJO);
			if (O!=null){
				for(int i=0;i<O.length;i++){
					lista.add(getDiaDeTrabajo_MD_Args(O[i]));
				}
			}
			return lista;
	}
	public DiaDeTrabajo_MD updateDiaDeTrabajo_MD(DiaDeTrabajo_MD dia_de_trabajo) throws Exception {
			this.BD.update_Id(DiaDeTrabajo_MD.TABLA_DIA_DE_TRABAJO,dia_de_trabajo.idkey
						 , DiaDeTrabajo_MD.COLUMNA_SESION_MANANA , dia_de_trabajo.idkey_sesion_manana
						 , DiaDeTrabajo_MD.COLUMNA_SESION_TARDE , dia_de_trabajo.idkey_sesion_tarde);
			return getDiaDeTrabajo_MD_id(dia_de_trabajo.idkey);
	}
	public void deleteDiaDeTrabajo_MD_ForId(int id) throws Exception {
			this.BD.delete_id(DiaDeTrabajo_MD.TABLA_DIA_DE_TRABAJO,id);
	}
	public void deleteDiaDeTrabajo_MD_ForId(DiaDeTrabajo_MD dia_de_trabajo) throws Exception {
			deleteDiaDeTrabajo_MD_ForId(dia_de_trabajo.idkey);
	}
	public void deleteDiaDeTrabajo_MD_ForId_CASCADE(int idkey_dia_de_trabajo) throws Exception {
		deleteDiaDeTrabajo_MD_ForId_CASCADE(idkey_dia_de_trabajo,null);
	}
	public void deleteDiaDeTrabajo_MD_ForId_CASCADE(int idkey_dia_de_trabajo,Object modeloQueLoLlamo) throws Exception {
		DiaDeTrabajo_MD dia_de_trabajo=getDiaDeTrabajo_MD_id(idkey_dia_de_trabajo);
		deleteOnePlantillaTrabajadorManyDiaDeTrabajo_MD_For_Idkey_dia_de_trabajo(idkey_dia_de_trabajo);
		if(modeloQueLoLlamo!=null&& modeloQueLoLlamo instanceof Sesion_MD){
			deleteSesion_MD_ForId(dia_de_trabajo.idkey_sesion_manana);
		}else{
			deleteSesion_MD_ForId_CASCADE(dia_de_trabajo.idkey_sesion_manana,dia_de_trabajo);
		}
		if(modeloQueLoLlamo!=null&& modeloQueLoLlamo instanceof Sesion_MD){
			deleteSesion_MD_ForId(dia_de_trabajo.idkey_sesion_tarde);
		}else{
			deleteSesion_MD_ForId_CASCADE(dia_de_trabajo.idkey_sesion_tarde,dia_de_trabajo);
		}
		deleteDiaDeTrabajo_MD_ForId(idkey_dia_de_trabajo);
	}
	public BDAdmin crearTablaPlantillaTrabajador_MD() throws Exception {
		 this.BD.crearTablaYBorrarSiExiste(PlantillaTrabajador_MD.TABLA_PLANTILLA_TRABAJADOR
						,PlantillaTrabajador_MD.COLUMNA_ID_TABLA_TRABAJADOR,TipoDeDatoSQL.INTEGER
						,PlantillaTrabajador_MD.COLUMNA_FECHA_INICIAL,TipoDeDatoSQL.DATE
						);
		return this;
	}
	public PlantillaTrabajador_MD getPlantillaTrabajador_MD_Args(Object[] listaDeArgumentos) throws Exception {
		return new PlantillaTrabajador_MD(toInt(listaDeArgumentos[1])
				,toJodaDateTime(listaDeArgumentos[2])
				,toInt(listaDeArgumentos[0])
				,this
				);
		}
	public Object[] __content_PlantillaTrabajador_MD(PlantillaTrabajador_MD plantilla_trabajador) throws Exception {
		Object[] lista = {new Object[]{PlantillaTrabajador_MD.COLUMNA_ID_TABLA_TRABAJADOR,plantilla_trabajador.idkey_trabajador}
			,new Object[]{PlantillaTrabajador_MD.COLUMNA_FECHA_INICIAL,plantilla_trabajador.fecha_inicial}
			};
		return lista;
		}
	public PlantillaTrabajador_MD getPlantillaTrabajador_MD_id(int id) throws Exception {
		Object[] O = this.BD.select_forID(PlantillaTrabajador_MD.TABLA_PLANTILLA_TRABAJADOR, id);
		if (O == null){
			return null;}
		return this.getPlantillaTrabajador_MD_Args(O);
		}
	public PlantillaTrabajador_MD insertarPlantillaTrabajador_MD(PlantillaTrabajador_MD plantilla_trabajador) throws Exception {
		if (plantilla_trabajador.idkey==-1){
			int id=this.BD.insertar(PlantillaTrabajador_MD.TABLA_PLANTILLA_TRABAJADOR
					,plantilla_trabajador.idkey_trabajador
					,plantilla_trabajador.fecha_inicial
					).id;
			return this.getPlantillaTrabajador_MD_id(id);
		}else{
			this.BD.insertar_SinIdAutomatico(PlantillaTrabajador_MD.TABLA_PLANTILLA_TRABAJADOR,plantilla_trabajador.idkey
					,plantilla_trabajador.idkey_trabajador
					,plantilla_trabajador.fecha_inicial
					);
			return this.getPlantillaTrabajador_MD_id(plantilla_trabajador.idkey);}
		}
	public List<PlantillaTrabajador_MD> getPlantillaTrabajador_MD_All() throws Exception {
			List<PlantillaTrabajador_MD> lista=new ArrayList<>();
			Object [][]O=this.BD.select_Todo(PlantillaTrabajador_MD.TABLA_PLANTILLA_TRABAJADOR);
			if (O!=null){
				for(int i=0;i<O.length;i++){
					lista.add(getPlantillaTrabajador_MD_Args(O[i]));
				}
			}
			return lista;
	}
	public PlantillaTrabajador_MD updatePlantillaTrabajador_MD(PlantillaTrabajador_MD plantilla_trabajador) throws Exception {
			this.BD.update_Id(PlantillaTrabajador_MD.TABLA_PLANTILLA_TRABAJADOR,plantilla_trabajador.idkey
						 , PlantillaTrabajador_MD.COLUMNA_ID_TABLA_TRABAJADOR , plantilla_trabajador.idkey_trabajador
						 , PlantillaTrabajador_MD.COLUMNA_FECHA_INICIAL , plantilla_trabajador.fecha_inicial);
			return getPlantillaTrabajador_MD_id(plantilla_trabajador.idkey);
	}
	public void deletePlantillaTrabajador_MD_ForId(int id) throws Exception {
			this.BD.delete_id(PlantillaTrabajador_MD.TABLA_PLANTILLA_TRABAJADOR,id);
	}
	public void deletePlantillaTrabajador_MD_ForId(PlantillaTrabajador_MD plantilla_trabajador) throws Exception {
			deletePlantillaTrabajador_MD_ForId(plantilla_trabajador.idkey);
	}
	public void deletePlantillaTrabajador_MD_ForId_CASCADE(int idkey_plantilla_trabajador) throws Exception {
		deletePlantillaTrabajador_MD_ForId_CASCADE(idkey_plantilla_trabajador,null);
	}
	public void deletePlantillaTrabajador_MD_ForId_CASCADE(int idkey_plantilla_trabajador,Object modeloQueLoLlamo) throws Exception {
		PlantillaTrabajador_MD plantilla_trabajador=getPlantillaTrabajador_MD_id(idkey_plantilla_trabajador);
		deleteOnePlantillaTrabajadorManyDiaDeTrabajo_MD_For_Idkey_plantilla_trabajador(idkey_plantilla_trabajador);
		deletePlantillaTrabajador_MD_ForId(idkey_plantilla_trabajador);
	}
	public BDAdmin crearTablaOnePlantillaTrabajadorManyDiaDeTrabajo_MD() throws Exception {
		 this.BD.crearTablaYBorrarSiExiste(OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO
						,OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_PLANTILLA_TRABAJADOR,TipoDeDatoSQL.INTEGER
						,OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_DIA_DE_TRABAJO,TipoDeDatoSQL.INTEGER
						);
		return this;
	}
	public OnePlantillaTrabajadorManyDiaDeTrabajo_MD getOnePlantillaTrabajadorManyDiaDeTrabajo_MD_Args(Object[] listaDeArgumentos) throws Exception {
		return new OnePlantillaTrabajadorManyDiaDeTrabajo_MD(toInt(listaDeArgumentos[1])
				,toInt(listaDeArgumentos[2])
				,toInt(listaDeArgumentos[0])
				,this
				);
		}
	public Object[] __content_OnePlantillaTrabajadorManyDiaDeTrabajo_MD(OnePlantillaTrabajadorManyDiaDeTrabajo_MD one_plantilla_trabajador_many_dia_de_trabajo) throws Exception {
		Object[] lista = {new Object[]{OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_PLANTILLA_TRABAJADOR,one_plantilla_trabajador_many_dia_de_trabajo.idkey_plantilla_trabajador}
			,new Object[]{OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_DIA_DE_TRABAJO,one_plantilla_trabajador_many_dia_de_trabajo.idkey_dia_de_trabajo}
			};
		return lista;
		}
	public OnePlantillaTrabajadorManyDiaDeTrabajo_MD getOnePlantillaTrabajadorManyDiaDeTrabajo_MD_id(int id) throws Exception {
		Object[] O = this.BD.select_forID(OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO, id);
		if (O == null){
			return null;}
		return this.getOnePlantillaTrabajadorManyDiaDeTrabajo_MD_Args(O);
		}
	public OnePlantillaTrabajadorManyDiaDeTrabajo_MD insertarOnePlantillaTrabajadorManyDiaDeTrabajo_MD(OnePlantillaTrabajadorManyDiaDeTrabajo_MD one_plantilla_trabajador_many_dia_de_trabajo) throws Exception {
		if (one_plantilla_trabajador_many_dia_de_trabajo.idkey==-1){
			int id=this.BD.insertar(OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO
					,one_plantilla_trabajador_many_dia_de_trabajo.idkey_plantilla_trabajador
					,one_plantilla_trabajador_many_dia_de_trabajo.idkey_dia_de_trabajo
					).id;
			return this.getOnePlantillaTrabajadorManyDiaDeTrabajo_MD_id(id);
		}else{
			this.BD.insertar_SinIdAutomatico(OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO,one_plantilla_trabajador_many_dia_de_trabajo.idkey
					,one_plantilla_trabajador_many_dia_de_trabajo.idkey_plantilla_trabajador
					,one_plantilla_trabajador_many_dia_de_trabajo.idkey_dia_de_trabajo
					);
			return this.getOnePlantillaTrabajadorManyDiaDeTrabajo_MD_id(one_plantilla_trabajador_many_dia_de_trabajo.idkey);}
		}
	public List<OnePlantillaTrabajadorManyDiaDeTrabajo_MD> getOnePlantillaTrabajadorManyDiaDeTrabajo_MD_All() throws Exception {
			List<OnePlantillaTrabajadorManyDiaDeTrabajo_MD> lista=new ArrayList<>();
			Object [][]O=this.BD.select_Todo(OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO);
			if (O!=null){
				for(int i=0;i<O.length;i++){
					lista.add(getOnePlantillaTrabajadorManyDiaDeTrabajo_MD_Args(O[i]));
				}
			}
			return lista;
	}
	public OnePlantillaTrabajadorManyDiaDeTrabajo_MD updateOnePlantillaTrabajadorManyDiaDeTrabajo_MD(OnePlantillaTrabajadorManyDiaDeTrabajo_MD one_plantilla_trabajador_many_dia_de_trabajo) throws Exception {
			this.BD.update_Id(OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO,one_plantilla_trabajador_many_dia_de_trabajo.idkey
						 , OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_PLANTILLA_TRABAJADOR , one_plantilla_trabajador_many_dia_de_trabajo.idkey_plantilla_trabajador
						 , OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_DIA_DE_TRABAJO , one_plantilla_trabajador_many_dia_de_trabajo.idkey_dia_de_trabajo);
			return getOnePlantillaTrabajadorManyDiaDeTrabajo_MD_id(one_plantilla_trabajador_many_dia_de_trabajo.idkey);
	}
	public void deleteOnePlantillaTrabajadorManyDiaDeTrabajo_MD_ForId(int id) throws Exception {
			this.BD.delete_id(OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO,id);
	}
	public void deleteOnePlantillaTrabajadorManyDiaDeTrabajo_MD_ForId(OnePlantillaTrabajadorManyDiaDeTrabajo_MD one_plantilla_trabajador_many_dia_de_trabajo) throws Exception {
			deleteOnePlantillaTrabajadorManyDiaDeTrabajo_MD_ForId(one_plantilla_trabajador_many_dia_de_trabajo.idkey);
	}
	public void crearTodasLasTablas() throws Exception {
		crearTablaTrabajador_MD();
		crearTablaSesion_MD();
		crearTablaDiaDeTrabajo_MD();
		crearTablaPlantillaTrabajador_MD();
		crearTablaOnePlantillaTrabajadorManyDiaDeTrabajo_MD();
	}
	public Trabajador_MD getTrabajador_MD_For_Nombre(String nombre) throws Exception {
			Object []O=this.BD.select_Where_FirstRow(Trabajador_MD.TABLA_TRABAJADOR,Trabajador_MD.COLUMNA_NOMBRE,nombre);
			if (O!=null){
				return getTrabajador_MD_Args(O);
			}
			return null;
	}
	public void deleteTrabajador_MD_For_Nombre(String nombre) throws Exception {
			this.BD.delete(Trabajador_MD.TABLA_TRABAJADOR,Trabajador_MD.COLUMNA_NOMBRE,nombre);
	}
	public void deleteTrabajador_MD_For_Nombre_CASCADE(String nombre) throws Exception {
		deleteTrabajador_MD_For_Nombre_CASCADE(nombre,null);
	}
	public void deleteTrabajador_MD_For_Nombre_CASCADE(String nombre,Object modeloQueLoLlamo) throws Exception {
		Trabajador_MD trabajador=getTrabajador_MD_For_Nombre(nombre);
		if(modeloQueLoLlamo!=null&& modeloQueLoLlamo instanceof PlantillaTrabajador_MD){
			deletePlantillaTrabajador_MD_For_Idkey_trabajador(trabajador.idkey);
		}else{
			deletePlantillaTrabajador_MD_For_Idkey_trabajador_CASCADE(trabajador.idkey,trabajador);
		}
		deleteTrabajador_MD_For_Nombre(nombre);
	}
	public boolean existeTrabajador_MD(String nombre) throws Exception {
			return this.BD.existe(Trabajador_MD.TABLA_TRABAJADOR,Trabajador_MD.COLUMNA_NOMBRE,nombre);
	}
	public List<PlantillaTrabajador_MD> getPlantillaTrabajador_MD_All_Idkey_trabajador_Sort_Fecha_inicial(int idkey_trabajador) throws Exception {
			List<PlantillaTrabajador_MD> lista=new ArrayList<>();
			Object [][]O=this.BD.select_Where_ORDER_BY(PlantillaTrabajador_MD.TABLA_PLANTILLA_TRABAJADOR,
				new Object []{
					PlantillaTrabajador_MD.COLUMNA_ID_TABLA_TRABAJADOR,idkey_trabajador
				}
					,PlantillaTrabajador_MD.COLUMNA_FECHA_INICIAL);
			if (O!=null){
				for(int i=0;i<O.length;i++){
					lista.add(getPlantillaTrabajador_MD_Args(O[i]));
				}
			}
			return lista;
	}
	public List<DiaDeTrabajo_MD> getDiaDeTrabajo_MD_All_Idkey_sesion_manana(int idkey_sesion_manana) throws Exception {
			List<DiaDeTrabajo_MD> lista=new ArrayList<>();
			Object [][]O=this.BD.select_Where(DiaDeTrabajo_MD.TABLA_DIA_DE_TRABAJO,DiaDeTrabajo_MD.COLUMNA_SESION_MANANA,idkey_sesion_manana);
			if (O!=null){
				for(int i=0;i<O.length;i++){
					lista.add(getDiaDeTrabajo_MD_Args(O[i]));
				}
			}
			return lista;
	}
	public void deleteDiaDeTrabajo_MD_For_Idkey_sesion_manana(int idkey_sesion_manana) throws Exception {
			this.BD.delete(DiaDeTrabajo_MD.TABLA_DIA_DE_TRABAJO,DiaDeTrabajo_MD.COLUMNA_SESION_MANANA,idkey_sesion_manana);
	}
	public void deleteDiaDeTrabajo_MD_For_Idkey_sesion_manana_CASCADE(int idkey_sesion_manana) throws Exception {
		deleteDiaDeTrabajo_MD_For_Idkey_sesion_manana_CASCADE(idkey_sesion_manana,null);
	}
	public void deleteDiaDeTrabajo_MD_For_Idkey_sesion_manana_CASCADE(int idkey_sesion_manana,Object modeloQueLoLlamo) throws Exception {
		List<DiaDeTrabajo_MD> l=getDiaDeTrabajo_MD_All_Idkey_sesion_manana(idkey_sesion_manana);
		for(int i=0;i<l.size();i++){
			DiaDeTrabajo_MD dia_de_trabajo=l.get(i);
			deleteOnePlantillaTrabajadorManyDiaDeTrabajo_MD_For_Idkey_dia_de_trabajo(dia_de_trabajo.idkey);
			if(modeloQueLoLlamo!=null&& modeloQueLoLlamo instanceof Sesion_MD){
				deleteSesion_MD_ForId(dia_de_trabajo.idkey_sesion_manana);
			}else{
				deleteSesion_MD_ForId_CASCADE(dia_de_trabajo.idkey_sesion_manana,dia_de_trabajo);
			}
			if(modeloQueLoLlamo!=null&& modeloQueLoLlamo instanceof Sesion_MD){
				deleteSesion_MD_ForId(dia_de_trabajo.idkey_sesion_tarde);
			}else{
				deleteSesion_MD_ForId_CASCADE(dia_de_trabajo.idkey_sesion_tarde,dia_de_trabajo);
			}
		}
		deleteDiaDeTrabajo_MD_For_Idkey_sesion_manana(idkey_sesion_manana);
	}
	public List<DiaDeTrabajo_MD> getDiaDeTrabajo_MD_All_Idkey_sesion_tarde(int idkey_sesion_tarde) throws Exception {
			List<DiaDeTrabajo_MD> lista=new ArrayList<>();
			Object [][]O=this.BD.select_Where(DiaDeTrabajo_MD.TABLA_DIA_DE_TRABAJO,DiaDeTrabajo_MD.COLUMNA_SESION_TARDE,idkey_sesion_tarde);
			if (O!=null){
				for(int i=0;i<O.length;i++){
					lista.add(getDiaDeTrabajo_MD_Args(O[i]));
				}
			}
			return lista;
	}
	public void deleteDiaDeTrabajo_MD_For_Idkey_sesion_tarde(int idkey_sesion_tarde) throws Exception {
			this.BD.delete(DiaDeTrabajo_MD.TABLA_DIA_DE_TRABAJO,DiaDeTrabajo_MD.COLUMNA_SESION_TARDE,idkey_sesion_tarde);
	}
	public void deleteDiaDeTrabajo_MD_For_Idkey_sesion_tarde_CASCADE(int idkey_sesion_tarde) throws Exception {
		deleteDiaDeTrabajo_MD_For_Idkey_sesion_tarde_CASCADE(idkey_sesion_tarde,null);
	}
	public void deleteDiaDeTrabajo_MD_For_Idkey_sesion_tarde_CASCADE(int idkey_sesion_tarde,Object modeloQueLoLlamo) throws Exception {
		List<DiaDeTrabajo_MD> l=getDiaDeTrabajo_MD_All_Idkey_sesion_tarde(idkey_sesion_tarde);
		for(int i=0;i<l.size();i++){
			DiaDeTrabajo_MD dia_de_trabajo=l.get(i);
			deleteOnePlantillaTrabajadorManyDiaDeTrabajo_MD_For_Idkey_dia_de_trabajo(dia_de_trabajo.idkey);
			if(modeloQueLoLlamo!=null&& modeloQueLoLlamo instanceof Sesion_MD){
				deleteSesion_MD_ForId(dia_de_trabajo.idkey_sesion_manana);
			}else{
				deleteSesion_MD_ForId_CASCADE(dia_de_trabajo.idkey_sesion_manana,dia_de_trabajo);
			}
			if(modeloQueLoLlamo!=null&& modeloQueLoLlamo instanceof Sesion_MD){
				deleteSesion_MD_ForId(dia_de_trabajo.idkey_sesion_tarde);
			}else{
				deleteSesion_MD_ForId_CASCADE(dia_de_trabajo.idkey_sesion_tarde,dia_de_trabajo);
			}
		}
		deleteDiaDeTrabajo_MD_For_Idkey_sesion_tarde(idkey_sesion_tarde);
	}
	public List<PlantillaTrabajador_MD> getPlantillaTrabajador_MD_All_Idkey_trabajador(int idkey_trabajador) throws Exception {
			List<PlantillaTrabajador_MD> lista=new ArrayList<>();
			Object [][]O=this.BD.select_Where(PlantillaTrabajador_MD.TABLA_PLANTILLA_TRABAJADOR,PlantillaTrabajador_MD.COLUMNA_ID_TABLA_TRABAJADOR,idkey_trabajador);
			if (O!=null){
				for(int i=0;i<O.length;i++){
					lista.add(getPlantillaTrabajador_MD_Args(O[i]));
				}
			}
			return lista;
	}
	public void deletePlantillaTrabajador_MD_For_Idkey_trabajador(int idkey_trabajador) throws Exception {
			this.BD.delete(PlantillaTrabajador_MD.TABLA_PLANTILLA_TRABAJADOR,PlantillaTrabajador_MD.COLUMNA_ID_TABLA_TRABAJADOR,idkey_trabajador);
	}
	public void deletePlantillaTrabajador_MD_For_Idkey_trabajador_CASCADE(int idkey_trabajador) throws Exception {
		deletePlantillaTrabajador_MD_For_Idkey_trabajador_CASCADE(idkey_trabajador,null);
	}
	public void deletePlantillaTrabajador_MD_For_Idkey_trabajador_CASCADE(int idkey_trabajador,Object modeloQueLoLlamo) throws Exception {
		List<PlantillaTrabajador_MD> l=getPlantillaTrabajador_MD_All_Idkey_trabajador(idkey_trabajador);
		for(int i=0;i<l.size();i++){
			PlantillaTrabajador_MD plantilla_trabajador=l.get(i);
			deleteOnePlantillaTrabajadorManyDiaDeTrabajo_MD_For_Idkey_plantilla_trabajador(plantilla_trabajador.idkey);
		}
		deletePlantillaTrabajador_MD_For_Idkey_trabajador(idkey_trabajador);
	}
	public List<DiaDeTrabajo_MD> getDiaDeTrabajo_MD_All_Idkey_plantilla_trabajador(int idkey_plantilla_trabajador) throws Exception {
		Object [][]O=this.BD.select_Where_Inner_Join_TodoDeTabla(DiaDeTrabajo_MD.TABLA_DIA_DE_TRABAJO
			,new Object[]{
				new Object[]{
					new Object[]{OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO,OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_PLANTILLA_TRABAJADOR},
					new Object[]{PlantillaTrabajador_MD.TABLA_PLANTILLA_TRABAJADOR}
				},
				new Object[]{
					new Object[]{OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO,OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_DIA_DE_TRABAJO},
					new Object[]{DiaDeTrabajo_MD.TABLA_DIA_DE_TRABAJO}
				}
			}
			
			,new Object[]{PlantillaTrabajador_MD.TABLA_PLANTILLA_TRABAJADOR},idkey_plantilla_trabajador);
		List<DiaDeTrabajo_MD> lista=new ArrayList<>();
		if (O!=null){
			for(int i=0;i<O.length;i++){
				lista.add(getDiaDeTrabajo_MD_Args(O[i]));
			}
		}
		return lista;
	}
	public OnePlantillaTrabajadorManyDiaDeTrabajo_MD getOnePlantillaTrabajadorManyDiaDeTrabajo_MD(int idkey_plantilla_trabajador,int idkey_dia_de_trabajo) throws Exception {
			List<OnePlantillaTrabajadorManyDiaDeTrabajo_MD> lista=new ArrayList<>();
			Object []O=this.BD.select_Where_FirstRow(OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO,OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_PLANTILLA_TRABAJADOR,idkey_plantilla_trabajador,OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_DIA_DE_TRABAJO,idkey_dia_de_trabajo);
			if (O!=null){
				return getOnePlantillaTrabajadorManyDiaDeTrabajo_MD_Args(O);
			}
			return null;
	}
	public boolean existePlantillaTrabajador_MD_And_DiaDeTrabajo_MD(int idkey_plantilla_trabajador,int idkey_dia_de_trabajo) throws Exception {
			return this.BD.existe(OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO
					,OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_PLANTILLA_TRABAJADOR,idkey_plantilla_trabajador
					,OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_DIA_DE_TRABAJO,idkey_dia_de_trabajo);
	}
	public List<OnePlantillaTrabajadorManyDiaDeTrabajo_MD> getOnePlantillaTrabajadorManyDiaDeTrabajo_MD_All_Idkey_plantilla_trabajador(int idkey_plantilla_trabajador) throws Exception {
			List<OnePlantillaTrabajadorManyDiaDeTrabajo_MD> lista=new ArrayList<>();
			Object [][]O=this.BD.select_Where(OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO,OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_PLANTILLA_TRABAJADOR,idkey_plantilla_trabajador);
			if (O!=null){
				for(int i=0;i<O.length;i++){
					lista.add(getOnePlantillaTrabajadorManyDiaDeTrabajo_MD_Args(O[i]));
				}
			}
			return lista;
	}
	public void deleteOnePlantillaTrabajadorManyDiaDeTrabajo_MD_For_Idkey_plantilla_trabajador(int idkey_plantilla_trabajador) throws Exception {
			this.BD.delete(OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO,OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_PLANTILLA_TRABAJADOR,idkey_plantilla_trabajador);
	}
	public List<OnePlantillaTrabajadorManyDiaDeTrabajo_MD> getOnePlantillaTrabajadorManyDiaDeTrabajo_MD_All_Idkey_dia_de_trabajo(int idkey_dia_de_trabajo) throws Exception {
			List<OnePlantillaTrabajadorManyDiaDeTrabajo_MD> lista=new ArrayList<>();
			Object [][]O=this.BD.select_Where(OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO,OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_DIA_DE_TRABAJO,idkey_dia_de_trabajo);
			if (O!=null){
				for(int i=0;i<O.length;i++){
					lista.add(getOnePlantillaTrabajadorManyDiaDeTrabajo_MD_Args(O[i]));
				}
			}
			return lista;
	}
	public void deleteOnePlantillaTrabajadorManyDiaDeTrabajo_MD_For_Idkey_dia_de_trabajo(int idkey_dia_de_trabajo) throws Exception {
			this.BD.delete(OnePlantillaTrabajadorManyDiaDeTrabajo_MD.TABLA_ONE_PLANTILLA_TRABAJADOR_MANY_DIA_DE_TRABAJO,OnePlantillaTrabajadorManyDiaDeTrabajo_MD.COLUMNA_ID_TABLA_DIA_DE_TRABAJO,idkey_dia_de_trabajo);
	}
}
