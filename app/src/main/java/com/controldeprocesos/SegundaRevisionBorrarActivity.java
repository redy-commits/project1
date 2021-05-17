package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SegundaRevisionBorrarActivity extends AppCompatActivity {
    ControlBDGpo16 helper;
    EditText editCarnet;
    EditText editNumEva;
    EditText editNotaDefinitiva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_revision_borrar);
        helper = new ControlBDGpo16(this);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editNumEva = (EditText) findViewById(R.id.editNumEva);
        editNotaDefinitiva = (EditText) findViewById(R.id.editNotaDefinitiva);
    }
    public void eliminarSegundaRevision(View v) {
        helper.abrir();
        String nota =   helper.eliminarSegundaRevision(editCarnet.getText().toString(),editNumEva.getText().toString());
        helper.cerrar();
        Toast.makeText(this, nota, Toast.LENGTH_SHORT).show();
    }
}