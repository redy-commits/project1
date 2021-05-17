package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CargoInsertarActivity extends Activity {
    private ControlBDGpo16 helper;
    private EditText edtIdCargo,edtNombreCargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_insertar);
        helper = new ControlBDGpo16(this);
        edtIdCargo=(EditText)findViewById(R.id.edtIdCargo);
        edtNombreCargo=(EditText)findViewById(R.id.edtNombreCargo);
    }

    public void insertarCargo(View v) {
        int idCargo=Integer.parseInt(edtIdCargo.getText().toString());
        String nombreCargo=edtNombreCargo.getText().toString();
        String regInsertados;
        Cargo cargo=new Cargo();
        cargo.setIdCargo(idCargo);
        cargo.setNombreCargo(nombreCargo);
        helper.abrir();
        regInsertados=helper.insertar(cargo);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();}
}
