package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class EscuelaBorrarActivity extends Activity {
    EditText editcodEscuela;
    ControlBDGpo16 controlhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_borrar);
        controlhelper=new ControlBDGpo16 (this);
        editcodEscuela=(EditText)findViewById(R.id.editcodEscuela);
    }

    public void eliminarEscuela(View v){
        String regEliminadas;
        Escuela escuela =new Escuela();
        escuela.setcodEscuela(editcodEscuela.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(escuela);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
