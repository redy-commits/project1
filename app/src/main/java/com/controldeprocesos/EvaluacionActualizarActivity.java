package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;

public class EvaluacionActualizarActivity extends Activity {

    private ControlBDGpo16 helper;
    private EditText edtNumEva, edtIdDocente, edtAlumnosEvaluados, edtCodMateria, edtTipo, edtFechaRealizacion, edtFechaPublicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_actualizar);
        helper = new ControlBDGpo16(this);
        edtNumEva=(EditText)findViewById(R.id.edtNumEva);
        edtIdDocente=(EditText)findViewById(R.id.edtIdDocente);
        edtAlumnosEvaluados=(EditText)findViewById(R.id.edtAlumnosEvaluados);
        edtCodMateria=(EditText)findViewById(R.id.edtCodMateria);
        edtTipo=(EditText)findViewById(R.id.edtTipo);
        edtFechaRealizacion=(EditText)findViewById(R.id.edtFechaRealizacion);
        edtFechaPublicacion=(EditText)findViewById(R.id.edtFechaPublicacion);

    }


    public void actualizarEvaluacion(View v) {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNumEva(Integer.parseInt(edtNumEva.getText().toString()));
        evaluacion.setIdDocente(Integer.parseInt(edtIdDocente.getText().toString()));
        evaluacion.setAlumnosEvaluados(Integer.parseInt(edtAlumnosEvaluados.getText().toString()));
        evaluacion.setCodMateria(edtCodMateria.getText().toString());
        evaluacion.setTipo(edtTipo.getText().toString());
        evaluacion.setFechaRealizacion(Date.valueOf(edtFechaRealizacion.getText().toString()));
        evaluacion.setFechaPublicacion(Date.valueOf(edtFechaPublicacion.getText().toString()));
        helper.abrir();
        String estado = helper.actualizar(evaluacion);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();}
}