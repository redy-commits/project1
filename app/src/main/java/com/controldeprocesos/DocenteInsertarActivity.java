package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteInsertarActivity extends Activity {
    ControlBDGpo16 helper;
    EditText editNombre;
    EditText editCorreo;
    EditText editContrasena;
    EditText editTipo;
    EditText editSesion;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar);
        helper = new ControlBDGpo16(this);
        editNombre = findViewById(R.id.editNombre);
        editContrasena = findViewById(R.id.editContrasena);
        editSesion = findViewById(R.id.editSesion);
        editTipo = findViewById(R.id.editTipo);
        editCorreo = findViewById(R.id.editCorreo);

    }
    public void insertarDocente(View v) {
        Usuario user = new Usuario();

        String nombre=editNombre.getText().toString();
        String correo=editCorreo.getText().toString();
        String contrasena=editContrasena.getText().toString();
        String tipo=editTipo.getText().toString();
        boolean sesion=Boolean.parseBoolean(editSesion.getText().toString());
        String regInsertados;
        user.setCorreo(correo);
        user.setContrasena(contrasena);
        user.setTipo(tipo);
        user.setSesion(sesion);
        user.setNombre(nombre);
        helper.abrir();
        regInsertados=helper.insertarDocente(user);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }
    public void limpiarTexto(View v) {

        editNombre.setText("");
        editCorreo.setText("");
        editContrasena.setText("");
        editTipo.setText("");
        editSesion.setText("");

    }
}