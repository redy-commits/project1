package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MateriaActualizarActivity extends AppCompatActivity {

    private ControlBDGpo16 helper;
    private EditText edtCodMateria,edtCodEscuela,edtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_actualizar);
        helper = new ControlBDGpo16(this);
        edtCodMateria=(EditText)findViewById(R.id.edtCodMateria);
        edtCodEscuela=(EditText)findViewById(R.id.edtCodEscuela);
        edtNombre=(EditText)findViewById(R.id.edtNombre);}

    public void actualizarMateria(View v) {
        Materia materia = new Materia();
        materia.setCodMateria(edtCodMateria.getText().toString());
        materia.setCodEscuela(Integer.parseInt(edtCodEscuela.getText().toString()));
        materia.setNombre(edtNombre.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(materia);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();}}