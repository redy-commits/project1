package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CargoBorrarActivity extends Activity {

    EditText edtIdCargo;
    ControlBDGpo16 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_borrar);
        helper=new ControlBDGpo16 (this);
        edtIdCargo=(EditText)findViewById(R.id.edtIdCargo);}

    public void eliminarCargo(View view) {
        String regEliminadas;
        Cargo cargo=new Cargo();
        cargo.setIdCargo(Integer.parseInt(edtIdCargo.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminar(cargo);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();}}
