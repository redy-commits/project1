package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EstudianteBorrarActivity extends AppCompatActivity {

    EditText editCarnet;
    ControlBDGpo16 controlhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_borrar);
        controlhelper=new ControlBDGpo16 (this);
        editCarnet=(EditText)findViewById(R.id.editCarnet);
    }

    public void eliminarEstudiante(View v){
        String regEliminadas;
        Estudiante estudiante =new Estudiante();
        String carnet =editCarnet.getText().toString();
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(carnet);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}