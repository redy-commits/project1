package com.controldeprocesos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    String[] menu={"Tabla ExamenIndividual","Tabla Docente","Tabla EncargadoDeImpresiones","Tabla Cargo","Tabla Local","Tabla Matricula","Tabla Estudiante","Tabla SegundaRevision","Tabla Escuela","Tabla Evaluacion","Tabla SolicitudDeImpresiones","Tabla Revision","Tabla Reservacion","Tabla Materia","Tabla Ciclo","Cerrar sesión","LLenar base de datos"};
    String[] activities={"ExamenIndividualMenuActivity","DocenteMenuActivity","EncargadoDeImpresionesMenuActivity","CargoMenuActivity","LocalMenuActivity","MatriculaMenuActivity","EstudianteMenuActivity","SegundaRevisionMenuActivity","EscuelaMenuActivity","EvaluacionMenuActivity","SolicitudDeImpresionesMenuActivity","RevisionMenuActivity","ReservacionMenuActivity","MateriaMenuActivity","CicloMenuActivity","Login"};
    ControlBDGpo16 helper;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu));
        helper = new ControlBDGpo16(this);

        try{
            helper.abrir();
            usuario= helper.consultarSesion();
            helper.cerrar();}
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Control de sesiones mal incorporado",Toast.LENGTH_SHORT).show();}

        if(usuario==null){
            Intent intent = new Intent(MainActivity.this,Login.class);
            finish();
            startActivity(intent);}
        else{}}

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        if(position!=16){
            String nombreValue=activities[position];

            if(nombreValue=="Login"){
                usuario.setSesion(false);
                helper.abrir();
                helper.actualizar(usuario);
                helper.cerrar();}

            try{
                Class<?> clase=Class.forName("com.controldeprocesos."+nombreValue);
                Intent inte = new Intent(this,clase);
                this.startActivity(inte);
            }catch(ClassNotFoundException e){e.printStackTrace();}

        }else{
            helper.abrir();
            String tost=helper.LlenarBDGpo16();
            helper.cerrar();
            Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();}}}
