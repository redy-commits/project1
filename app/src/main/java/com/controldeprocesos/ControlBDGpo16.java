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
    private DatabaseHelper DBHelper;
    private final Context context;
    private SQLiteDatabase db;

    public ControlBDGpo16(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);}

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "controlProcesos.s3db";
        private static final int VERSION = 1;
        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);}

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE reservacion(idReservacion INTEGER NOT NULL PRIMARY KEY,idLocal integer not null,idDocente integer not null,horaInicio VARCHAR(5) not null,fecha DATE not null,horaFin VARCHAR(5) not null);");
                db.execSQL("CREATE TABLE ciclo(idCiclo INTEGER NOT NULL PRIMARY KEY,anio INTEGER NOT NULL,numCiclo INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE materia(codMateria VARCHAR(6) NOT NULL PRIMARY KEY,codigoEscuela varchar(10) not null,nombre VARCHAR(75) NOT NULL);");
                db.execSQL("CREATE TABLE solicitudDeImpresiones(idSolicitud INTEGER NOT NULL PRIMARY KEY,numPaginas INTEGER NOT NULL,estadoAprobado boolean NOT NULL);");
                db.execSQL("CREATE TABLE cargo(idCargo INTEGER NOT NULL PRIMARY KEY,nombreCargo VARCHAR(30) NOT NULL);");
                db.execSQL("CREATE TABLE local(idLocal INTEGER NOT NULL PRIMARY KEY,nombre VARCHAR(10) NOT NULL);");
                db.execSQL("CREATE TABLE docente(idDocente INTEGER NOT NULL PRIMARY KEY,idUsuario integer NOT NULL);");
                db.execSQL("CREATE TABLE usuario(idUsuario INTEGER NOT NULL PRIMARY KEY,tipo VARCHAR(10) NOT NULL,contrasena VARCHAR(30) NOT NULL,nombre VARCHAR(75) NOT NULL,correo VARCHAR(75) NOT NULL);");
                db.execSQL("CREATE TABLE accesoUsuario(idAcceso integer not null primary key,idUsuario integer not null,idPermiso integer not null);");
                db.execSQL("CREATE TABLE encargadoDeImpresiones(idEncargado INTEGER NOT NULL PRIMARY KEY);");
                db.execSQL("CREATE TABLE matricula(idMatricula INTEGER NOT NULL PRIMARY KEY,carnet varchar(7) not null,codMateria varchar(6) not null,idCiclo integer not null,numMatricula integer NOT NULL);");
                db.execSQL("CREATE TABLE segundaRevision(numRev2 INTEGER NOT NULL PRIMARY KEY,idExamen integer not null,respSociedadEstud varchar(7) not null,idDocente integer not null,idOtroDocente integer not null,notaDefinitica float NOT NULL);");
                db.execSQL("CREATE TABLE revision(numRev1 INTEGER NOT NULL PRIMARY KEY,nuevaNota float NOT NULL,observ text NOT NULL,asistio boolean NOT NULL,idExamen integer not null);");
                db.execSQL("CREATE TABLE evaluacion(numEva INTEGER NOT NULL PRIMARY KEY,idDocente integer not null,codMateria varchar(6) not null,tipo VARCHAR(10) NOT NULL,alumnosEvaluados integer NOT NULL,fechaRealización date NOT NULL,fechaPublicacion date NOT NULL);");
                db.execSQL("CREATE TABLE examenIndividual(idExamen INTEGER NOT NULL PRIMARY KEY,numEva integer not null,carnet integer not null,nota float NOT NULL);");
                db.execSQL("CREATE TABLE escuela(codEscuela INTEGER NOT NULL PRIMARY KEY,nombre VARCHAR(75) NOT NULL);");
                db.execSQL("CREATE TABLE estudiante(carnet varchar(7) NOT NULL PRIMARY KEY);");
                db.execSQL("CREATE TABLE permiso(idPermiso INTEGER NOT NULL PRIMARY KEY,descipcionP text NOT NULL);");
                db.execSQL("CREATE TABLE catedra(idCatedra INTEGER NOT NULL primary key,idCiclo NOT NULL,codMateria varchar(6) NOT NULL,idDocente INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE instructor_emite(carnet varchar(7) NOT NULL,idSolicitud integer not null,constraint PK_INSTRUCTOR_EMITE primary key (carnet,idSolicitud));");
                db.execSQL("CREATE TABLE posee(idDocente INTEGER NOT NULL,idCargo integer NOT NULL,constraint PK_POSEE primary key (idDocente,idCargo));");
                db.execSQL("CREATE TABLE emite(idDocente INTEGER NOT NULL,idSolicitud NOT NULL,constraint PK_EMITE primary key (idDocente,idSolicitud));");
            }catch(SQLException e){
                e.printStackTrace();}}

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
        }}

    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;}

    public void cerrar(){DBHelper.close();}

    public String insertar(Reservacion reservacion){
        String regInsertados="Registro insertado #";
        long contador=0;
        ContentValues reservacion_ = new ContentValues();
        reservacion_.put("idReservacion",reservacion.getIdReservacion());
        reservacion_.put("idLocal",reservacion.getIdLocal());
        reservacion_.put("iddocente",reservacion.getIdDocente());
        reservacion_.put("horaInicio",reservacion.getHoraInicio());
        reservacion_.put("fecha",reservacion.getFecha().toString());
        reservacion_.put("horaFin",reservacion.getHoraFin());
        contador=db.insert("reservacion", null, reservacion_);

        if(contador==-1 || contador==0){
            regInsertados= "Error al insertar el registro. Verifique la inserción, por favor";}
        else{regInsertados=regInsertados+contador;}
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
            return reservacion;}
        else{return null;}}

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        switch(relacion){
            case 1:{
                //Verificar que al insertar una reservación exista el local que se reserva y el docente que reserva.
                Reservacion reservacion = (Reservacion)dato;
                String[] id1 = {String.valueOf(reservacion.getIdReservacion())};
                Cursor c = db.query("local", null, "idReservacion = ?", id1, null,null, null);

                if(c.moveToFirst()){
                    //Se encontraron datos.
                    return true;}
                return false;}
            default:
                return false;}}

    public String LlenarBDGpo16(){
        cerrar();
        return "Guardó correctamente";}}
