package com.controldeprocesos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Date;

public class ControlBDGpo16 {
    private static final String[] camposReservacion = new String [] {"idReservacion","idLocal","idDocente","horaInicio","fecha","horaFin"};
    private static final String[] camposCiclo= new String [] {"idCiclo","anio","numCiclo"};
    private static final String[] camposMateria= new String [] {"codMateria","codEscuela","nombre"};
    private static final String[] camposUsuario= new String [] {"idUsuario","tipo","contrasena","nombre","correo","sesion"};
    private static final String[] camposPermiso= new String [] {"idPermiso","descripcion"};
    private static final String[] camposEscuela= new String [] {"codEscuela","nombre"};
    private static final String[] camposEstudiante= new String [] {"carnet","idUsuario"};
    private static final String[] camposDocente= new String [] {"idDocente","idUsuario"};
    private static final String[] camposEncargado= new String [] {"idEncargado","idUsuario"};
    private static final String[] camposExamenIndividual= new String [] {"idExamen","numEva","carnet","nota"};
    private static final String[] camposSegundaRevision= new String [] {"numRev2","idExamen","respSociedadEstud","idDocente","idOtroDocente","notaDefinitiva"};
    private static final String[] camposMatricula= new String [] {"idMatricula","carnet","codMateria","idCiclo","numMatricula"};
    private static final String[] camposCargo= new String [] {"idCargo","nombreCargo"};
    private static final String[] camposEvaluacion= new String [] {"numEva", "idDocente", "alumnosEvaluados", "codMateria", "tipo", "fechaRealizacion", "fechaPublicacion"};
    private static final String[] camposRevision= new String [] {"numRev1", "idExamen", "nuevaNota", "observ","asistio"};
    private static final String[] camposSolicitudDeImpresiones= new String[] {"idSolicitud", "numPaginas", "estadoAprobado"};
    private DatabaseHelper DBHelper;
    private final Context context;
    private SQLiteDatabase db;

    public ControlBDGpo16(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);}

 //-----------------------AQUI ESTA INSERTAR ALUMNO--------------------------------
    public String insertar(Estudiante estudiante, Usuario user){
    String regInsertados="Usuario Agregado con exito ";
    long contador;
    ContentValues alum = new ContentValues();
       alum.put("correo",user.getCorreo());
        alum.put("nombre",user.getNombre());
       alum.put("contrasena",user.getContrasena());
       alum.put("tipo",user.getTipo());
       alum.put("sesion",user.isSesion());

    contador=db.insert("usuario", null, alum);
    if(contador==-1 || contador==0)
    {
        regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
    }

    else {
        regInsertados=regInsertados+contador;
    }
        String regInsertado="Alumno Agregado con exito ";
        long contado;
        ContentValues alumno = new ContentValues();
        String[] id = {user.getCorreo()};
        @SuppressLint("Recycle") Cursor cursor = db.query("usuario", camposUsuario, "correo = ?",  id, null, null, null);
        if(cursor.moveToFirst()){
            Usuario usua = new Usuario();
            usua.setIdUsuario(Integer.parseInt(cursor.getString(0)));
            alumno.put("carnet",estudiante.getCarnet());
            alumno.put("idUsuario",usua.getIdUsuario());
            contado=db.insert("estudiante", null, alumno);
            if(contado==-1 || contado==0)
            {
                regInsertado= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            }
        }
        else
        {
         regInsertado="  Error al ingresar al estudiante";
        }
    return regInsertados+regInsertado;
}
//-------------------------ELIMINAR ESTUDIANTE---------------------------------------------------------
    public String eliminar(String carnet){
        String[] id = {carnet};
        String regAfectados="filas afectadas= ";

        //Integridad
        //  if (verificarIntegridad(escuela,3)) {
        //   contador+=db.delete("escuela", "codEscuela='"+escuela.getcodEscuela()+"'", null);
        // }
        int contador=0;
        Cursor cursor = db.query("estudiante", camposEstudiante, "carnet = ?",  id, null, null, null);
        if(cursor.moveToFirst()){
            int idusuario=Integer.parseInt(cursor.getString(1));
            contador+=db.delete("estudiante", "carnet='"+carnet+"'", null);
            contador+=db.delete("usuario", "idUsuario='"+idusuario+"'", null);

        }
        regAfectados+=contador;
        return regAfectados;
    }
    //Metodo Actualizar estudiante
    public String actualizar(Estudiante estudiante, Usuario usuario){
        if(verificarIntegridad(estudiante, 14)){
            String[] id = {estudiante.getCarnet()};


            ContentValues u = new ContentValues();

            Cursor cursor = db.query("estudiante", camposEstudiante, "carnet = ?",  id, null, null, null);
            if(cursor.moveToFirst()){

                String idu = String.valueOf(cursor.getString(1));
                u.put("correo",usuario.getCorreo());
                u.put("contrasena",usuario.getContrasena());
                u.put("tipo",usuario.getTipo());
                db.update("usuario", u, "idUsuario = ?", new String[]{idu});
                return "Registro Actualizado Correctamente";
            }
        }else{
            return "Registro con carnet " + estudiante.getCarnet() + " no existe";
        }
        return "Algo salio mal";
    }

    //Metodo consultar Estudiante
    public Estudiante consultarEstudiante(String carnet) {
        String[] id = {carnet};
        Cursor cursor = db.query("estudiante", camposEstudiante, "carnet = ?",  id, null, null, null);
        if(cursor.moveToFirst()){
            Estudiante estudiante = new Estudiante();
            estudiante.setCarnet(cursor.getString(0));
            estudiante.setIdUsuario(cursor.getInt(1));
            return estudiante;
        }else{
            return null;
        }
    }
    //Insertar Encargado de Impresiones
    public String insertarEncargado(Usuario user){
        String regInsertados="Usuario Agregado con exito ";
        long contador;
        ContentValues doc = new ContentValues();
        doc.put("correo",user.getCorreo());
        doc.put("nombre",user.getNombre());
        doc.put("contrasena",user.getContrasena());
        doc.put("tipo",user.getTipo());
        doc.put("sesion",user.isSesion());

        contador=db.insert("usuario", null, doc);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }

        else {
            regInsertados=regInsertados+contador;
        }
        String regInsertado="Encargado Agregado con exito ";
        long contado;
        ContentValues encargado = new ContentValues();
        String[] id = {user.getCorreo()};
        @SuppressLint("Recycle") Cursor cursor = db.query("usuario", camposUsuario, "correo = ?",  id, null, null, null);
        if(cursor.moveToFirst()){
            Usuario usua = new Usuario();
            usua.setIdUsuario(Integer.parseInt(cursor.getString(0)));
            encargado.put("idUsuario",usua.getIdUsuario());
            contado=db.insert("encargadoDeImpresiones", null, encargado);
            if(contado==-1 || contado==0)
            {
                regInsertado= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            }
        }
        else
        {
            regInsertado="  Error al ingresar al docente";
        }
        return regInsertados+regInsertado;
    }
    //Consultar Encargado de impresiones
    public EncargadoDeImpresiones consultarEncargado(String idEncargadoDeImpresiones) {
        String[] id = {idEncargadoDeImpresiones};
        Cursor cursor = db.query("encargadoDeImpresiones", camposEncargado, "idEncargado = ?",  id, null, null, null);
        if(cursor.moveToFirst()){
            EncargadoDeImpresiones encargado= new EncargadoDeImpresiones();
            encargado.setIdEncargadoDeImpresiones(Integer.parseInt(cursor.getString(0)));
            encargado.setIdUsuario(cursor.getInt(1));
            return encargado;
        }else{
            return null;
        }
    }

    //-------------------ELIMINAR ENCARGADO DE IMPRESIONES-----------------
    public String eliminarEncargado(String carnet){
        String[] id = {carnet};
        String regAfectados="filas afectadas= ";

        //Integridad
        //  if (verificarIntegridad(escuela,3)) {
        //   contador+=db.delete("escuela", "codEscuela='"+escuela.getcodEscuela()+"'", null);
        // }
        int contador=0;
        Cursor cursor = db.query("encargadoDeImpresiones", camposEncargado, "idEncargado = ?",  id, null, null, null);
        if(cursor.moveToFirst()){
            int idusuario=Integer.parseInt(cursor.getString(1));
            contador+=db.delete("encargadoDeImpresiones", "idEncargado='"+carnet+"'", null);
            contador+=db.delete("usuario", "idUsuario='"+idusuario+"'", null);
        }
        regAfectados+=contador;
        return regAfectados;
    }
    //-------------------------------------- ACTUALIZAR ENCARGADO ----------------------------//
    public String actualizarEncargado(EncargadoDeImpresiones encargado, Usuario usuario){
        if(verificarIntegridad(encargado, 17)){
            String[] id = {String.valueOf(encargado.getIdEncargadoDeImpresiones())};


            ContentValues u = new ContentValues();

            Cursor cursor = db.query("encargadoDeImpresiones", camposEncargado, "idEncargado = ?",  id, null, null, null);
            if(cursor.moveToFirst()){

                String idu = String.valueOf(cursor.getString(1));
                u.put("correo",usuario.getCorreo());
                u.put("contrasena",usuario.getContrasena());
                u.put("tipo",usuario.getTipo());
                db.update("usuario", u, "idUsuario = ?", new String[]{idu});
                return "Registro Actualizado Correctamente";
            }
        }else{
            return "Registro con ID " + encargado.getIdEncargadoDeImpresiones() + " no existe";
        }
        return "Algo salio mal";
    }
    //Insertar Docente
    public String insertarDocente(Usuario user){
        String regInsertados="Usuario Agregado con exito ";
        long contador;
        ContentValues doc = new ContentValues();
        doc.put("correo",user.getCorreo());
        doc.put("nombre",user.getNombre());
        doc.put("contrasena",user.getContrasena());
        doc.put("tipo",user.getTipo());
        doc.put("sesion",user.isSesion());

        contador=db.insert("usuario", null, doc);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }

        else {
            regInsertados=regInsertados+contador;
        }
        String regInsertado="Docente Agregado con exito ";
        long contado;
        ContentValues docente = new ContentValues();
        String[] id = {user.getCorreo()};
        @SuppressLint("Recycle") Cursor cursor = db.query("usuario", camposUsuario, "correo = ?",  id, null, null, null);
        if(cursor.moveToFirst()){
            Usuario usua = new Usuario();
            usua.setIdUsuario(Integer.parseInt(cursor.getString(0)));
            docente.put("idUsuario",usua.getIdUsuario());
            contado=db.insert("docente", null, docente);
            if(contado==-1 || contado==0)
            {
                regInsertado= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            }
        }
        else
        {
            regInsertado="  Error al ingresar al docente";
        }
        return regInsertados+regInsertado;
    }
    //------------------METODO ACTUALIZAR DOCENTE -------------------------------------//

    public String actualizarDocente(Docente docente, Usuario usuario){
        if(verificarIntegridad(docente, 16)){
            String[] id = {String.valueOf(docente.getIdDocente())};


            ContentValues u = new ContentValues();

            Cursor cursor = db.query("docente", camposDocente, "idDocente = ?",  id, null, null, null);
            if(cursor.moveToFirst()){

                String idu = String.valueOf(cursor.getString(1));
                u.put("correo",usuario.getCorreo());
                u.put("contrasena",usuario.getContrasena());
                u.put("tipo",usuario.getTipo());
                db.update("usuario", u, "idUsuario = ?", new String[]{idu});
                return "Registro Actualizado Correctamente";
            }
        }else{
            return "Registro con carnet " + docente.getIdDocente() + " no existe";
        }
        return "Algo salio mal";
    }
   //-----------------------Metodo borrar Docente---------------------------------------
   public String eliminarDocente(String carnet){
       String[] id = {carnet};
       String regAfectados="filas afectadas= ";

       //Integridad
       //  if (verificarIntegridad(escuela,3)) {
       //   contador+=db.delete("escuela", "codEscuela='"+escuela.getcodEscuela()+"'", null);
       // }
       int contador=0;
       Cursor cursor = db.query("docente", camposDocente, "idDocente = ?",  id, null, null, null);
       if(cursor.moveToFirst()){
           int idusuario=Integer.parseInt(cursor.getString(1));
           contador+=db.delete("docente", "idDocente='"+carnet+"'", null);
           contador+=db.delete("usuario", "idUsuario='"+idusuario+"'", null);
       }
       regAfectados+=contador;
       return regAfectados;
   }
    //Metodo consultar Docente
    public Docente consultarDocente(String idDocente) {
        String[] id = {idDocente};
        Cursor cursor = db.query("docente", camposDocente, "idDocente = ?",  id, null, null, null);
        if(cursor.moveToFirst()){
            Docente docente= new Docente();
            docente.setIdDocente(Integer.parseInt(cursor.getString(0)));
            docente.setIdUsuario(cursor.getInt(1));
            return docente;
        }else{
            return null;
        }
    }
    //---------------------------------------------Metodo insertar segunda revision ------------------------
    public String insertarSegundaRevision( SegundaRevision segunda,String carnet, String numeva) {
        String[] id = {carnet,numeva};

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        Cursor cursor = db.query("examenIndividual", camposExamenIndividual, "carnet = ? AND numEva=?",  id, null, null, null);
        if(cursor.moveToFirst()){
            ExamenIndividual examen = new ExamenIndividual();
            int idExamen= Integer.parseInt(cursor.getString(0));
            ContentValues exam = new ContentValues();
            exam.put("idExamen",idExamen);
            exam.put("respSociedadEstud", segunda.getRespSociedadEstud());
            exam.put("idDocente", segunda.getIdDocente());
            exam.put("idOtroDocente", segunda.getIdOtroDocente());
            exam.put("notaDefinitiva", segunda.getNotaDefinitiva());

            contador=db.insert("segundaRevision", null, exam);
            if(contador==-1 || contador==0)
            {
                regInsertados= "Error al Insertar el registro, Registro  Duplicado. Verificar inserción";
            }
            else {
                regInsertados=regInsertados+contador;
            }
            return regInsertados;


        }else{
            return null;
        }
   }
   //------------------------------CONSULTAR SEGUNDA REVISION---------------------------------------------//
   public String consultarSegundaRevision( String carnet, String numeva) {
       String[] id = {carnet,numeva};
       Cursor cursor = db.query("examenIndividual", camposExamenIndividual, "carnet = ? AND numEva=?",  id, null, null, null);
       if(cursor.moveToFirst()){

           String[] idExamen={cursor.getString(0)};
           Cursor curso = db.query("segundaRevision", camposSegundaRevision, "idExamen = ?",  idExamen, null, null, null);
           if(curso.moveToFirst()){
                return curso.getString(5);
           }
           else {
               return "El estudiante no presento segunda revision";
           }

       }else{
           return null;
       }
   }
    public String eliminarSegundaRevision( String carnet, String numeva) {
        String[] id = {carnet,numeva};
        Cursor cursor = db.query("examenIndividual", camposExamenIndividual, "carnet = ? AND numEva=?",  id, null, null, null);
        if(cursor.moveToFirst()){

            String[] idExamen={cursor.getString(0)};
            int curso = db.delete("segundaRevision",  "idExamen = ?",  idExamen);
            if(curso > 0){
                return "Segunda revision eliminada";
            }
            else {
                return "El estudiante no presento segunda revision";
            }

        }else{
            return null;
        }
    }
    //---------------------------------------------METODO INSERTAR EXAMEN INDIVIDUAL -----------------------------------------------------------

    public String insertarExamenIndividual(ExamenIndividual exa){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues examen = new ContentValues();
        examen.put("numeva",exa.getNumeva());
        examen.put("carnet", exa.getCarnet());
        examen.put("nota", exa.getNota());
        contador=db.insert("examenIndividual", null, examen);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro  Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    // Metodo Consultar Examen Individual
    public ExamenIndividual consultarExamenIndividual( String carnet, String numeva) {
        String[] id = {carnet,numeva};
        Cursor cursor = db.query("examenIndividual", camposExamenIndividual, "carnet = ? AND numEva=?",  id, null, null, null);
        if(cursor.moveToFirst()){
            ExamenIndividual examen = new ExamenIndividual();
            examen.setNumeva(Integer.parseInt(cursor.getString(1)));
            examen.setCarnet(cursor.getString(2));
            examen.setNota(Float.parseFloat(cursor.getString(3)));
            return examen;
        }else{
            return null;
        }
    }
    //METODO ELIMINAR EXAMEN INDIVIDUAL
    public String eliminarExamenIndividual(String carnet, String numeva){
        String regAfectados="filas afectadas= ";
        int contador=0;
        String[] id = {carnet,numeva};
        Cursor cursor = db.query("examenIndividual", camposExamenIndividual, "carnet = ? AND numEva=?",  id, null, null, null);
        if(cursor.moveToFirst()){
            contador+= db.delete("examenIndividual", "carnet = ? AND numEva=?",  id);
        //  if (verificarIntegridad(escuela,3)) {
        //   contador+=db.delete("escuela", "codEscuela='"+escuela.getcodEscuela()+"'", null);
         }

        regAfectados+=contador;
        return regAfectados;
    }
//Metodo insertar Escuela
    public String insertar(Escuela escuela){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues escu = new ContentValues();
        escu.put("codEscuela", escuela.getcodEscuela());
        escu.put("nombre", escuela.getNombre());
        contador=db.insert("escuela", null, escu);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro  Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    //Metodo eliminar Escuela
    public String eliminar(Escuela escuela){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //Integridad
      //  if (verificarIntegridad(escuela,3)) {
         //   contador+=db.delete("escuela", "codEscuela='"+escuela.getcodEscuela()+"'", null);
       // }
        contador+=db.delete("escuela", "codEscuela='"+escuela.getcodEscuela()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }
    public String actualizar(Escuela escuela){
        if(verificarIntegridad(escuela, 15)){
            String[] id = {String.valueOf(escuela.getcodEscuela())};
            ContentValues u = new ContentValues();
                u.put("nombre",escuela.getNombre());
                db.update("escuela", u, "codEscuela = ?", id);
                return "Registro Actualizado Correctamente";
            }
        return "Algo salio mal"; }



    //Metodo consultar Escuela
    public Escuela consultarEscuela(String codEscuela) {
            String[] id = {codEscuela};
            Cursor cursor = db.query("escuela", camposEscuela, "codEscuela = ?",  id, null, null, null);
            if(cursor.moveToFirst()){
                Escuela escuela = new Escuela();
                escuela.setcodEscuela(Integer.parseInt(cursor.getString(0)));
                escuela.setNombre(cursor.getString(1));
                return escuela;
            }else{
                return null;
            }
        }

        //Métodos para la tabla Reservación
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

    //Métodos para la tabla SolicitudDeCambio.

    public String insertar(SolicitudDeCambio solicitudDeCambio){
        String regInsertados="¡Solicitud de cambio registrada!";
        long contador=0;
        if(verificarIntegridad(solicitudDeCambio, 11)){
        ContentValues solicitudDeCambio_ = new ContentValues();
        solicitudDeCambio_.put("idSolicitudCambio",solicitudDeCambio.getIdSolicitudCambio());
        solicitudDeCambio_.put("idExamen",solicitudDeCambio.getIdExamen());
        solicitudDeCambio_.put("idRazon",solicitudDeCambio.getIdRazon());
        solicitudDeCambio_.put("nuevaNota",solicitudDeCambio.getIdRazon());
        solicitudDeCambio_.put("estadoAprobado",solicitudDeCambio.getIdRazon());
        contador=db.insert("solicitudDeCambio", null,solicitudDeCambio_);}

        if(contador==-1 || contador==0){regInsertados= "Error al insertar el registro. Verifique la inserción, por favor";}

        return regInsertados;}

    public SolicitudDeCambio consultarSolicitudDeCambio(int idSolicitud){
        String[] id = {String.valueOf(idSolicitud)};
        Cursor cursor = db.query("solicitudDeCambio", camposSolicitudDeCambio, "idSolicitudCambio = ?", id, null, null, null);

        if(cursor.moveToFirst()){
            SolicitudDeCambio solicitudDeCambio= new SolicitudDeCambio();
            solicitudDeCambio.setIdSolicitudCambio(cursor.getInt(0));
            solicitudDeCambio.setIdExamen(cursor.getInt(1));
            solicitudDeCambio.setIdRazon(cursor.getInt(2));
            solicitudDeCambio.setNuevaNota(cursor.getFloat(3));
            solicitudDeCambio.setEstadoAprobado(Boolean.valueOf(cursor.getString(4)));
            return solicitudDeCambio;}else{return null;}}

    public String actualizar(SolicitudDeCambio solicitudDeCambio) {
        if(verificarIntegridad(solicitudDeCambio, 19)){
            String[] id = {String.valueOf(solicitudDeCambio.getIdSolicitudCambio())};
            ContentValues cv = new ContentValues();
            cv.put("idExamen",solicitudDeCambio.getIdExamen());
            cv.put("idRazon",solicitudDeCambio.getIdRazon());
            cv.put("nuevaNota",solicitudDeCambio.getNuevaNota());
            cv.put("estadoAprobado",solicitudDeCambio.isEstadoAprobado());
            db.update("solicitudDeCambio", cv, "idCiclo = ?", id);
            return "Registro actualizado correctamente";}
        else{return "La solicitud de cambio "+solicitudDeCambio.getIdSolicitudCambio()+" no existe";}}

    public String eliminar(SolicitudDeCambio solicitudDeCambio) {
        String regAfectados="Filas afectadas: ";
        int contador=0;
        contador+=db.delete("solicitudDeCambio", "idSolicitudCambio='"+solicitudDeCambio.getIdSolicitudCambio()+"'", null);
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

    //Métodos para la tabla Usuario.

    public Usuario consultarSesion() {
        String[] id = {String.valueOf(1)};
        Cursor c = db.query("usuario", camposUsuario, "sesion = ?", id, null, null, null);

        if(c.moveToFirst()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(c.getInt(0));
            usuario.setTipo(c.getString(1));
            usuario.setContrasena(c.getString(2));
            usuario.setNombre(c.getString(3));
            usuario.setCorreo(c.getString(4));
            usuario.setSesion(Boolean.valueOf(c.getString(5)));
            return usuario;}else{return null;}}

    public boolean insertar(Usuario usuario){
        long contador=0;
        ContentValues usuario_ = new ContentValues();
        usuario_.put("idUsuario",usuario.getIdUsuario());
        usuario_.put("tipo",usuario.getTipo());
        usuario_.put("contrasena",usuario.getContrasena());
        usuario_.put("nombre",usuario.getNombre());
        usuario_.put("correo",usuario.getCorreo());
        usuario_.put("sesion",usuario.isSesion());
        contador=db.insert("usuario", null,usuario_);
        if(contador==-1 || contador==0){return false;}
        else{return true;}}

    public Usuario consultarUsuario(int idUsuario){
        String[] id = {String.valueOf(idUsuario)};
        Cursor c = db.query("usuario", camposUsuario, "idUsuario = ?", id, null, null, null);

        if(c.moveToFirst()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(c.getInt(0));
            usuario.setTipo(c.getString(1));
            usuario.setContrasena(c.getString(2));
            usuario.setNombre(c.getString(3));
            usuario.setCorreo(c.getString(4));
            usuario.setSesion(Boolean.valueOf(c.getString(5)));
            return usuario;}else{return null;}}

    public Usuario consultarUsuario(String correo){
        String[] id = {correo};
        Cursor c = db.query("usuario", camposUsuario, "correo = ?", id, null, null, null);

        if(c.moveToFirst()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(c.getInt(0));
            usuario.setTipo(c.getString(1));
            usuario.setContrasena(c.getString(2));
            usuario.setNombre(c.getString(3));
            usuario.setCorreo(c.getString(4));
            usuario.setSesion(Boolean.valueOf(c.getString(5)));
            return usuario;}else{return null;}}

    public boolean actualizar(Usuario usuario) {
        if(verificarIntegridad(usuario, 8)){
            String[] id = {String.valueOf(usuario.getIdUsuario())};
            ContentValues cv = new ContentValues();
            cv.put("tipo",usuario.getTipo());
            cv.put("contrasena",usuario.getContrasena());
            cv.put("nombre",usuario.getNombre());
            cv.put("correo",usuario.getCorreo());
            cv.put("sesion", usuario.isSesion());
            db.update("usuario", cv, "idUsuario = ?", id);
            return true;}
        else{return false;}}

    public boolean eliminar(Usuario usuario) {
        int contador=0;
        contador+=db.delete("usuario", "idUsuario='"+usuario.getIdUsuario()+"'", null);//Implementar un trigger.
        if(contador==0){return false;}
        else{return true;}}

    //Métodos para la tabla Permiso.

    public boolean insertar(Permiso permiso){
        long contador=0;
        ContentValues permiso_ = new ContentValues();
        permiso_.put("idPermiso",permiso.getIdPermiso());
        permiso_.put("descripcion",permiso.getDescripcion());
        contador=db.insert("permiso", null,permiso_);
        if(contador==-1 || contador==0){return false;}
        else{return true;}}

    public Permiso consultarPermiso(int idPermiso){
        String[] id = {String.valueOf(idPermiso)};
        Cursor c = db.query("permiso", camposPermiso, "idPermiso = ?", id, null, null, null);

        if(c.moveToFirst()){
            Permiso permiso = new Permiso();
            permiso.setIdPermiso(c.getInt(0));
            permiso.setDescripcion(c.getString(1));
            return permiso;}else{return null;}}

    public boolean actualizar(Permiso permiso){
        if(verificarIntegridad(permiso, 12)){
            String[] id = {String.valueOf(permiso.getIdPermiso())};
            ContentValues cv = new ContentValues();
            cv.put("descripcion",permiso.getDescripcion());
            db.update("permiso", cv, "idPermiso = ?", id);
            return true;}
        else{return false;}}

    public boolean eliminar(Permiso permiso){
        int contador=0;
        contador+=db.delete("permiso", "idPermiso='"+permiso.getIdPermiso()+"'", null);//Implementar un trigger.
        if(contador==0){return false;}
        else{return true;}}

    //Métodos para la tabla AccesoUsuario.

    public boolean insertar(AccesoUsuario accesoUsuario){
        if(verificarIntegridad(accesoUsuario, 10)){
            long contador=0;
            ContentValues accesoUsuario_ = new ContentValues();
            accesoUsuario_.put("idAcceso",accesoUsuario.getIdAcceso());
            accesoUsuario_.put("idUsuario",accesoUsuario.getIdUsuario());
            accesoUsuario_.put("idPermiso",accesoUsuario.getIdPermiso());
            contador=db.insert("AccesoUsuario", null,accesoUsuario_);
            if(contador==-1 || contador==0){return false;}
            else{return true;}}
        else{return false;}}

    public boolean eliminar(AccesoUsuario accesoUsuario) {
        int contador=0;
        contador+=db.delete("accesoUsuario", "idUsuario='"+accesoUsuario.getIdUsuario()+"' and idPermiso='"+accesoUsuario.getIdPermiso()+"'", null);
        if(contador==0){return false;}
        else{return true;}}

    //Métodos para la tabla Emite.

    public boolean insertar(Emite emite){
        if(verificarIntegridad(emite, 13)){
        long contador=0;
        ContentValues emite_ = new ContentValues();
        emite_.put("idPermiso",emite.getIdDocente());
        emite_.put("idSolicitud",emite.getIdSolicitud());
        contador=db.insert("emite", null,emite_);
        if(contador==-1 || contador==0){return false;}
        else{return true;}}
        else{return false;}}

    public boolean eliminar(Emite emite){
        int contador=0;
        contador+=db.delete("emite", "idDocente='"+emite.getIdDocente()+"' and idSolicitud='"+emite.getIdSolicitud()+"'", null);
        if(contador==0){return false;}
        else{return true;}}

    //Métodos para la tabla Posee.

    public boolean insertar(Posee posee){
        if(verificarIntegridad(posee, 9)){
            long contador=0;
            ContentValues posee_ = new ContentValues();
            posee_.put("idPermiso",posee.getIdDocente());
            posee_.put("idSolicitud",posee.getIdCargo());
            contador=db.insert("emite", null,posee_);
            if(contador==-1 || contador==0){return false;}
            else{return true;}}
        else{return false;}}

    public boolean eliminar(Posee posee){
        int contador=0;
        contador+=db.delete("emite", "idDocente='"+posee.getIdDocente()+"' and idSolicitud='"+posee.getIdCargo()+"'", null);
        if(contador==0){return false;}
        else{return true;}}


    //Metodos de la tabla Evaluacion

    public String insertar(Evaluacion evaluacion){
        String regInsertados="¡Evaluacion registrada!";
        long contador=0;
        ContentValues evaluacion_ = new ContentValues();
        evaluacion_.put("numEva", evaluacion.getNumEva());
        evaluacion_.put("idDocente", evaluacion.getIdDocente());
        evaluacion_.put("alumnosEvaluados", evaluacion.getAlumnosEvaluados());
        evaluacion_.put("codMateria", evaluacion.getCodMateria());
        evaluacion_.put("tipo", evaluacion.getTipo());
        evaluacion_.put("fechaRealizacion", String.valueOf(evaluacion.getFechaRealizacion()));
        evaluacion_.put("fechaPublicacion", String.valueOf(evaluacion.getFechaPublicacion()));
        contador=db.insert("evaluacion", null,evaluacion_);

        if(contador==-1 || contador==0){
            regInsertados= "Error al insertar el nuevo registro. Verifique nuevamente";
        }
        else{
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public Evaluacion consultarEvaluacion(int numEva){
        String[] id = {String.valueOf(numEva)};
        Cursor cursor = db.query("evaluacion", camposEvaluacion, "numEva = ?", id, null, null, null);

        if(cursor.moveToFirst()){
            Evaluacion evaluacion = new Evaluacion();
            evaluacion.setNumEva(cursor.getInt(0));
            evaluacion.setIdDocente(cursor.getInt(1));
            evaluacion.setAlumnosEvaluados(cursor.getInt(2));
            evaluacion.setCodMateria((cursor.getString(3)));
            evaluacion.setTipo((cursor.getString(4)));
            evaluacion.setFechaRealizacion(Date.valueOf(cursor.getString(5)));
            evaluacion.setFechaPublicacion(Date.valueOf(cursor.getString(6)));
            return evaluacion;}else{
            return null;}}

    public String actualizar(Evaluacion evaluacion) {
        if(verificarIntegridad(evaluacion, 18)){
            String[] id = {String.valueOf(evaluacion.getNumEva())};
            ContentValues cv = new ContentValues();
            cv.put("idDocente",evaluacion.getIdDocente());
            cv.put("alumnosEvaluados",evaluacion.getAlumnosEvaluados());
            cv.put("codMateria",evaluacion.getCodMateria());
            cv.put("tipo",evaluacion.getTipo());
            cv.put("fechaRealizacion",String.valueOf(evaluacion.getFechaRealizacion()));
            cv.put("fechaPublicacion",String.valueOf(evaluacion.getFechaPublicacion()));
            db.update("evaluacion", cv, "numEva = ?", id);
            return "Registro actualizado correctamente";}
        else{return "La evaluacion "+evaluacion.getNumEva()+" no existe";}}

    public String eliminar(Evaluacion evaluacion) {
        String regAfectados="Filas afectadas: ";
        int contador=0;
        contador+=db.delete("evaluacion", "numEva='"+evaluacion.getNumEva()+"'", null);
        regAfectados+=contador;
        return regAfectados;}


// Metodos de la tabla Revision
    public String insertar(Revision revision){
        String regInsertados="¡Revision registrada con exito!";
        long contador=0;
        ContentValues revision_ = new ContentValues();
        revision_.put("numRev1", revision.getNumRev1());
        revision_.put("idExamen", revision.getIdExamen());
        revision_.put("nuevaNota", revision.getNuevaNota());
        revision_.put("observ", revision.getObserv());
        revision_.put("asistio", revision.isAsistio());
        contador= db.insert("revision", null, revision_);

    if(contador==-1 || contador==0){
        regInsertados= "Error al insertar el nuevo registro. Verifique nuevamente";
    }
    else{
        regInsertados=regInsertados+contador;
    }
    return regInsertados;
    }

    public Revision consultarRevision(int numRev1){
        String[] id = {String.valueOf(numRev1)};
        Cursor cursor = db.query("revision", camposRevision, "numRev1 = ?", id, null, null, null);

        if(cursor.moveToFirst()){
            Revision revision = new Revision();
            revision.setNumRev1(cursor.getInt(0));
            revision.setIdExamen(cursor.getInt(1));
            revision.setNuevaNota(cursor.getFloat(2));
            revision.setObserv((cursor.getString(3)));
            revision.setAsistio(Boolean.valueOf(cursor.getString(5)));
            return revision;}else{
            return null;}}

    public String actualizar(Revision revision) {
        if(verificarIntegridad(revision, 19)){
            String[] id = {String.valueOf(revision.getNumRev1())};
            ContentValues cv = new ContentValues();
            cv.put("idExamen",revision.getIdExamen());
            cv.put("nuevaNota",revision.getNuevaNota());
            cv.put("observ",revision.getObserv());
            cv.put("asistio",revision.isAsistio());
            db.update("revision", cv, "numRev1 = ?", id);
            return "Registro actualizado correctamente";}
        else{return "La revision "+revision.getNumRev1()+" no existe";}}

    public String eliminar(Revision revision) {
        String regAfectados="Filas afectadas: ";
        int contador=0;
        contador+=db.delete("revision", "numRev1='"+revision.getNumRev1()+"'", null);
        regAfectados+=contador;
        return regAfectados;}


    // Metodos de la tabla Solicitud de Impresiones
    public String insertarSolicitudDeImpresiones(SolicitudDeImpresiones solicitud){
        String regInsertados="¡Solicitud de impresion registrada con exito!";
        long contador=0;
        ContentValues sol = new ContentValues();
        sol.put("idSolicitud", solicitud.getIdSolicitud());
        sol.put("numPaginas", solicitud.getNumPaginas());
        sol.put("estadoAprobado", solicitud.isEstadoAprobado());
        contador= db.insert("solicitud", null, sol);

        if(contador==-1 || contador==0){
            regInsertados= "Error al insertar el nuevo registro. Verifique nuevamente";
        }
        else{
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public SolicitudDeImpresiones consultarSolicitudDeImpresiones(int idSolicitud){
        String[] id = {String.valueOf(idSolicitud)};
        Cursor cursor = db.query("solicitud", camposSolicitudDeImpresiones, "idSolicitud = ?", id, null, null, null);

        if(cursor.moveToFirst()){
            SolicitudDeImpresiones solicitud = new SolicitudDeImpresiones();
            solicitud.setIdSolicitud(cursor.getInt(0));
            solicitud.setNumPaginas(cursor.getInt(1));
            solicitud.setEstadoAprobado(Boolean.valueOf(cursor.getString(2)));
            return solicitud;}else{
            return null;}}

    public String actualizarSolicitudDeImpresiones(SolicitudDeImpresiones solicitud) {
        if(verificarIntegridad(solicitud, 20)){
            String[] id = {String.valueOf(solicitud.getIdSolicitud())};
            ContentValues cv = new ContentValues();
            cv.put("numPaginas",solicitud.getNumPaginas());
            cv.put("estadoAprobado",solicitud.isEstadoAprobado());
            db.update("solicitud", cv, "idSolicitud = ?", id);
            return "Registro actualizado correctamente";
        }
        else{
            return "La solicitud de impresion "+solicitud.getIdSolicitud()+" no existe";}
    }

    public String eliminarSolicitudDeImpresiones(SolicitudDeImpresiones solicitud) {
        String regAfectados="Filas afectadas: ";
        int contador=0;
        contador+=db.delete("solicitud", "idSolicitud='"+solicitud.getIdSolicitud()+"'", null);
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
                db.execSQL("drop index if exists accesoUsuario2_fk;");
                db.execSQL("drop index if exists accesoUsuario_fk;");
                db.execSQL("drop table if exists accesoUsuario;");
                db.execSQL("drop index if exists es_5_fk;");
                db.execSQL("drop table if exists administrador;");
                db.execSQL("drop index if exists admite2_fk;");
                db.execSQL("drop index if exists admite_fk;");
                db.execSQL("drop table if exists admite;");
                db.execSQL("drop index if exists admite_3_fk;");
                db.execSQL("drop index if exists admite_2_fk;");
                db.execSQL("drop table if exists admite_2;");
                db.execSQL("drop table if exists cargo;");
                db.execSQL("drop index if exists imparte3_fk;");
                db.execSQL("drop index if exists imparte2_fk;");
                db.execSQL("drop index if exists imparte_fk;");
                db.execSQL("drop table if exists catedra;");
                db.execSQL("drop table if exists ciclo;");
                db.execSQL("drop index if exists es_fk;");
                db.execSQL("drop table if exists docente;");
                db.execSQL("drop index if exists emite2_fk;");
                db.execSQL("drop index if exists emite_fk;");
                db.execSQL("drop table if exists emite;");
                db.execSQL("drop index if exists emite_3_fk;");
                db.execSQL("drop index if exists emite_2_fk;");
                db.execSQL("drop table if exists emite_2;");
                db.execSQL("drop index if exists es_3_fk;");
                db.execSQL("drop table if exists encargadoDeImpresiones;");
                db.execSQL("drop table if exists escuela;");
                db.execSQL("drop index if exists es_2_fk;");
                db.execSQL("drop table if exists estudiante;");
                db.execSQL("drop index if exists es_admitida_por2_fk;");
                db.execSQL("drop index if exists es_admitida_por_fk;");
                db.execSQL("drop table if exists es_admitida_por;");
                db.execSQL("drop index if exists tiene_3_fk;");
                db.execSQL("drop index if exists crea_fk;");
                db.execSQL("drop table if exists evaluacion;");
                db.execSQL("drop index if exists tiene_4_fk;");
                db.execSQL("drop index if exists realiza_fk;");
                db.execSQL("drop table if exists examenIndividual;");
                db.execSQL("drop index if exists es_6_fk;");
                db.execSQL("drop table if exists instructor;");
                db.execSQL("drop table if exists local;");
                db.execSQL("drop index if exists tiene_2_fk;");
                db.execSQL("drop table if exists materia;");
                db.execSQL("drop index if exists corresponde_a_fk;");
                db.execSQL("drop index if exists realiza_2_fk;");
                db.execSQL("drop index if exists tiene_6_fk;");
                db.execSQL("drop table if exists matricula;");
                db.execSQL("drop table if exists permiso;");
                db.execSQL("drop index if exists posee2_fk;");
                db.execSQL("drop index if exists posee_fk;");
                db.execSQL("drop table if exists posee;");
                db.execSQL("drop table if exists razon;");
                db.execSQL("drop index if exists recibe2_fk;");
                db.execSQL("drop index if exists recibe_fk;");
                db.execSQL("drop table if exists recibe;");
                db.execSQL("drop index if exists tiene_fk;");
                db.execSQL("drop index if exists hace_fk;");
                db.execSQL("drop table if exists reservacion;");
                db.execSQL("drop index if exists tiene_5_fk;");
                db.execSQL("drop table if exists revision;");
                db.execSQL("drop index if exists se_presenta_fk;");
                db.execSQL("drop index if exists asiste_fk;");
                db.execSQL("drop index if exists se_presenta_2_fk;");
                db.execSQL("drop index if exists tiene_7_fk;");
                db.execSQL("drop table if exists segundaRevision;");
                db.execSQL("drop index if exists tiene_9_fk;");
                db.execSQL("drop index if exists corresponde_fk;");
                db.execSQL("drop table if exists solicitudDeCambio;");
                db.execSQL("drop index if exists tiene_8_fk;");
                db.execSQL("drop table if exists solicitudDeDiferido;");
                db.execSQL("drop table if exists solicitudDeImpresiones;");
                db.execSQL("drop table if exists solicitudDeRevision;");
                db.execSQL("drop table if exists usuario;");
                db.execSQL("CREATE TABLE permiso(idPermiso INTEGER NOT NULL PRIMARY KEY,descipcion text);");
                db.execSQL("CREATE TABLE usuario(idUsuario INTEGER NOT NULL PRIMARY KEY,tipo VARCHAR(10),contrasena VARCHAR(30),nombre VARCHAR(75),correo VARCHAR(75),sesion boolean);");
                db.execSQL("CREATE TABLE accesoUsuario(idUsuario integer not null,idPermiso integer not null,constraint pk_accesoUsuario primary key (idPermiso, idUsuario),foreign key (idPermiso) references permiso (idPermiso),foreign key (idUsuario) references usuario (idUsuario));");
                db.execSQL("create index accesoUsuario_fk on accesoUsuario (idPermiso ASC);");
                db.execSQL("create index accesoUsuario2_fk on accesoUsuario (idUsuario ASC);");
                db.execSQL("CREATE TABLE administrador(idAdministrador INTEGER NOT NULL PRIMARY KEY,idUsuario integer,foreign key (idUsuario) references usuario (idUsuario));");
                db.execSQL("create index es_5_fk on administrador (idUsuario asc);");
                db.execSQL("CREATE TABLE solicitudDeImpresiones(idSolicitud INTEGER NOT NULL PRIMARY KEY,numPaginas INTEGER,estadoAprobado boolean);");
                db.execSQL("CREATE TABLE encargadoDeImpresiones(idEncargado INTEGER NOT NULL PRIMARY KEY,idUsuario integer,foreign key (idUsuario) references usuario (idUsuario));");
                db.execSQL("create index es_3_fk on encargadoDeImpresiones (idUsuario asc);");
                db.execSQL("create table admite (idEncargado integer not null,idSolicitud integer not null,constraint pk_admite primary key (idEncargado, idSolicitud),foreign key (idEncargado) references encargadoDeImpresiones (idEncargado), foreign key (idSolicitud) references solicitudDeImpresiones (idSolicitud));");
                db.execSQL("create index admite_fk on admite (idEncargado asc);");
                db.execSQL("create index admite2_fk on admite (idSolicitud asc);");
                db.execSQL("CREATE TABLE estudiante(carnet varchar(7) NOT NULL PRIMARY KEY,idUsuario integer,foreign key (idUsuario) references usuario (idUsuario));");
                db.execSQL("create index es_2_fk on estudiante (idUsuario asc);");
                db.execSQL("CREATE TABLE docente(idDocente INTEGER NOT NULL PRIMARY KEY,idUsuario integer,foreign key (idUsuario) references usuario (idUsuario));");
                db.execSQL("create index es_fk on docente (idUsuario asc);");
                db.execSQL("CREATE TABLE escuela(codEscuela INTEGER NOT NULL PRIMARY KEY,nombre VARCHAR(75));");
                db.execSQL("CREATE TABLE materia(codMateria VARCHAR(6) NOT NULL PRIMARY KEY,codEscuela varchar(10),nombre VARCHAR(75),foreign key (codEscuela) references escuela (codEscuela));");
                db.execSQL("create index tiene_2_fk on materia (codEscuela asc);");
                db.execSQL("CREATE TABLE evaluacion(numEva INTEGER NOT NULL PRIMARY KEY,idDocente integer,codMateria varchar(6),tipo VARCHAR(10),alumnosEvaluados integer,fechaRealización date,fechaPublicacion date,foreign key (idDocente) references docente (idDocente),foreign key (codMateria) references materia (codMateria));");
                db.execSQL("create index crea_fk on evaluacion (idDocente asc);");
                db.execSQL("create index tiene_3_fk on evaluacion (codMateria asc);");
                db.execSQL("CREATE TABLE examenIndividual(idExamen INTEGER NOT NULL PRIMARY KEY,numEva integer,carnet varchar(7),nota float,foreign key (carnet) references estudiante (carnet),foreign key (numEva) references evaluacion (numEva));");
                db.execSQL("create index realiza_fk on examenIndividual (carnet asc);");
                db.execSQL("create index tiene_4_fk on examenIndividual (numEva asc);");
                db.execSQL("create table razon(idRazon integer not null primary key,nombre varchar(75),descripcion text);");
                db.execSQL("create table solicitudDeCambio(idSolicitudCambio integer not null primary key,idExamen integer,idRazon integer,nuevaNota float,estadoAprobado boolean,foreign key (idRazon) references razon (idrazon),foreign key (idExamen) references examenIndividual (idExamen));");
                db.execSQL("create index corresponde_fk on solicitudDeCambio (idRazon asc);");
                db.execSQL("create index tiene_9_fk on solicitudDeCambio (idExamen asc);");
                db.execSQL("CREATE TABLE admite_2(idAdministrador INTEGER NOT NULL,idSolicitudCambio integer not null,constraint pk_admite_2 primary key (idAdministrador,idSolicitudCambio),foreign key (idAdministrador) references administrador (idAdministrador),foreign key (idSolicitudCambio) references solicitudDeCambio (idSolicitudCambio));");
                db.execSQL("create index admite_2_fk on admite_2 (idAdministrador asc);");
                db.execSQL("create index admite_3_fk on admite_2 (idSolicitudCambio asc);");
                db.execSQL("CREATE TABLE cargo(idCargo INTEGER NOT NULL PRIMARY KEY,nombreCargo VARCHAR(30));");
                db.execSQL("CREATE TABLE ciclo(idCiclo INTEGER NOT NULL PRIMARY KEY,anio INTEGER,numCiclo INTEGER);");
                db.execSQL("CREATE TABLE catedra(idCatedra INTEGER NOT NULL primary key,idCiclo,codMateria varchar(6),idDocente INTEGER,foreign key (idDocente) references docente (idDocente),foreign key (codMateria) references materia (codMateria),foreign key (idCiclo) references ciclo (idCiclo));");
                db.execSQL("create index imparte_fk on catedra (idDocente asc);");
                db.execSQL("create index imparte2_fk on catedra (codMateria asc);");
                db.execSQL("create index imparte3_fk on catedra (idCiclo asc);");
                db.execSQL("CREATE TABLE emite(idDocente INTEGER NOT NULL,idSolicitud NOT NULL,constraint pk_emite primary key (idDocente,idSolicitud),foreign key (idDocente) references docente (idDocente),foreign key (idSolicitud) references solicitudDeImpresiones (idSolicitud));");
                db.execSQL("create index emite_fk on emite (idDocente asc);");
                db.execSQL("create index emite2_fk on emite (idSolicitud asc);");
                db.execSQL("create table instructor(idInstructor integer not null primary key,carnet varchar(7),foreign key (carnet) references estudiante (carnet));");
                db.execSQL("create index es_6_fk on instructor (carnet asc);");
                db.execSQL("CREATE TABLE emite_2(idInstructor INTEGER NOT NULL,idSolicitud NOT NULL,constraint pk_emite_2 primary key (idInstructor,idSolicitud),foreign key (idInstructor) references instructor (idInstructor),foreign key (idSolicitud) references solicitudDeImpresiones (idSolicitud));");
                db.execSQL("create index emite_2_fk on emite_2 (idInstructor asc);");
                db.execSQL("create index emite_3_fk on emite_2 (idSolicitud asc);");
                db.execSQL("create table solicitudDeDiferido(idSolicitudDiferido integer not null primary key,idExamen integer,estadoAprobado boolean,foreign key (idExamen) references examenIndividual (idExamen));");
                db.execSQL("create index tiene_8_fk on solicitudDeDiferido (idExamen asc);");
                db.execSQL("CREATE TABLE es_admitida_por(idSolicitudDiferido INTEGER NOT NULL,idAdministrador integer not null,constraint pk_es_admitida_por primary key (idSolicitudDiferido,idAdministrador),foreign key (idSolicitudDiferido) references solicitudDeDiferido (idSolicitudDiferido),foreign key (idAdministrador) references administrador (idAdministrador));");
                db.execSQL("create index es_admitida_por_fk on es_admitida_por (idSolicitudDiferido asc);");
                db.execSQL("create index es_admitida_por2_fk on es_admitida_por (idAdministrador asc);");
                db.execSQL("CREATE TABLE local(idLocal INTEGER NOT NULL PRIMARY KEY,nombre VARCHAR(10));");
                db.execSQL("CREATE TABLE matricula(idMatricula INTEGER NOT NULL PRIMARY KEY,carnet varchar(7),codMateria varchar(6),idCiclo integer,numMatricula integer,foreign key (idCiclo) references ciclo (idCiclo),foreign key (carnet) references estudiante (carnet),foreign key (codMateria) references materia (codMateria));");
                db.execSQL("create index tiene_6_fk on matricula (codMateria asc);");
                db.execSQL("create index realiza_2_fk on matricula (carnet asc);");
                db.execSQL("create index corresponde_a_fk on matricula (idCiclo asc);");
                db.execSQL("CREATE TABLE posee(idDocente INTEGER NOT NULL,idCargo integer NOT NULL,constraint pk_posee primary key (idDocente,idCargo),foreign key (idDocente) references docente (idDocente),foreign key (idCargo) references cargo (idCargo));");
                db.execSQL("create index posee_fk on posee (idDocente asc);");
                db.execSQL("create index posee2_fk on posee (idCargo asc);");
                db.execSQL("create table solicitudDeRevision(idSolicitudRevision integer not null primary key,numRev integer,idRevision integer);");
                db.execSQL("CREATE TABLE recibe(idDocente INTEGER NOT NULL,idSolicitudRevision NOT NULL,constraint pk_recibe primary key (idDocente,idSolicitudRevision),foreign key (idDocente) references docente (idDocente),foreign key (idSolicitudRevision) references solicitudDeRevision (idSolicitudRevision));");
                db.execSQL("create index recibe_fk on recibe (idDocente asc);");
                db.execSQL("create index recibe2_fk on recibe (idSolicitudRevision asc);");
                db.execSQL("CREATE TABLE reservacion(idReservacion INTEGER NOT NULL PRIMARY KEY,idLocal integer,idDocente integer,horaInicio VARCHAR(5),fecha DATE,horaFin VARCHAR(5),foreign key (idDocente) references docente (idDocente),foreign key (idLocal) references local (idLocal));");
                db.execSQL("create index hace_fk on reservacion (idDocente asc);");
                db.execSQL("create index tiene_fk on reservacion (idLocal asc);");
                db.execSQL("CREATE TABLE revision(numRev1 INTEGER NOT NULL PRIMARY KEY,idExamen integer,nuevaNota float,observ text,asistio boolean,foreign key (idExamen) references examenIndividual (idExamen));");
                db.execSQL("create index tiene_5_fk on revision (idExamen asc);");
                db.execSQL("CREATE TABLE segundaRevision(numRev2 INTEGER NOT NULL PRIMARY KEY,idExamen integer,respSociedadEstud varchar(7),idDocente integer,idOtroDocente integer,notaDefinitiva float,foreign key (idDocente) references docente (idDocente),foreign key (respSociedadEstud) references estudiante (carnet),foreign key (idOtroDocente) references docente (idDocente),foreign key (idExamen) references examenIndividual (idExamen));");
                db.execSQL("create index tiene_7_fk on segundaRevision (idExamen asc);");
                db.execSQL("create index se_presenta_2_fk on segundaRevision (idOtroDocente asc);");
                db.execSQL("create index asiste_fk on segundaRevision (idDocente asc);");
                db.execSQL("create index se_presenta_fk on segundaRevision (respSociedadEstud asc);");
                db.execSQL("create trigger validarInscripcion before insert on matricula for each row begin select case when ((select count(carnet) from matricula where carnet=new.carnet and idCiclo=new.idCiclo)=5) then raise(abort, 'No puedes inscribir más de cinco materias') END; END;");
                db.execSQL("create trigger validarCiclo before insert on ciclo for each row begin select case when ((select count(anio) from ciclo where anio=new.anio)=2) then raise(abort, 'Un año solo puede tener dos ciclos') END; END;");
                db.execSQL("create trigger updateNotaSR after insert on segundaRevision begin update examenIndividual set nota=new.notaDefinitiva where examenIndividual.idExamen=new.idExamen; END;");
                db.execSQL("create trigger updateNotaR after update on solicitudDeCambio when new.estadoAprobado=1 begin update examenIndividual set nota=new.nuevaNota where examenIndividual.idExamen=new.idExamen; END;");
                db.execSQL("create trigger updateSolicitudI after update on solicitudDeImpresiones when new.numPaginas<>old.numPaginas begin update solicitudDeImpresiones set estadoAprobado=0 where solicitudDeImpresiones.idSolicitud=new.idSolicitud; END;");
                db.execSQL("insert into usuario values (1,'superusuario','1234','Súper Usuario','superuser@gmail.com',0);");
                db.execSQL("insert into usuario values (2,'docente','2345','César Augusto González Rodríguez','cesar.gonzalez@gmail.com',0);");
                db.execSQL("insert into docente values (1,2);");
                db.execSQL("insert into usuario values (3,'admin','3456','Verónica Rocío Almogabar Santos','veronica.almogabar@gmail.com',0);");
                db.execSQL("insert into administrador values (1,3);");
                db.execSQL("insert into usuario values (4,'estudiante','4567','Daniela Katherinne Suarique Ávila','daniela.suarique@gmail.com',0);");
                db.execSQL("insert into estudiante values ('SA18043',4);");
                db.execSQL("insert into usuario values (5,'instructor','5678','Oscar Ricardo Ovalle Solano','oscar.ovalle@gmail.com',0);");
                db.execSQL("insert into instructor values (1,5);");
                db.execSQL("insert into usuario values (6,'encargado','6789','Jorge Esteban Coral Burbano','jorge.coral@gmail.com',0);");
                db.execSQL("insert into encargadoDeImpresiones values (1,6);");
                db.execSQL("insert into permiso values (1,'Manipular tabla segundaRevision');");
                db.execSQL("insert into permiso values (2,'Manipular tabla examenIndividual');");
                db.execSQL("insert into permiso values (3,'Manipular tabla evaluacion');");
                db.execSQL("insert into permiso values (4,'Manipular tabla reservacion');");
                db.execSQL("insert into permiso values (5,'Manipular tabla revision');");
                db.execSQL("insert into permiso values (6,'Manipular tabla matricula');");
                db.execSQL("insert into permiso values (7,'Realizar la solicitud de impresiones');");
                db.execSQL("insert into permiso values (8,'Aceptar o denegar solicitudes de impresiones');");
                db.execSQL("insert into permiso values (9,'Aceptar o denegar solicitudes de cambio de notas');");
                db.execSQL("insert into permiso values (10,'Realizar solicitud de cambio de notas');");
                db.execSQL("insert into accesoUsuario values (2,2);");
                db.execSQL("insert into accesoUsuario values (2,3);");
                db.execSQL("insert into accesoUsuario values (2,4);");
                db.execSQL("insert into accesoUsuario values (2,5);");
                db.execSQL("insert into accesoUsuario values (2,7);");
                db.execSQL("insert into accesoUsuario values (2,10);");
                db.execSQL("insert into accesoUsuario values (3,9);");
                db.execSQL("insert into accesoUsuario values (3,1);");
                db.execSQL("insert into accesoUsuario values (4,6);");
                db.execSQL("insert into accesoUsuario values (5,7);");
                db.execSQL("insert into accesoUsuario values (6,8);");
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

            //Verificación de integridad de CN18006 (del caso 1 al caso 13).
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
                    //Se encontró el ciclo.
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
                    //Se encontró la materia.
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
            case 8:{
                //Verificar que exista el usuario para poder actualizarlo.
                Usuario usuario = (Usuario)dato;
                String[] id = {String.valueOf(usuario.getIdUsuario())};
                abrir();
                Cursor c = db.query("usuario", null, "idUsuario = ?", id, null, null,null);
                if(c.moveToFirst()){
                    //Se encontró el usuario.
                    return true;}
                return false;}
            case 9:{
                //Verificar que al registrar una posesión de cargo para el docente, existan el docente y el cargo.
                Posee posee = (Posee)dato;
                String[] id1 = {String.valueOf(posee.getIdDocente())};
                String[] id2 = {String.valueOf(posee.getIdCargo())};
                abrir();
                Cursor c1 = db.query("docente", null, "idDocente = ?", id1,null, null, null);
                Cursor c2 = db.query("cargo", null, "idCargo = ?", id2, null,null, null);
                if(c1.moveToFirst() && c2.moveToFirst()){
                    //Se encontraron datos.
                    return true;}
                return false;}
            case 10:{
                //Verificar que al registrar un acceso de usuario, existan el usuario y el permiso.
                AccesoUsuario accesoUsuario = (AccesoUsuario)dato;
                String[] id1 = {String.valueOf(accesoUsuario.getIdUsuario())};
                String[] id2 = {String.valueOf(accesoUsuario.getIdPermiso())};
                abrir();
                Cursor c1 = db.query("usuario", null, "idUsuario = ?", id1,null, null, null);
                Cursor c2 = db.query("permiso", null, "idPermiso = ?", id2, null,null, null);
                if(c1.moveToFirst() && c2.moveToFirst()){
                    //Se encontraron datos.
                    return true;}
                return false;}
            case 11:{
                //Verificar que al registrar una solicitud de cambio de nota, existan el examen y la razón.
                SolicitudDeCambio solicitudDeCambio= (SolicitudDeCambio)dato;
                String[] id1 = {String.valueOf(solicitudDeCambio.getIdExamen())};
                String[] id2 = {String.valueOf(solicitudDeCambio.getIdRazon())};
                abrir();
                Cursor c1 = db.query("examenIndividual", null, "idExamen = ?", id1,null, null, null);
                Cursor c2 = db.query("razon", null, "idRazon = ?", id2, null,null, null);
                if(c1.moveToFirst() && c2.moveToFirst()){
                    //Se encontraron datos.
                    return true;}
                return false;}
            case 12:{
                //Verificar que exista el usuario para poder actualizarlo.
                Permiso permiso = (Permiso)dato;
                String[] id = {String.valueOf(permiso.getIdPermiso())};
                abrir();
                Cursor c = db.query("permiso", null, "idPermiso = ?", id, null, null,null);
                if(c.moveToFirst()){
                    //Se encontró el usuario.
                    return true;}
                return false;}
            case 13:{
                //Verificar que al registrar un acceso de usuario, existan el usuario y el permiso.
                Emite emite = (Emite)dato;
                String[] id1 = {String.valueOf(emite.getIdDocente())};
                String[] id2 = {String.valueOf(emite.getIdSolicitud())};
                abrir();
                Cursor c1 = db.query("docente", null, "idDocente = ?", id1,null, null, null);
                Cursor c2 = db.query("solicitudDeImpresiones", null, "idSolicitud = ?", id2, null,null, null);
                if(c1.moveToFirst() && c2.moveToFirst()){
                    //Se encontraron datos.
                    return true;}
                return false;}
            case 14:{
                //Verificar que exista el estudiante para poder actualizarlo.
                Estudiante estudiante = (Estudiante) dato;
                String[] id = {String.valueOf(estudiante.getCarnet())};
                abrir();
                Cursor c = db.query("estudiante", null, "carnet = ?", id, null, null,null);
                if(c.moveToFirst()){
                    //Se encontró el usuario.
                    return true;}
                return false;}
            case 15:{
                //Verificar que exista la escuela para poder actualizarla.
                Escuela escuela = (Escuela) dato;
                String[] id = {String.valueOf(escuela.getcodEscuela())};
                abrir();
                Cursor c = db.query("escuela", null, "codEscuela = ?", id, null, null,null);
                if(c.moveToFirst()){
                    //Se encontró el usuario.
                    return true;}
                return false;}
            case 16:{
                //Verificar que exista el docente para poder actualizarlo.
                Docente docente = (Docente) dato;
                String[] id = {String.valueOf(docente.getIdDocente())};
                abrir();
                Cursor c = db.query("docente", null, "idDocente = ?", id, null, null,null);
                if(c.moveToFirst()){
                    //Se encontró el usuario.
                    return true;}
                return false;}
            case 17:{
                //Verificar que exista el docente para poder actualizarlo.
               EncargadoDeImpresiones encargado = (EncargadoDeImpresiones) dato;
                String[] id = {String.valueOf(encargado.getIdEncargadoDeImpresiones())};
                abrir();
                Cursor c = db.query("encargadoDeImpresiones", null, "idEncargado = ?", id, null, null,null);
                if(c.moveToFirst()){
                    //Se encontró el usuario.
                    return true;}
                return false;}
            case 18:{
                //Verificar que exista la evaluacion para actualizarla.
                Evaluacion evaluacion = (Evaluacion) dato;
                String[] id = {String.valueOf(evaluacion.getNumEva())};
                abrir();
                Cursor c = db.query("evaluacion", null, "numEva = ?", id, null, null,null);
                if(c.moveToFirst()){
                    //Se encontró la evaluacion.
                    return true;}
                return false;}
            case 19:{
                //Verificar que exista la revision para actualizarla.
                Revision revision = (Revision) dato;
                String[] id = {String.valueOf(revision.getNumRev1())};
                abrir();
                Cursor c = db.query("revision", null, "numRev1 = ?", id, null, null,null);
                if(c.moveToFirst()){
                    //Se encontró la revision.
                    return true;}
                return false;}
            case 20:{
                //Verificar que exista la solicitud de impresion para actualizarla.
                SolicitudDeImpresiones solicitud = (SolicitudDeImpresiones) dato;
                String[] id = {String.valueOf(solicitud.getIdSolicitud())};
                abrir();
                Cursor c = db.query("solicitud", null, "idSolicitud = ?", id, null, null,null);
                if(c.moveToFirst()){
                    //Se encontró la solicitud de impresion.
                    return true;}
                return false;}
            default:
                return false;}}

    public String LlenarBDGpo16(){
        abrir();
        cerrar();
        return "Guardó correctamente";}
    public  String actualizarMatr(Matricula matricula) {
        if(verificarIntegridad(matricula, 1)){
            String[] id = {String.valueOf(matricula.getIdMatricula())};
            ContentValues cv = new ContentValues();
            cv.put("idMatricula",matricula.getIdMatricula());
            cv.put("carnet",matricula.getCarnet());
            cv.put("codMateria",matricula.getCodMateria());
            cv.put("idCiclo",matricula.getIdCiclo());
            cv.put("numMatricula",matricula.getNumMatricula());
            db.update("matricula", cv, "idMatricula = ?", id);
            return "Registro actualizado correctamente";}
        else{return "La matricula "+matricula.getIdMatricula()+" no existe";}}
    public String insertarMatr(Matricula matricula){
        String regInsertados="¡Matricula registrada!";
        long contador=0;
        ContentValues matricula_ = new ContentValues();
        matricula_.put("idMatricula",matricula.getIdMatricula());
        matricula_.put("carnet",matricula.getCarnet());
        matricula_.put("codMateria",matricula.getCodMateria());
        matricula_.put("idCiclo",matricula.getIdCiclo());
        matricula_.put("numMatricula",matricula.getNumMatricula());
        contador=db.insert("matricula", null, matricula_);

        if(contador==-1 || contador==0){regInsertados= "Error al insertar el registro. Verifique la inserción, por favor";}

        return regInsertados;}
    public String eliminarMatr(Matricula matricula) {
        String regAfectados="Filas afectadas: ";
        int contador=0;
        contador+=db.delete("matricula", "idMatricula='"+matricula.getIdMatricula()+"'", null);
        regAfectados+=contador;
        return regAfectados;}


    public Matricula consultarMatricula(String idMatricula){
        String[] id = {String.valueOf(idMatricula)};
        Cursor cursor = db.query("matricula", camposMatricula, "idMatricula = ?", id, null, null, null);

        if(cursor.moveToFirst()){
            Matricula matricula = new Matricula();
            matricula.setIdMatricula(cursor.getInt(0));
            matricula.setCarnet(cursor.getString(1));
            matricula.setCodMateria(cursor.getString(2));
            matricula.setIdCiclo(cursor.getInt(3));
            matricula.setNumMatricula(cursor.getInt(4));
            return matricula;}else{return null;}}
    public String insertar(Cargo cargo){
        String regInsertados="¡Cargo registrado!";
        long contador=0;
        ContentValues cargo_ = new ContentValues();
        cargo_.put("idCargo",cargo.getIdCargo());
        cargo_.put("NombreCargo",cargo.getNombreCargo());
        contador=db.insert("cargo", null,cargo_);

        if(contador==-1 || contador==0){regInsertados= "Error al insertar el registro. Verifique la inserción, por favor";}

        return regInsertados;}
    public Cargo consultarCargo(int idCargo){
        String[] id = {String.valueOf(idCargo)};
        Cursor cursor = db.query("cargo", camposCargo, "idCargo = ?", id, null, null, null);

        if(cursor.moveToFirst()){
            Cargo cargo = new Cargo();
            cargo.setIdCargo(cursor.getInt(0));
            cargo.setNombreCargo(cursor.getString(1));
            return cargo;}else{return null;}}
    public String eliminar(Cargo cargo) {
        String regAfectados="Filas afectadas: ";
        int contador=0;
        contador+=db.delete("cargo", "idCargo='"+cargo.getIdCargo()+"'", null);
        regAfectados+=contador;
        return regAfectados;}
    public String actualizar(Cargo cargo) {
        if(verificarIntegridad(cargo, 1)){
            String[] id = {String.valueOf(cargo.getIdCargo())};
            ContentValues cv = new ContentValues();
            cv.put("idCargo",cargo.getIdCargo());
            cv.put("nombreCargo",cargo.getNombreCargo());
            db.update("cargo", cv, "idCargo = ?", id);
            return "Registro actualizado correctamente";}
        else{return "El cargo "+cargo.getIdCargo()+" no existe";}}
        public String insertar(Local local){
        String regInsertados="¡Local registrado!";
        long contador=0;
        ContentValues local_ = new ContentValues();
        local_.put("idLocal",local.getIdLocal());
        local_.put("nombre",local.getNombre());
        contador=db.insert("local", null,local_);

        if(contador==-1 || contador==0){regInsertados= "Error al insertar el registro. Verifique la inserción, por favor";}

        return regInsertados;}
    public Local consultarLocal(int idLocal){
        String[] id = {String.valueOf(idLocal)};
        Cursor cursor = db.query("local", camposLocal, "idLocal = ?", id, null, null, null);

        if(cursor.moveToFirst()){
            Local local= new Local();
            local.setIdLocal(cursor.getInt(0));
            local.setNombre(cursor.getString(1));
            return local;}else{return null;}}
    public String eliminar(Local local) {
        String regAfectados="Filas afectadas: ";
        int contador=0;
        contador+=db.delete("local", "idLocal='"+local.getIdLocal()+"'", null);
        regAfectados+=contador;
        return regAfectados;}
    public String actualizar(Local local) {
        if(verificarIntegridad(local, 1)){
            String[] id = {String.valueOf(local.getIdLocal())};
            ContentValues cv = new ContentValues();
            cv.put("idLocal",local.getIdLocal());
            cv.put("nombre",local.getNombre());
            db.update("local", cv, "idLocal = ?", id);
            return "Registro actualizado correctamente";}
        else{return "El local "+local.getIdLocal()+" no existe";
        }
    }
}
