package com.controldeprocesos;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class EscuelaActualizarActivity extends Activity {
    ControlBDGpo16 helper;
    EditText editCodEscuela;
    EditText editNombre;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_actualizar);
        helper = new ControlBDGpo16(this);
        editCodEscuela = (EditText) findViewById(R.id.editCodEscuela);
        editNombre = (EditText) findViewById(R.id.editNombre);

    }
    public void actualizarEscuela(View v) {

        Escuela escuela = new Escuela();
        int coescuela= Integer.parseInt(editCodEscuela.getText().toString());
        escuela.setcodEscuela(coescuela);
        escuela.setNombre(editNombre.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(escuela);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCodEscuela.setText("");
        editNombre.setText("");
        }
    }