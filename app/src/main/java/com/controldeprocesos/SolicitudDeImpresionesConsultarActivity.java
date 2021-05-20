package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SolicitudDeImpresionesConsultarActivity extends Activity {

    ControlBDGpo16 helper;
    EditText edtIdSolicitud;
    EditText edtNumPaginas;
    EditText edtEstadoAprobado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_de_impresiones_consultar);
        helper = new ControlBDGpo16(this);
        edtIdSolicitud = (EditText) findViewById(R.id.edtIdSolicitud);
        edtNumPaginas = (EditText) findViewById(R.id.edtNumPaginas);
        edtEstadoAprobado = (EditText) findViewById(R.id.edtEstadoAprobado);
    }

    public void consultarSolicitudDeImpresiones(View v) {
        helper.abrir();
        SolicitudDeImpresiones solicitud =   helper.consultarSolicitudDeImpresiones(Integer.parseInt(edtIdSolicitud.getText().toString()));
        helper.cerrar();
        if(solicitud == null)
            Toast.makeText(this, "Solicitud de impresion " + edtIdSolicitud.getText().toString() + " no encontrada", Toast.LENGTH_LONG).show();
        else {
            edtNumPaginas.setText(String.valueOf(solicitud.getNumPaginas()));

        }
    }

    public void limpiarTexto(View view) {
        edtIdSolicitud.setText("");
        edtNumPaginas.setText("");
        edtEstadoAprobado.setText("");
    }
}