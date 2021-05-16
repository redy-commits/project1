package com.controldeprocesos;

import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;



public class EstudianteInsertarActivity extends Activity {

    ControlBDGpo16 helper;
    EditText editCarnet;
    EditText editNombre;
    EditText editCorreo;
    EditText editContrasena;
    EditText editTipo;
    EditText editSesion;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_insertar);
        helper = new ControlBDGpo16(this);
        editCarnet = findViewById(R.id. editCarnet);
        editNombre = findViewById(R.id.editNombre);
        editContrasena = findViewById(R.id.editContrasena);
        editSesion = findViewById(R.id.editSesion);
        editTipo = findViewById(R.id.editTipo);
        editCorreo = findViewById(R.id.editCorreo);

    }
    public void insertarEstudiante(View v) {
        Usuario user = new Usuario();
        String carnet=editCarnet.getText().toString();
        String nombre=editNombre.getText().toString();
        String correo=editCorreo.getText().toString();
        String contrasena=editContrasena.getText().toString();
        String tipo=editTipo.getText().toString();
        boolean sesion=Boolean.parseBoolean(editSesion.getText().toString());
        String regInsertados;
        Estudiante estudiante=new Estudiante();

        estudiante.setCarnet(carnet);
        user.setCorreo(correo);
        user.setContrasena(contrasena);
        user.setTipo(tipo);
        user.setSesion(sesion);
        user.setNombre(nombre);
        helper.abrir();
        regInsertados=helper.insertar(estudiante, user);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }
    public void limpiarTexto(View v) {
        editCarnet.setText("");
        editNombre.setText("");
        editCorreo.setText("");
        editContrasena.setText("");
        editTipo.setText("");
        editSesion.setText("");

    }
}