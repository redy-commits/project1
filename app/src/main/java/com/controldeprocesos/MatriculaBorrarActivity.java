package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MatriculaBorrarActivity extends AppCompatActivity {

    EditText edtIdMatricula;
    ControlBDGpo16 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matricula_borrar);
        helper=new ControlBDGpo16 (this);
        edtIdMatricula=(EditText)findViewById(R.id.edtIdMatricula);}

    public void eliminarMatricula(View v){
        String regEliminadas;
        Matricula matricula=new Matricula();
        matricula.setIdMatricula(Integer.parseInt(edtIdMatricula.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminarMatr(matricula);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();}}
