package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText edtUsuario,edtContrasena;
    private ControlBDGpo16 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper = new ControlBDGpo16(this);
        edtUsuario=(EditText)findViewById(R.id.edtUsuario);
        edtContrasena=(EditText)findViewById(R.id.edtContrasena);}

    public void acceder(View view) {
        helper.abrir();
        Usuario usuario = helper.consultarUsuario(edtUsuario.getText().toString());

        if(usuario==null){
            Estudiante estudiante = helper.consultarEstudiante(edtUsuario.getText().toString());
            if(estudiante!=null){usuario=helper.consultarUsuario(estudiante.getIdUsuario());}}

            if(usuario!=null){
                if(usuario.getContrasena().toString().equals(edtContrasena.getText().toString())){
                    usuario.setSesion(true);
                    helper.actualizar(usuario);
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    finish();
                    startActivity(intent);}
                else{Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();}}
        else{Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_SHORT).show();}
        helper.cerrar();}}