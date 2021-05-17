package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SegundaRevisionInsertarActivity extends AppCompatActivity {
    ControlBDGpo16 helper;
    EditText editIdDocente;
    EditText editIdOtroDocente;
    EditText editCarnet;
    EditText editNumEva;
    EditText editrespSociedadEstud;
    EditText editNotaDefinitiva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_revision_insertar);
        helper = new ControlBDGpo16(this);
        editIdDocente = findViewById(R.id.editIdDocente);
        editIdOtroDocente = findViewById(R.id.editIdOtroDocente);
        editCarnet = findViewById(R.id.editCarnet);
        editNumEva = findViewById(R.id.editNumEva);
        editrespSociedadEstud = findViewById(R.id.editrespSociedadEstud);
        editNotaDefinitiva = findViewById(R.id.editNotaDefinitiva);

    }
    public void insertarSegundaRevision(View v) {
        SegundaRevision segunda = new SegundaRevision();
        String idDocente=editIdDocente.getText().toString();
        String idOtroDocente=editIdOtroDocente.getText().toString();
        String carnet=editCarnet.getText().toString();
        String numeva=editNumEva.getText().toString();
        String respuesta=editrespSociedadEstud.getText().toString();
        String nota=editNotaDefinitiva.getText().toString();
        String regInsertados;
        segunda.setIdDocente(Integer.parseInt(idDocente));
        segunda.setIdOtroDocente(Integer.parseInt(idOtroDocente));
        segunda.setRespSociedadEstud(respuesta);
        segunda.setNotaDefinitiva(Float.parseFloat(nota));

        helper.abrir();
        regInsertados= String.valueOf(helper.insertarSegundaRevision(segunda,carnet,numeva));
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }
}