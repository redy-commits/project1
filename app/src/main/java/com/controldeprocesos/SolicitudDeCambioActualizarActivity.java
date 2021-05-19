package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SolicitudDeCambioActualizarActivity extends AppCompatActivity{

    private ControlBDGpo16 helper;
    private EditText edtIdSolicitudCambio;
    private CheckBox cbEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_de_cambio_actualizar);
        helper = new ControlBDGpo16(this);
        cbEstado=(CheckBox)findViewById(R.id.cbEstadoAP);
        cbEstado.setChecked(true);
        cbEstado.setClickable(false);
        edtIdSolicitudCambio=(EditText)findViewById(R.id.edtIdSolicitudCambio);}

    public void actualizarSolicitudDeCambio(View v) {
        SolicitudDeCambio solicitudDeCambio = new SolicitudDeCambio();
        solicitudDeCambio.setIdSolicitudCambio(Integer.parseInt(edtIdSolicitudCambio.getText().toString()));
        solicitudDeCambio.setEstadoAprobado(true);
        helper.abrir();
        String estado = helper.actualizar(solicitudDeCambio);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();}}