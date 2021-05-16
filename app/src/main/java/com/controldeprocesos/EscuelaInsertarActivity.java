package com.controldeprocesos;



import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;



public class EscuelaInsertarActivity extends Activity {

    ControlBDGpo16 helper;
    EditText editcodEscuela;
    EditText editNombre;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_insertar);
        helper = new ControlBDGpo16(this);
        editcodEscuela = (EditText) findViewById(R.id. editcodEscuela);
        editNombre = (EditText) findViewById(R.id.editNombre);
    }
    public void insertarEscuela(View v) {
        int coEscuela= Integer.parseInt(editcodEscuela.getText().toString());
        String nombre=editNombre.getText().toString();
        String regInsertados;
        Escuela escuela=new Escuela();
        escuela.setcodEscuela(coEscuela);
        escuela.setNombre(nombre);
        helper.abrir();
        regInsertados=helper.insertar(escuela);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }
    public void limpiarTexto(View v) {
        editcodEscuela.setText("");
        editNombre.setText("");

    }
}