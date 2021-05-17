package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteConsultarActivity extends AppCompatActivity {

    ControlBDGpo16 helper;
    EditText editIdDocente;
    EditText editIdUsuario;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar);
        helper = new ControlBDGpo16(this);
        editIdDocente = (EditText) findViewById(R.id.editIdDocente);
        editIdUsuario = (EditText) findViewById(R.id.editIdUsuario);

    }
    public void consultarDocente(View v) {
        helper.abrir();
        Docente docente =   helper.consultarDocente(editIdDocente.getText().toString());
        helper.cerrar();
        if(docente == null)
            Toast.makeText(this, "Docente con ID " + editIdDocente.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else {
            editIdUsuario.setText(String.valueOf(docente.getIdUsuario()));
        }
    }

    public void limpiarTexto(View view) {
        editIdDocente.setText("");
        editIdUsuario.setText("");
    }
}