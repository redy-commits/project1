package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SegundaRevisionConsultarActivity extends AppCompatActivity {
    ControlBDGpo16 helper;
    EditText editCarnet;
    EditText editNumEva;
    EditText editNotaDefinitiva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_revision_consultar);
        helper = new ControlBDGpo16(this);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editNumEva = (EditText) findViewById(R.id.editNumEva);
        editNotaDefinitiva = (EditText) findViewById(R.id.editNotaDefinitiva);

    }
    public void consultarSegundaRevision(View v) {
        helper.abrir();
       String nota =   helper.consultarSegundaRevision(editCarnet.getText().toString(),editNumEva.getText().toString());
        helper.cerrar();
        if(nota == null)
            Toast.makeText(this, "Estudiante con CARNET " + editCarnet.getText().toString() + " no ha hecho segunda revision", Toast.LENGTH_LONG).show();
        else {
            editNotaDefinitiva.setText(nota);
        }
    }
}