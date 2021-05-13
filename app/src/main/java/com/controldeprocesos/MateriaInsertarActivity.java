package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MateriaInsertarActivity extends AppCompatActivity {

    private ControlBDGpo16 helper;
    private EditText edtCodMateria,edtCodEscuela,edtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_insertar);
        helper = new ControlBDGpo16(this);
        edtCodMateria=(EditText)findViewById(R.id.edtIdCiclo);
        edtCodEscuela=(EditText)findViewById(R.id.edtAnio);
        edtNombre=(EditText)findViewById(R.id.edtNumCiclo);}

    public void insertarMateria(View v) {
        String codMateria=edtCodMateria.getText().toString();
        int codEscuela=Integer.parseInt(edtCodEscuela.getText().toString());
        String nombre=edtNombre.getText().toString();
        String regInsertados;
        Materia materia=new Materia();
        materia.setCodMateria(codMateria);
        materia.setCodEscuela(codEscuela);
        materia.setNombre(nombre);
        helper.abrir();
        regInsertados=helper.insertar(materia);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();}}