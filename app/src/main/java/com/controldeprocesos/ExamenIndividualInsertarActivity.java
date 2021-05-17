package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ExamenIndividualInsertarActivity extends AppCompatActivity {

    ControlBDGpo16 helper;
    EditText editCarnet;
    EditText editNumEva;
    EditText editNota;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen_individual_insertar);
        helper = new ControlBDGpo16(this);
        editCarnet = findViewById(R.id.editCarnet);
        editNumEva = findViewById(R.id.editNumEva);
        editNota = findViewById(R.id.editNota);

    }
    public void insertarExamenIndividual(View v) {
        ExamenIndividual examen = new ExamenIndividual();

        String carnet=editCarnet.getText().toString();
        String numeva=editNumEva.getText().toString();
        String nota=editNota.getText().toString();
        examen.setNota(Float.parseFloat(nota));
        examen.setNumeva(Integer.parseInt(numeva));
        examen.setCarnet(carnet);
        String regInsertados;

        helper.abrir();
        regInsertados=helper.insertarExamenIndividual(examen);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }
    public void limpiarTexto(View v) {

        editCarnet.setText("");
        editNumEva.setText("");
        editNota.setText("");
    }
}