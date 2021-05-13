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
        //Estudiante estudiante=new Estudiante();
        //Docente docente=new Docente();
        //EncargadoDeImpresiones encargadoDeImpresiones=new EncargadoDeImpresiones();

        helper.abrir();
        Usuario usuario = helper.consultarUsuario(Integer.parseInt(edtUsuario.getText().toString()));
        helper.cerrar();

        try{
        if(String.valueOf(usuario.getIdUsuario()).equals(edtUsuario.getText().toString())){
            if(usuario.getContrasena().toString().equals(edtContrasena.getText().toString())){
                usuario.setSesion(true);
                helper.abrir();
                helper.actualizar(usuario);
                helper.cerrar();
                Intent intent = new Intent(Login.this,MainActivity.class);
                finish();
                startActivity(intent);}
            else{Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();}}}
        catch(Exception e){Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_SHORT).show();}}}