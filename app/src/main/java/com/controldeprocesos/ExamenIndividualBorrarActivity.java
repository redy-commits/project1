package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ExamenIndividualBorrarActivity extends AppCompatActivity {

    ControlBDGpo16 helper;
    EditText editCarnet;
    EditText editNumEva;
    EditText editNota;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen_individual_borrar);
        helper = new ControlBDGpo16(this);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editNumEva = (EditText) findViewById(R.id.editNumEva);
        editNota = (EditText) findViewById(R.id.editNota);

    }
    public void eliminarExamenIndividual(View v) {
        helper.abrir();
       String examen =   helper.eliminarExamenIndividual(editCarnet.getText().toString(), editNumEva.getText().toString() );
        helper.cerrar();
        Toast.makeText(this, "Examen individual Eliminado", Toast.LENGTH_LONG).show();
    }

    public void limpiarTexto(View view) {
        editCarnet.setText("");
        editNumEva.setText("");
    }
}