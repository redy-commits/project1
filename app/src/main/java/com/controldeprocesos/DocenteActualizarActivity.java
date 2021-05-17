package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteActualizarActivity extends Activity {

    ControlBDGpo16 helper;
    EditText editIdDocente;
    EditText editNombre;
    EditText editCorreo;
    EditText editTipo;
    EditText editContrasena;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_actualizar);
        helper = new ControlBDGpo16(this);
        editIdDocente = (EditText) findViewById(R.id.editIdDocente);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editTipo = (EditText) findViewById(R.id.editTipo);
        editContrasena = (EditText) findViewById(R.id.editContrasena);
    }
    public void actualizarDocente(View v) {
        Docente docente = new Docente();
        Usuario usuario= new Usuario();
        docente.setIdDocente(Integer.parseInt(editIdDocente.getText().toString()));
        usuario.setNombre(editNombre.getText().toString());
        usuario.setContrasena(editContrasena.getText().toString());
        usuario.setTipo(editTipo.getText().toString());
        usuario.setCorreo(editCorreo.getText().toString());
        helper.abrir();
        String estado = helper.actualizarDocente(docente,usuario);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdDocente.setText("");
        editNombre.setText("");
        editCorreo.setText("");
        editTipo.setText("");
        editContrasena.setText("");
    }
}