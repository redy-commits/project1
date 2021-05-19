package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LocalActualizarActivity extends Activity {

    private ControlBDGpo16 helper;
    private EditText edtIdLocal,edtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_actualizar);
        helper = new ControlBDGpo16(this);
        edtIdLocal=(EditText)findViewById(R.id.edtIdLocal);
        edtNombre=(EditText)findViewById(R.id.edtNombre);}

    public void actualizarLocal(View view) {
        Local local = new Local();
        local.setIdLocal(Integer.parseInt(edtIdLocal.getText().toString()));
        local.setNombre(edtNombre.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(local);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();}}
