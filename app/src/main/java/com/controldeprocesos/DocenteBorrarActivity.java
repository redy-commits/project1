package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteBorrarActivity extends AppCompatActivity {

    EditText editIdDocente;
    ControlBDGpo16 controlhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_borrar);
        controlhelper=new ControlBDGpo16 (this);
        editIdDocente =(EditText)findViewById(R.id.editIdDocente);
    }

    public void eliminarDocente(View v){
        String regEliminadas;
        Docente docente =new Docente();
        String idDocente = editIdDocente.getText().toString();
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarDocente(idDocente);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}