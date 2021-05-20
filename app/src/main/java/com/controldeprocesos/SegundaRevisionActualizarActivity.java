package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SegundaRevisionActualizarActivity extends AppCompatActivity {
    ControlBDGpo16 helper;
    EditText editCarnet;
    EditText editNumEva;
    EditText editIdDocente;
    EditText editIdOtroDocente;
    EditText editNotaDefinitiva;
    EditText editRespSociedadEstud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_revision_actualizar);
        helper = new ControlBDGpo16(this);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editNumEva = (EditText) findViewById(R.id.editNumEva);
        editIdDocente = (EditText) findViewById(R.id.editIdDocente);
        editIdOtroDocente = (EditText) findViewById(R.id.editIdOtroDocente);
        editNotaDefinitiva = (EditText) findViewById(R.id.editNotaDefinitiva);
        editRespSociedadEstud = (EditText) findViewById(R.id.editRespSociedadEstud);
    }
    public void actualizarSegundaRevision(View v) {

        SegundaRevision segunda = new SegundaRevision();
       String carnet= editCarnet.getText().toString();
        String numeva= editNumEva.getText().toString();
    int idDocente= Integer.parseInt(editIdDocente.getText().toString());
    int idOtroDocente= Integer.parseInt(editNumEva.getText().toString());
    float nota= Integer.parseInt(editNotaDefinitiva.getText().toString());
    String respSociedadEstud = editRespSociedadEstud.getText().toString();
        segunda.setRespSociedadEstud(respSociedadEstud);
    segunda.setIdDocente(idDocente);
     segunda.setIdOtroDocente(idOtroDocente);
     segunda.setNotaDefinitiva(nota);
        helper.abrir();
        String estado = helper.actualizarSegundaRevision(segunda,carnet,numeva);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
}