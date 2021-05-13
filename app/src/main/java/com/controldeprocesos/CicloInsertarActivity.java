package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CicloInsertarActivity extends Activity {

    private ControlBDGpo16 helper;
    private EditText edtIdCiclo,edtAnio,edtNumCiclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_insertar);
        helper = new ControlBDGpo16(this);
        edtIdCiclo=(EditText)findViewById(R.id.edtIdCiclo);
        edtAnio=(EditText)findViewById(R.id.edtAnio);
        edtNumCiclo=(EditText)findViewById(R.id.edtNumCiclo);}

    public void insertarCiclo(View view) {
        int idCiclo=Integer.parseInt(edtIdCiclo.getText().toString());
        int anio=Integer.parseInt(edtAnio.getText().toString());
        int numCiclo=Integer.parseInt(edtNumCiclo.getText().toString());
        String regInsertados;
        Ciclo ciclo=new Ciclo();
        ciclo.setIdCiclo(idCiclo);
        ciclo.setAnio(anio);
        ciclo.setNumCiclo(numCiclo);
        helper.abrir();
        regInsertados=helper.insertar(ciclo);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();}}