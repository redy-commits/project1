package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoDeImpresionesConsultarActivity extends AppCompatActivity {

    ControlBDGpo16 helper;
    EditText editIdEncargadoDeImpresiones;
    EditText editIdUsuario;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_de_impresiones_consultar);
        helper = new ControlBDGpo16(this);
        editIdEncargadoDeImpresiones = (EditText) findViewById(R.id.editIdEncargadoDeImpresiones);
        editIdUsuario = (EditText) findViewById(R.id.editIdUsuario);

    }
    public void consultarEncargadoDeImpresiones(View v) {
        helper.abrir();
        EncargadoDeImpresiones encargado =  helper.consultarEncargado(editIdEncargadoDeImpresiones.getText().toString());
        helper.cerrar();
        if(encargado == null)
            Toast.makeText(this, "Encargado de Impresiones con ID " + editIdEncargadoDeImpresiones.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else {
            editIdUsuario.setText(String.valueOf(encargado.getIdUsuario()));
        }
    }

    public void limpiarTexto(View view) {
        editIdEncargadoDeImpresiones.setText("");
        editIdUsuario.setText("");
    }
}