package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LocalBorrarActivity extends Activity {

    EditText edtIdLocal;
    ControlBDGpo16 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_borrar);
        helper=new ControlBDGpo16 (this);
        edtIdLocal=(EditText)findViewById(R.id.edtIdLocal);}

    public void eliminarLocal(View view) {
        String regEliminadas;
        Local local=new Local();
        local.setIdLocal(Integer.parseInt(edtIdLocal.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminar(local);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();}}
