package com.controldeprocesos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    private String[] menu={"Tabla ExamenIndividual","Tabla Docente","Tabla EncargadoDeImpresiones","Tabla Cargo","Tabla Local","Tabla Matricula","Tabla Estudiante","Tabla SegundaRevision","Tabla Escuela","Tabla Evaluacion","Tabla SolicitudDeImpresiones","Tabla Revision","Tabla Reservacion","Tabla Materia","Tabla Ciclo","Tabla SolicitudDeCambio","Cerrar sesión","Llenar base de datos"};
    private String[] activities={"ExamenIndividualMenuActivity","DocenteMenuActivity","EncargadoDeImpresionesMenuActivity","CargoMenuActivity","LocalMenuActivity","MatriculaMenuActivity","EstudianteMenuActivity","SegundaRevisionMenuActivity","EscuelaMenuActivity","EvaluacionMenuActivity","SolicitudDeImpresionesMenuActivity","RevisionMenuActivity","ReservacionMenuActivity","MateriaMenuActivity","CicloMenuActivity","SolicitudDeCambioMenuActivity","MainActivity"};
    private ControlBDGpo16 helper;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new ControlBDGpo16(this);

        helper.abrir();
        usuario= helper.consultarSesion();
        helper.cerrar();

        if(usuario!=null){
            if(usuario.getTipo().toString().equals("admin")){
                menu=new String[]{"Tabla SegundaRevision","Tabla SolicitudDeCambio", "Cerrar sesión","Llenar base de datos"};
                activities= new String[]{"SegundaRevisionMenuActivity","SolicitudDeCambioMenuActivity","MainActivity"};
            }else if(usuario.getTipo().toString().equals("docente")){
                menu=new String[]{"Tabla SolicitudDeCambio","Tabla ExamenIndividual","Tabla Evaluacion","Tabla SolicitudDeImpresiones","Tabla Reservacion","Tabla Revision","Cerrar sesión","Llenar base de datos"};
                activities= new String[]{"SolicitudDeCambioMenuActivity","ExamenIndividualMenuActivity","EvaluacionMenuActivity","SolicitudDeImpresionesMenuActivity","ReservacionMenuActivity","RevisionMenuActivity","MainActivity"};
            }else if(usuario.getTipo().toString().equals("estudiante")){
                menu=new String[]{"Tabla Matricula","Cerrar sesión"};
                activities= new String[]{"MatriculaMenuActivity","MainActivity"};
            }else if(usuario.getTipo().toString().equals("encargado")){
                menu=new String[]{"Tabla SolicitudDeImpresiones","Cerrar sesión","Llenar base de datos"};
                activities= new String[]{"SolicitudDeImpresionesMenuActivity","MainActivity"};}
            else if(usuario.getTipo().toString().equals("instructor")){
                menu=new String[]{"Tabla SolicitudDeImpresiones","Tabla Matricula","Cerrar sesión","Llenar base de datos"};
                activities= new String[]{"SolicitudDeImpresionesMenuActivity","MatriculaMenuActivity","MainActivity"};}}

        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu));

        if(usuario==null){
            Intent intent = new Intent(MainActivity.this,Login.class);
            finish();
            startActivity(intent);}
        else{}}

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        if(position!=menu.length-1){
            String nombreValue=activities[position];

            if(nombreValue=="MainActivity"){
                usuario.setSesion(false);
                helper.abrir();
                helper.actualizar(usuario);
                helper.cerrar();}

            try{
                Class<?> clase=Class.forName("com.controldeprocesos."+nombreValue);
                Intent inte = new Intent(this,clase);
                if(nombreValue=="MainActivity")finish();
                this.startActivity(inte);
            }catch(ClassNotFoundException e){e.printStackTrace();}

        }else{
            helper.abrir();
            String tost=helper.LlenarBDGpo16();
            helper.cerrar();
            Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();}}}
