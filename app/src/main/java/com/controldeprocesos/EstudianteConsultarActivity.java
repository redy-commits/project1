package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EstudianteConsultarActivity extends AppCompatActivity {

    ControlBDGpo16 helper;
    EditText editCarnet;
    EditText editIdUsuario;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_consultar);
        helper = new ControlBDGpo16(this);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editIdUsuario = (EditText) findViewById(R.id.editIdUsuario);

    }
    public void consultarEstudiante(View v) {
        helper.abrir();
        Estudiante estudiante =   helper.consultarEstudiante(editCarnet.getText().toString());
        helper.cerrar();
        if(estudiante == null)
            Toast.makeText(this, "Estudiante con CARNET " + editCarnet.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else {
            editIdUsuario.setText(String.valueOf(estudiante.getIdUsuario()));
        }
    }

    public void limpiarTexto(View view) {
        editCarnet.setText("");
        editIdUsuario.setText("");
    }
}