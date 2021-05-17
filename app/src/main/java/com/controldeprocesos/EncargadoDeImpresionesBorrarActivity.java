package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoDeImpresionesBorrarActivity extends AppCompatActivity {

    EditText editIdEncargadoDeImpresiones;
    ControlBDGpo16 controlhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_de_impresiones_borrar);
        controlhelper=new ControlBDGpo16 (this);
        editIdEncargadoDeImpresiones =(EditText)findViewById(R.id.editIdEncargadoDeImpresiones);
    }

    public void eliminarEncargadoDeImpresiones(View v){
        String regEliminadas;
        EncargadoDeImpresiones encargado =new EncargadoDeImpresiones();
        String idEncargado = editIdEncargadoDeImpresiones.getText().toString();
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarEncargado(idEncargado);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}