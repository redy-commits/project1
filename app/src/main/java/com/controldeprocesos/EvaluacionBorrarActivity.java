package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EvaluacionBorrarActivity extends AppCompatActivity {

    EditText edtNumEva;
    ControlBDGpo16 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservacion_borrar);
        helper=new ControlBDGpo16 (this);
        edtNumEva=(EditText)findViewById(R.id.edtNumEva);}

    public void eliminarEvaluacion(View v){
        String regEliminadas;
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNumEva(Integer.parseInt(edtNumEva.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminar(evaluacion);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();}
}