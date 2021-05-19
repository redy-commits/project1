package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SolicitudDeImpresionesBorrarActivity extends Activity {

    ControlBDGpo16 controlhelper;
    EditText edtIdSolicitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_de_impresiones_borrar);
        controlhelper=new ControlBDGpo16 (this);
        edtIdSolicitud =(EditText)findViewById(R.id.edtIdSolicitud);
    }

    public void eliminarSolicitudDeImpresiones(View v){
        String regEliminadas;
        SolicitudDeImpresiones solicitud =new SolicitudDeImpresiones();
        solicitud.setIdSolicitud(Integer.parseInt(edtIdSolicitud.getText().toString()));
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarSolicitudDeImpresiones(solicitud);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

}