package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MateriaBorrarActivity extends AppCompatActivity {

    EditText edtCodMateria;
    ControlBDGpo16 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_borrar);
        helper=new ControlBDGpo16 (this);
        edtCodMateria=(EditText)findViewById(R.id.edtCodMateria);}

    public void eliminarMateria(View view) {
    String regEliminadas;
    Materia materia=new Materia();
    materia.setCodMateria(edtCodMateria.getText().toString());
        helper.abrir();
    regEliminadas=helper.eliminar(materia);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();}}