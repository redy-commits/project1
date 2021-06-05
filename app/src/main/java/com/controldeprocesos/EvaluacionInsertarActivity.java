package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Date;

public class EvaluacionInsertarActivity extends Activity {

    private ControlBDGpo16 helper;
    private EditText edtNumEva, edtIdDocente, edtAlumnosEvaluados, edtCodMateria, edtTipo, edtFechaRealizacion, edtFechaPublicacion;
    //Para el servicio web
    private String urlS= "https://cn18006pdm115.000webhostapp.com/p1/insertarEvaluacion.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_insertar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlBDGpo16(this);
        edtNumEva= (EditText)findViewById(R.id.edtNumEva);
        edtIdDocente= (EditText)findViewById(R.id.edtIdDocente);
        edtAlumnosEvaluados= (EditText)findViewById(R.id.edtAlumnosEvaluados);
        edtCodMateria= (EditText)findViewById(R.id.edtCodMateria);
        edtTipo= (EditText)findViewById(R.id.edtTipo);
        edtFechaRealizacion= (EditText)findViewById(R.id.edtFechaRealizacion);
        edtFechaPublicacion= (EditText)findViewById(R.id.edtFechaPublicacion);
    }

    public void insertarEvaluacion(View v) {
        int numEva=Integer.parseInt(edtNumEva.getText().toString());
        int idDocente=Integer.parseInt(edtIdDocente.getText().toString());
        int alumnosEvaluados=Integer.parseInt(edtAlumnosEvaluados.getText().toString());
        String codMateria=edtCodMateria.getText().toString();
        String tipo=edtTipo.getText().toString();
        Date fechaRealizacion=Date.valueOf(edtFechaRealizacion.getText().toString());
        Date fechaPublicacion=Date.valueOf(edtFechaPublicacion.getText().toString());
        String regInsertados;
        Evaluacion evaluacion=new Evaluacion();
        evaluacion.setNumEva(numEva);
        evaluacion.setIdDocente(idDocente);
        evaluacion.setAlumnosEvaluados(alumnosEvaluados);
        evaluacion.setCodMateria(codMateria);
        evaluacion.setTipo(tipo);
        evaluacion.setFechaRealizacion(fechaRealizacion);
        evaluacion.setFechaPublicacion(fechaPublicacion);
        helper.abrir();
        regInsertados=helper.insertar(evaluacion);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

        //servicio de consumo web.
        String url = null;
        url = urlS+ "?numEva=" +numEva+ "&idDocente="+idDocente+ "&alumnosEvaluados="+alumnosEvaluados+ "&codMateria="+codMateria+ "&tipo"+tipo+ "&fechaRealizacion"+fechaRealizacion+ "&fechaPublicacion"+fechaPublicacion;
        ControladorServicio.insertar(url, this);

    }
}