package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;

public class MatriculaInsertarActivity extends Activity {
    private ControlBDGpo16 helper;
    private EditText edtIdMatricula,edtCarnet,edtCodMateria,edtIdCiclo,edtNumMatricula,edtFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matricula_insertar);
        helper = new ControlBDGpo16(this);
        edtIdMatricula=(EditText)findViewById(R.id.edtIdMatricula);
        edtCarnet=(EditText)findViewById(R.id.edtCarnet);
        edtCodMateria=(EditText)findViewById(R.id.edtCodMateria);
        edtIdCiclo=(EditText)findViewById(R.id.edtIdCiclo);
        edtNumMatricula=(EditText)findViewById(R.id.edtNumMatricula);
        edtFecha=(EditText)findViewById(R.id.edtFecha);}

    public void insertarMatricula(View v) {
        int idMatricula=Integer.parseInt(edtIdMatricula.getText().toString());
        String carnet=edtCarnet.getText().toString();
        String codMateria=edtCodMateria.getText().toString();
        int idCiclo=Integer.parseInt(edtIdCiclo.getText().toString());
        int numMatricula=Integer.parseInt(edtNumMatricula.getText().toString());
        String regInsertados;
        Matricula matricula=new Matricula();
        matricula.setIdMatricula(idMatricula);
        matricula.setCarnet(carnet);
        matricula.setCodMateria(codMateria);
        matricula.setIdCiclo(idCiclo);
        matricula.setNumMatricula(numMatricula);
        helper.abrir();
        regInsertados=helper.insertarMatr(matricula);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();}
}
