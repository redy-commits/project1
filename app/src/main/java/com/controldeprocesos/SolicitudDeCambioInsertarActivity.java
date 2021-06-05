package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.sql.Date;

public class SolicitudDeCambioInsertarActivity extends AppCompatActivity {

    private ControlBDGpo16 helper;
    private EditText edtIdSolicitudCambio,edtIdExamen,edtIdRazon,edtNuevaNota;
    private String urlS= "https://cn18006pdm115.000webhostapp.com/p1/insertarSoliCambio.php";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_de_cambio_insertar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlBDGpo16(this);
        edtIdSolicitudCambio=(EditText)findViewById(R.id.edtIdSolicitudCambio);
        edtIdExamen=(EditText)findViewById(R.id.edtIdExamen);
        edtIdRazon=(EditText)findViewById(R.id.edtIdRazon);
        edtNuevaNota=(EditText)findViewById(R.id.edtNuevaNota);}

    public void insertarReservacion(View v) {
        int idSolicitudCambio=Integer.parseInt(edtIdSolicitudCambio.getText().toString());
        int idExamen=Integer.parseInt(edtIdExamen.getText().toString());
        int idRazon=Integer.parseInt(edtIdRazon.getText().toString());
        float nuevaNota=Float.parseFloat(edtNuevaNota.getText().toString());

        String regInsertados;
        SolicitudDeCambio solicitudDeCambio=new SolicitudDeCambio();
        solicitudDeCambio.setIdSolicitudCambio(idSolicitudCambio);
        solicitudDeCambio.setIdExamen(idExamen);
        solicitudDeCambio.setIdRazon(idRazon);
        solicitudDeCambio.setNuevaNota(nuevaNota);
        solicitudDeCambio.setEstadoAprobado(false);
        helper.abrir();
        regInsertados=helper.insertar(solicitudDeCambio);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

        //Parte del servicio de consumo web.
        String url = null;
        url = urlS+ "?idSolicitudCambio=" +idSolicitudCambio+ "&idExamen="+idExamen+ "&idRazon=" +idRazon+ "&nuevaNota=" +nuevaNota+ "&estadoAprobado=0";
        ControladorServicio.insertar(url, this);}}