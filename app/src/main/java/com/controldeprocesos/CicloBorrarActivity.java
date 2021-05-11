package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CicloBorrarActivity extends Activity {

    EditText edtIdCiclo;
    ControlBDGpo16 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_borrar);
        helper=new ControlBDGpo16 (this);
        edtIdCiclo=(EditText)findViewById(R.id.edtIdCiclo);}

    public void eliminarCiclo(View view) {
        String regEliminadas;
        Ciclo ciclo=new Ciclo();
        ciclo.setIdCiclo(Integer.parseInt(edtIdCiclo.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminar(ciclo);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();}}