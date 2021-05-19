package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SolicitudDeImpresionesActualizarActivity extends Activity {

    ControlBDGpo16 helper;
    EditText edtIdSolicitud;
    EditText edtNumPaginas;
    EditText edtEstadoAprobado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_de_impresiones_actualizar);
        helper = new ControlBDGpo16(this);
        edtIdSolicitud = (EditText) findViewById(R.id.edtIdSolicitud);
        edtNumPaginas = (EditText) findViewById(R.id.edtNumPaginas);
        edtEstadoAprobado = (EditText) findViewById(R.id.edtEstadoAprobado);
    }
    public void actualizarSolicitudDeImpresiones(View v) {
        SolicitudDeImpresiones solicitud = new SolicitudDeImpresiones();
        solicitud.setIdSolicitud(Integer.parseInt(edtIdSolicitud.getText().toString()));
        solicitud.setNumPaginas(Integer.parseInt(edtNumPaginas.getText().toString()));
        solicitud.setEstadoAprobado(Boolean.parseBoolean(edtEstadoAprobado.getText().toString()));
        helper.abrir();
        String estado = helper.actualizarSolicitudDeImpresiones(solicitud);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        edtIdSolicitud.setText("");
        edtNumPaginas.setText("");
        edtEstadoAprobado.setText("");
    }
}