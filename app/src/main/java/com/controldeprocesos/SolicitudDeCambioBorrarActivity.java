package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SolicitudDeCambioBorrarActivity extends AppCompatActivity {

    private ControlBDGpo16 helper;
    private EditText edtIdSolicitudCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_de_cambio_borrar);
        helper = new ControlBDGpo16(this);
        edtIdSolicitudCambio=(EditText)findViewById(R.id.edtIdSolicitudCambio);}

    public void eliminarSolicitudDeCambio(View v){
        String regEliminadas;
        SolicitudDeCambio solicitudDeCambio=new SolicitudDeCambio();
        solicitudDeCambio.setIdSolicitudCambio(Integer.parseInt(edtIdSolicitudCambio.getText().toString()));
        helper.abrir();
        regEliminadas=helper.eliminar(solicitudDeCambio);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();}}