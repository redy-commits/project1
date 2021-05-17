package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoDeImpresionesActualizarActivity extends Activity {

    ControlBDGpo16 helper;
    EditText editIdEncargadoDeImpresiones;
    EditText editNombre;
    EditText editCorreo;
    EditText editTipo;
    EditText editContrasena;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_de_impresiones_actualizar);
        helper = new ControlBDGpo16(this);
        editIdEncargadoDeImpresiones = (EditText) findViewById(R.id.editIdEncargadoDeImpresiones);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editTipo = (EditText) findViewById(R.id.editTipo);
        editContrasena = (EditText) findViewById(R.id.editContrasena);
    }
    public void actualizarEncargado(View v) {
        EncargadoDeImpresiones encargado = new EncargadoDeImpresiones();
        Usuario usuario= new Usuario();
        encargado.setIdEncargadoDeImpresiones(Integer.parseInt(editIdEncargadoDeImpresiones.getText().toString()));
        usuario.setNombre(editNombre.getText().toString());
        usuario.setContrasena(editContrasena.getText().toString());
        usuario.setTipo(editTipo.getText().toString());
        usuario.setCorreo(editCorreo.getText().toString());
        helper.abrir();
        String estado = helper.actualizarEncargado(encargado,usuario);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdEncargadoDeImpresiones.setText("");
        editNombre.setText("");
        editCorreo.setText("");
        editTipo.setText("");
        editContrasena.setText("");
    }
}