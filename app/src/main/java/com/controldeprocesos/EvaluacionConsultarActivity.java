package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EvaluacionConsultarActivity extends Activity {

    private ControlBDGpo16 helper;
    private EditText edtNumEva;
    private TextView txtIdDocente, txtAlumnosEvaluados, txtCodMateria, txtTipo, txtFechaRealizacion, txtFechaPublicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_consultar);
        helper = new ControlBDGpo16(this);
        edtNumEva=(EditText)findViewById(R.id.edtNumEva);
        txtIdDocente=(TextView)findViewById(R.id.txtIdDocente);
        txtAlumnosEvaluados=(TextView)findViewById(R.id.txtAlumnosEvaluados);
        txtCodMateria=(TextView)findViewById(R.id.txtCodMateria);
        txtTipo=(TextView)findViewById(R.id.txtTipo);
        txtFechaRealizacion=(TextView)findViewById(R.id.txtFechaRealizacion);
        txtFechaPublicacion=(TextView)findViewById(R.id.txtFechaPublicacion);
        txtIdDocente.setText("");
        txtAlumnosEvaluados.setText("");
        txtCodMateria.setText("");
        txtTipo.setText("");
        txtFechaRealizacion.setText("");
        txtFechaPublicacion.setText("");

    }

    public void consultarEvaluacion(View v) {
        helper.abrir();
        Evaluacion evaluacion = helper.consultarEvaluacion(Integer.parseInt(edtNumEva.getText().toString()));
        helper.cerrar();
        if(evaluacion == null)
            Toast.makeText(this, "La evaluacion " +edtNumEva.getText().toString()+ " no ha sido encontrada", Toast.LENGTH_LONG).show();
        else{
            txtIdDocente.setText("Docente: "+evaluacion.getIdDocente());
            txtAlumnosEvaluados.setText("Alumnos evaluados: "+evaluacion.getAlumnosEvaluados());
            txtCodMateria.setText("Codigo: "+evaluacion.getCodMateria());
            txtTipo.setText("Tipo de evaluacion: "+evaluacion.getTipo());
            txtFechaRealizacion.setText("Fecha de realizacion: "+evaluacion.getFechaRealizacion());
            txtFechaPublicacion.setText("Fecha de publicacion: "+evaluacion.getFechaPublicacion());
        }
    }



}