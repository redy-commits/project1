package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ExamenIndividualConsultarActivity extends AppCompatActivity {

    ControlBDGpo16 helper;
    EditText editCarnet;
    EditText editNumEva;
    EditText editNota;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen_individual_consultar);
        helper = new ControlBDGpo16(this);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editNumEva = (EditText) findViewById(R.id.editNumEva);
        editNota = (EditText) findViewById(R.id.editNota);

    }
    public void consultarExamenIndividual(View v) {
        helper.abrir();
        ExamenIndividual examen =   helper.consultarExamenIndividual(editCarnet.getText().toString(), editNumEva.getText().toString() );
        helper.cerrar();
        if(examen == null)
            Toast.makeText(this, "Examen con CARNET " + editCarnet.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else {
            editNota.setText(String.valueOf(examen.getNota()));
        }
    }

    public void limpiarTexto(View view) {
        editCarnet.setText("");
        editNumEva.setText("");
    }
}