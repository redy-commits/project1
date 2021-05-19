package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SolicitudDeImpresionesInsertarActivity extends Activity {

    ControlBDGpo16 helper;
    EditText edtIdSolicitud;
    EditText edtNumPaginas;
    EditText edtEstadoAprobado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_de_impresiones_insertar);
        helper = new ControlBDGpo16(this);
        edtIdSolicitud = (EditText) findViewById(R.id.edtIdSolicitud);
        edtNumPaginas = (EditText) findViewById(R.id.edtNumPaginas);
        edtEstadoAprobado = (EditText) findViewById(R.id.edtEstadoAprobado);
    }

    public void insertarSolicitudDeImpresiones(View v) {
        SolicitudDeImpresiones solicitud = new SolicitudDeImpresiones();
        String regInsertados;
        int idSolicitud = Integer.parseInt(edtIdSolicitud.getText().toString());
        int numPaginas = Integer.parseInt(edtNumPaginas.getText().toString());
        boolean estadoAprobado=Boolean.parseBoolean(edtEstadoAprobado.getText().toString());
        solicitud.setIdSolicitud(idSolicitud);
        solicitud.setNumPaginas(numPaginas);
        solicitud.setEstadoAprobado(estadoAprobado);
        helper.abrir();
        regInsertados=helper.insertarSolicitudDeImpresiones(solicitud);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }
    public void limpiarTexto(View v) {

        edtIdSolicitud.setText("");
        edtNumPaginas.setText("");
        edtEstadoAprobado.setText("");

    }

}