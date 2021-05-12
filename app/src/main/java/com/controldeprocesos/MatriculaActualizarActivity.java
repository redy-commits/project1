package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MatriculaActualizarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matricula_actualizar);
    }
}package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MatriculaActualizarActivity extends AppCompatActivity {

    private ControlBDGpo16 helper;
    private EditText edtIdMatricula,edtCarnet,edtCodMateria,edtIdCiclo,edtNumMatricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matricula_actualizar);
        helper =  new ControlBDGpo16(this);
        edtIdMatricula=(EditText)findViewById(R.id.edtIdMatricula);
        edtCarnet=(EditText)findViewById(R.id.edtCarnet);
        edtCodMateria=(EditText)findViewById(R.id.edtIdCodMateria);
        edtIdCiclo=(EditText)findViewById(R.id.edtIdCiclo);
        edtNumMatricula=(EditText)findViewById(R.id.edtNumMatricula);
    }
    public void actualizarMatricula(View v){
        Matricula matricula =new Matricula();
        matricula.setIdMatricula(Integer.parseInt(edtIdMatricula.getText().toString()));
        matricula.setCarnet(edtCarnet.getText().toString());
        matricula.setCodMateria(edtCodMateria.getText().toString());
        matricula.setIdCiclo(Integer.parseInt(edtIdCiclo.getText().toString()));
        matricula.setNumMatricula(Integer.parseInt(edtNumMatricula.getText().toString()));
        helper.abrir();
        String estado = helper.actualizarMatr(matricula);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
}
