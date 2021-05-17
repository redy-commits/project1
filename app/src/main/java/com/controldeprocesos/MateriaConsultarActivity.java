package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MateriaConsultarActivity extends AppCompatActivity {

    private ControlBDGpo16 helper;
    private EditText edtCodMateria;
    private TextView txtCodEscuela,txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_consultar);
        helper = new ControlBDGpo16(this);
        edtCodMateria=(EditText)findViewById(R.id.edtCodMateria);
        txtCodEscuela=(TextView)findViewById(R.id.txtCodEscuela);
        txtNombre=(TextView)findViewById(R.id.txtNombre);
        txtCodEscuela.setText("");
        txtNombre.setText("");}

    public void consultarMateria(View v) {
        helper.abrir();
        Materia materia = helper.consultarMateria(edtCodMateria.getText().toString());
        helper.cerrar();
        if(materia == null)
            Toast.makeText(this, "La materia "+edtCodMateria.getText().toString()+ " no ha sido encontrada", Toast.LENGTH_LONG).show();
        else{
            txtCodEscuela.setText(txtCodEscuela.getText()+": "+materia.getCodEscuela());
            txtNombre.setText(txtNombre.getText()+": "+materia.getNombre());}}}