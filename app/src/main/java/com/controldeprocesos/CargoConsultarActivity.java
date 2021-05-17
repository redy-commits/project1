package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CargoConsultarActivity extends Activity {

    private ControlBDGpo16 helper;
    private EditText edtIdCargo;
    private TextView txtNombreCargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_consultar);
        helper = new ControlBDGpo16(this);
        edtIdCargo=(EditText)findViewById(R.id.edtIdCargo);
        txtNombreCargo=(TextView)findViewById(R.id.txtNombreCargo);
        txtNombreCargo.setText("");}

    public void consultarCargo(View v) {
        helper.abrir();
        Cargo cargo = helper.consultarCargo(Integer.parseInt(edtIdCargo.getText().toString()));
        helper.cerrar();
        if(cargo == null)
            Toast.makeText(this, "El cargo "+edtIdCargo.getText().toString()+ " no ha sido encontrado", Toast.LENGTH_LONG).show();
        else{
            txtNombreCargo.setText("Nombre de cargo: "+cargo.getNombreCargo());}}}
