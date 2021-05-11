package com.controldeprocesos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

public class ControlBDGpo16 {
    private static final String[] camposReservacion = new String [] {"idReservacion","idLocal","idDocente","horaInicio","fecha","horaFin"};
    private static final String[] camposCiclo= new String [] {"idciclo","anio","numCiclo"};
    private static final String[] camposMateria= new String [] {"codMateria","codEscuela","nombre"};
    private DatabaseHelper DBHelper;
    private final Context context;
    private SQLiteDatabase db;

    public ControlBDGpo16(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);}

        //Métodos para la tabla Reservación.

    public String insertar(Reservacion reservacion){
        String regInsertados="¡Reservación registrada!";
        if(verificarIntegridad(reservacion, 4)){
        long contador=0;
        ContentValues reservacion_ = new ContentValues();
        reservacion_.put("idReservacion",reservacion.getIdReservacion());
        reservacion_.put("idLocal",reservacion.getIdLocal());
        reservacion_.put("idDocente",reservacion.getIdDocente());
        reservacion_.put("horaInicio",reservacion.getHoraInicio());
        reservacion_.put("fecha",reservacion.getFecha().toString());
        reservacion_.put("horaFin",reservacion.getHoraFin());
        contador=db.insert("reservacion", null, reservacion_);

        if(contador==-1 || contador==0){regInsertados= "Error al insertar el registro. Verifique la inserción, por favor";}}
        else{
            //regInsertados= "El docente "+reservacion.getIdDocente()+" no existe";
            regInsertados= "El docente o el local no existe";}

        return regInsertados;}

    public Reservacion consultarReservacion(int idReservacion){
        String[] id = {String.valueOf(idReservacion)};
        Cursor cursor = db.query("reservacion", camposReservacion, "idReservacion = ?", id, null, null, null);

        if(cursor.moveToFirst()){
            Reservacion reservacion = new Reservacion();
            reservacion.setIdReservacion(cursor.getInt(0));
            reservacion.setIdLocal(cursor.getInt(1));
            reservacion.setIdDocente(cursor.getInt(2));
            reservacion.setHoraInicio(cursor.getString(3));
            reservacion.setFecha(Date.valueOf(cursor.getString(4)));
            reservacion.setHoraFin(cursor.getString(5));
            return reservacion;}else{return null;}}

    public String actualizar(Reservacion reservacion) {
        if(verificarIntegridad(reservacion, 1)){
            if(verificarIntegridad(reservacion, 4)){
            String[] id = {String.valueOf(reservacion.getIdReservacion())};
            ContentValues cv = new ContentValues();
            cv.put("idLocal",reservacion.getIdLocal());
            cv.put("idDocente",reservacion.getIdDocente());
            cv.put("horaInicio",reservacion.getHoraInicio());
            cv.put("fecha",String.valueOf(reservacion.getFecha()));
            cv.put("horaFin",reservacion.getHoraFin());
            db.update("reservacion", cv, "idReservacion = ?", id);
            return "Registro actualizado correctamente";}
        else{
            return "El local o el docente no existe";}}
        else{return "La reservación "+reservacion.getIdReservacion()+" no existe";}}

    public String eliminar(Reservacion reservacion) {
        String regAfectados="Filas afectadas: ";
        int contador=0;
        contador+=db.delete("reservacion", "idReservacion='"+reservacion.getIdReservacion()+"'", null);
        regAfectados+=contador;
        return regAfectados;}

    //Métodos para la tabla Ciclo.

    public String insertar(Ciclo ciclo){
        String regInsertados="¡Ciclo registrado!";
        long contador=0;
        ContentValues ciclo_ = new ContentValues();
        ciclo_.put("idCiclo",ciclo.getIdCiclo());
        ciclo_.put("anio",ciclo.getAnio());
        ciclo_.put("numCiclo",ciclo.getNumCiclo());
        contador=db.insert("ciclo", null,ciclo_);

        if(contador==-1 || contador==0){regInsertados= "Error al insertar el registro. Verifique la inserción, por favor";}

        return regInsertados;}

    public Ciclo consultarCiclo(int idCiclo){
        String[] id = {String.valueOf(idCiclo)};
        Cursor cursor = db.query("ciclo", camposCiclo, "idCiclo = ?", id, null, null, null);

        if(cursor.moveToFirst()){
            Ciclo ciclo = new Ciclo();
            ciclo.setIdCiclo(cursor.getInt(0));
            ciclo.setAnio(cursor.getInt(1));
            ciclo.setNumCiclo(cursor.getInt(2));
            return ciclo;}else{return null;}}

    public String actualizar(Ciclo ciclo) {
        if(verificarIntegridad(ciclo, 2)){
            String[] id = {String.valueOf(ciclo.getIdCiclo())};
            ContentValues cv = new ContentValues();
            cv.put("anio",ciclo.getAnio());
            cv.put("numciclo",ciclo.getNumCiclo());
            db.update("ciclo", cv, "idCiclo = ?", id);
            return "Registro actualizado correctamente";}
        else{return "El ciclo "+ciclo.getIdCiclo()+" no existe";}}

    public String eliminar(Ciclo ciclo) {
        String regAfectados="Filas afectadas: ";
        int contador=0;
        if(verificarIntegridad(ciclo, 3)){contador+=db.delete("ciclo", "idCiclo='"+ciclo.getIdCiclo()+"'", null);}
        regAfectados+=contador;
        return regAfectados;}

    //Métodos para la tabla Materia.

    public String insertar(Materia materia){
        String regInsertados="¡Materia registrada!";
        if(verificarIntegridad(materia, 7)){
        long contador=0;
        ContentValues materia_ = new ContentValues();
        materia_.put("codMateria",materia.getCodMateria());
        materia_.put("codEscuela",materia.getCodEscuela());
        materia_.put("nombre",materia.getNombre());
        contador=db.insert("materia", null,materia_);

        if(contador==-1 || contador==0){regInsertados= "Error al insertar el registro. Verifique la inserción, por favor";}}
        else{regInsertados= "No existe la escuela";}

        return regInsertados;}

    public Materia consultarMateria(String codMateria){
        String[] id = {codMateria};
        Cursor cursor = db.query("materia", camposMateria, "codMateria = ?", id, null, null, null);

        if(cursor.moveToFirst()){
            Materia materia = new Materia();
            materia.setCodMateria(cursor.getString(0));
            materia.setCodEscuela(cursor.getInt(1));
            materia.setNombre(cursor.getString(2));
            return materia;}else{return null;}}

    public String actualizar(Materia materia) {
        if(verificarIntegridad(materia, 5)){
            if(verificarIntegridad(materia, 7)){
            String[] id = {materia.getCodMateria()};
            ContentValues cv = new ContentValues();
            cv.put("codEscuela",materia.getCodEscuela());
            cv.put("nombre",materia.getNombre());
            db.update("materia", cv, "codMateria = ?", id);
            return "Registro actualizado correctamente";}
        else{return "No existe la escuela";}}
        else{return "La materia "+materia.getCodMateria()+" no existe";}}

    public String eliminar(Materia materia) {
        String regAfectados="Filas afectadas: ";
        int contador=0;
        if(verificarIntegridad(materia, 6)){contador+=db.delete("materia", "codMateria='"+materia.getCodMateria()+"'", null);}
        regAfectados+=contador;
        return regAfectados;}

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "db.s3db";
        private static final int VERSION = 1;
        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);}

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE reservacion(idReservacion INTEGER NOT NULL PRIMARY KEY,idLocal integer,idDocente integer,horaInicio VARCHAR(5),fecha DATE,horaFin VARCHAR(5));");
                db.execSQL("CREATE TABLE ciclo(idCiclo INTEGER NOT NULL PRIMARY KEY,anio INTEGER,numCiclo INTEGER);");
                db.execSQL("CREATE TABLE materia(codMateria VARCHAR(6) NOT NULL PRIMARY KEY,codEscuela varchar(10),nombre VARCHAR(75));");
                db.execSQL("CREATE TABLE solicitudDeImpresiones(idSolicitud INTEGER NOT NULL PRIMARY KEY,numPaginas INTEGER,estadoAprobado boolean);");
                db.execSQL("CREATE TABLE cargo(idCargo INTEGER NOT NULL PRIMARY KEY,nombreCargo VARCHAR(30));");
                db.execSQL("CREATE TABLE local(idLocal INTEGER NOT NULL PRIMARY KEY,nombre VARCHAR(10));");
                db.execSQL("CREATE TABLE docente(idDocente INTEGER NOT NULL PRIMARY KEY,idUsuario integer);");
                db.execSQL("CREATE TABLE usuario(idUsuario INTEGER NOT NULL PRIMARY KEY,tipo VARCHAR(10),contrasena VARCHAR(30),nombre VARCHAR(75),correo VARCHAR(75));");
                db.execSQL("CREATE TABLE accesoUsuario(idAcceso integer not null primary key,idUsuario integer,idPermiso integer);");
                db.execSQL("CREATE TABLE encargadoDeImpresiones(idEncargado INTEGER NOT NULL PRIMARY KEY);");
                db.execSQL("CREATE TABLE matricula(idMatricula INTEGER NOT NULL PRIMARY KEY,carnet varchar(7),codMateria varchar(6),idCiclo integer,numMatricula integer);");
                db.execSQL("CREATE TABLE segundaRevision(numRev2 INTEGER NOT NULL PRIMARY KEY,idExamen integer,respSociedadEstud varchar(7),idDocente integer,idOtroDocente integer,notaDefinitica float);");
                db.execSQL("CREATE TABLE revision(numRev1 INTEGER NOT NULL PRIMARY KEY,nuevaNota float,observ text,asistio boolean,idExamen integer);");
                db.execSQL("CREATE TABLE evaluacion(numEva INTEGER NOT NULL PRIMARY KEY,idDocente integer,codMateria varchar(6),tipo VARCHAR(10),alumnosEvaluados integer,fechaRealización date,fechaPublicacion date);");
                db.execSQL("CREATE TABLE examenIndividual(idExamen INTEGER NOT NULL PRIMARY KEY,numEva integer,carnet integer,nota float);");
                db.execSQL("CREATE TABLE escuela(codEscuela INTEGER NOT NULL PRIMARY KEY,nombre VARCHAR(75));");
                db.execSQL("CREATE TABLE estudiante(carnet varchar(7) NOT NULL PRIMARY KEY);");
                db.execSQL("CREATE TABLE permiso(idPermiso INTEGER NOT NULL PRIMARY KEY,descipcionP text);");
                db.execSQL("CREATE TABLE catedra(idCatedra INTEGER NOT NULL primary key,idCiclo,codMateria varchar(6),idDocente INTEGER);");
                db.execSQL("CREATE TABLE instructor_emite(carnet varchar(7) NOT NULL,idSolicitud integer not null,constraint PK_INSTRUCTOR_EMITE primary key (carnet,idSolicitud));");
                db.execSQL("CREATE TABLE posee(idDocente INTEGER NOT NULL,idCargo integer NOT NULL,constraint PK_POSEE primary key (idDocente,idCargo));");
                db.execSQL("CREATE TABLE emite(idDocente INTEGER NOT NULL,idSolicitud NOT NULL,constraint PK_EMITE primary key (idDocente,idSolicitud));");
            }catch(SQLException e){e.printStackTrace();}}

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
        }}

    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;}

    public void cerrar(){DBHelper.close();}

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        switch(relacion){

            //Verificación de integridad de CN18006 (del caso 1 al caso 7).
            case 1:{
                //Verificar que exista la reservación para poder actualizarla.
                Reservacion reservacion = (Reservacion)dato;
                String[] id = {String.valueOf(reservacion.getIdReservacion())};
                abrir();
                Cursor c = db.query("reservacion", null, "idReservacion = ?", id, null, null,null);
                    if(c.moveToFirst()){
                        //Se encontró la reservación.
                        return true;}
                    return false;}
            case 2:{
                //Verificar que exista el ciclo para poder actualizarlo.
                Ciclo ciclo = (Ciclo)dato;
                String[] id = {String.valueOf(ciclo.getIdCiclo())};
                abrir();
                Cursor c = db.query("ciclo", null, "idCiclo = ?", id, null, null,null);
                if(c.moveToFirst()){
                    //Se encontró ciclo.
                    return true;}
                return false;}
            case 3:{
                //Verificar que el ciclo no tenga registros hijos antes de borrarlo.
                Ciclo ciclo=(Ciclo)dato;
                String[] id = {String.valueOf(ciclo.getIdCiclo())};
                abrir();
                Cursor c1 = db.query("catedra", null, "idCiclo = ?", id, null, null,null);
                Cursor c2 = db.query("matricula", null, "idCiclo = ?", id, null, null,null);
                if(c1.moveToFirst() || c2.moveToFirst()){
                    //El registro que se quiere borrar es padre de otros registros en otras tablas.
                    return false;}
                return true;}
            case 4:{
                //Verificar que al registrar una reservación exista el docente que reserva y el local que están reservando.
                Reservacion reservacion = (Reservacion)dato;
                String[] id1 = {String.valueOf(reservacion.getIdDocente())};
                String[] id2 = {String.valueOf(reservacion.getIdLocal())};
                abrir();
                Cursor c1 = db.query("docente", null, "idDocente = ?", id1,null, null, null);
                Cursor c2 = db.query("local", null, "idLocal = ?", id2, null,null, null);
                if(c1.moveToFirst() && c2.moveToFirst()){
                    //Se encontraron datos.
                    return true;}
                return false;}
            case 5:{
                //Verificar que exista la materia para poder actualizarla.
                Materia materia = (Materia)dato;
                String[] id = {materia.getCodMateria()};
                abrir();
                Cursor c = db.query("materia", null, "codMateria = ?", id, null, null,null);
                if(c.moveToFirst()){
                    //Se encontró ciclo.
                    return true;}
                return false;}
            case 6:{
                //Verificar que la materia no tenga registros hijos antes de borrarla.
                Materia materia=(Materia)dato;
                String[] id = {String.valueOf(materia.getCodMateria())};
                abrir();
                Cursor c1 = db.query("catedra", null, "codMateria = ?", id, null, null,null);
                Cursor c2 = db.query("matricula", null, "codMateria = ?", id, null, null,null);
                Cursor c3 = db.query("evaluacion", null, "codMateria = ?", id, null, null,null);
                if(c1.moveToFirst() || c2.moveToFirst() || c3.moveToFirst()){
                    //El registro que se quiere borrar es padre de otros registros en otras tablas.
                    return false;}
                return true;}
            case 7:{
                //Verificar que al registrar una materia exista la escuela a la cual pertenece.
                Materia materia = (Materia)dato;
                String[] id = {String.valueOf(materia.getCodEscuela())};
                abrir();
                Cursor c = db.query("escuela", null, "codEscuela = ?", id,null, null, null);
                if(c.moveToFirst()){
                    //Se encontraron datos.
                    return true;}
                return false;}
            default:
                return false;}}

    public String LlenarBDGpo16(){
        abrir();
        cerrar();
        return "Guardó correctamente";}}
