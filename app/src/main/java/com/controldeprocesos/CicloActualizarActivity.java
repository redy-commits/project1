package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CicloActualizarActivity extends Activity {

    private ControlBDGpo16 helper;
    private EditText edtIdCiclo,edtAnio,edtNumCiclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_actualizar);
        helper = new ControlBDGpo16(this);
        edtIdCiclo=(EditText)findViewById(R.id.edtIdCiclo);
        edtAnio=(EditText)findViewById(R.id.edtAnio);
        edtNumCiclo=(EditText)findViewById(R.id.edtNumCiclo);}

    public void actualizarCiclo(View view) {
        Ciclo ciclo = new Ciclo();
        ciclo.setIdCiclo(Integer.parseInt(edtIdCiclo.getText().toString()));
        ciclo.setAnio(Integer.parseInt(edtAnio.getText().toString()));
        ciclo.setNumCiclo(Integer.parseInt(edtNumCiclo.getText().toString()));
        helper.abrir();
        String estado = helper.actualizar(ciclo);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();}}