package com.controldeprocesos;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class RevisionInsertarActivity extends Activity {

    ControlBDGpo16 helper;
    EditText edtNumRev1, edtIdExamen, edtNuevaNota, edtObserv, edtAsistio;
    //Para el servicio web
    private String urlS= "https://cn18006pdm115.000webhostapp.com/p1/insertarRevision.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_insertar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlBDGpo16(this);
        edtNumRev1 = (EditText) findViewById(R.id.edtNumRev1);
        edtIdExamen = (EditText) findViewById(R.id.edtIdExamen);
        edtNuevaNota = (EditText) findViewById(R.id.edtNuevaNota);
        edtObserv = (EditText) findViewById(R.id.edtObserv);
        edtAsistio = (EditText) findViewById(R.id.edtAsistio);
    }

    public void insertarRevision(View v) {
        int numRev1=Integer.parseInt(edtNumRev1.getText().toString());
        int idExamen=Integer.parseInt(edtIdExamen.getText().toString());
        boolean asistio=Boolean.parseBoolean(edtAsistio.getText().toString());
        float nuevaNota=Float.parseFloat(edtNuevaNota.getText().toString());
        String observ=edtObserv.getText().toString();
        String regInsertados;
        Revision revision= new Revision();
        revision.setNumRev1(numRev1);
        revision.setIdExamen(idExamen);
        revision.setNuevaNota(nuevaNota);
        revision.setObserv(observ);
        revision.setAsistio(asistio);
        helper.abrir();
        regInsertados=helper.insertarRevision(revision);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

        //servicio de consumo web.
        String url = null;
        url = urlS+ "?numRev1=" +numRev1+ "&idExamen="+idExamen+ "&observ="+observ+ "&asistio="+asistio;
        ControladorServicio.insertar(url, this);
    }

}


