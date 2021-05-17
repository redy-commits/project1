package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CargoActualizarActivity extends Activity {

    private ControlBDGpo16 helper;
    private EditText edtIdCargo,edtNombreCargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_actualizar);
        helper = new ControlBDGpo16(this);
        edtIdCargo=(EditText)findViewById(R.id.edtIdCargo);
        edtNombreCargo=(EditText)findViewById(R.id.edtNombreCargo);}

    public void actualizarCargo(View view) {
        Cargo cargo = new Cargo();
        cargo.setIdCargo(Integer.parseInt(edtIdCargo.getText().toString()));
        cargo.setNombreCargo(edtNombreCargo.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(cargo);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();}}
