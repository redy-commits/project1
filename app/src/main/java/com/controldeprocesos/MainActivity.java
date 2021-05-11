package com.controldeprocesos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    String[] menu={"Tabla ExamenIndividual","Tabla Docente","Tabla EncargadoDeImpresiones","Tabla Cargo","Tabla Local","Tabla Matricula","Tabla Estudiante","Tabla SegundaRevision","Tabla Escuela","Tabla Evaluacion","Tabla SolicitudDeImpresiones","Tabla Revision","Tabla Reservacion","Tabla Materia","Tabla Ciclo","LLenar base de datos"};
    String[] activities={"ExamenIndividualMenuActivity","DocenteMenuActivity","EncargadoDeImpresionesMenuActivity","CargoMenuActivity","LocalMenuActivity","CargoMenuMatricula","EstudianteMenuActivity","SegundaRevisionMenuActivity","EscuelaMenuActivity","EvaluacionMenuActivity","SolicitudDeImpresionesMenuActivity","RevisionMenuActivity","ReservacionMenuActivity","MateriaMenuActivity","CicloMenuActivity"};
    ControlBDGpo16 BDhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu));
        BDhelper = new ControlBDGpo16(this);}

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        if(position!=15){
            String nombreValue=activities[position];

            try{
                Class<?> clase=Class.forName("com.controldeprocesos."+nombreValue);
                Intent inte = new Intent(this,clase);
                this.startActivity(inte);
            }catch(ClassNotFoundException e){e.printStackTrace();}

        }else{
            BDhelper.abrir();
            String tost=BDhelper.LlenarBDGpo16();
            BDhelper.cerrar();
            Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();}}}