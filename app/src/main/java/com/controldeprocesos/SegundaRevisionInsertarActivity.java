package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SegundaRevisionInsertarActivity extends AppCompatActivity {
    ControlBDGpo16 helper;
    EditText editIdDocente;
    EditText editIdOtroDocente;
    EditText editCarnet;
    EditText editNumEva;
    EditText editrespSociedadEstud;
    EditText editNotaDefinitiva;
    private String urlS= "https://cn18006pdm115.000webhostapp.com/p1/insertar2Rev.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_revision_insertar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlBDGpo16(this);
        editIdDocente = findViewById(R.id.editIdDocente);
        editIdOtroDocente = findViewById(R.id.editIdOtroDocente);
        editCarnet = findViewById(R.id.editCarnet);
        editNumEva = findViewById(R.id.editNumEva);
        editrespSociedadEstud = findViewById(R.id.editrespSociedadEstud);
        editNotaDefinitiva = findViewById(R.id.editNotaDefinitiva);}

    public void insertarSegundaRevision(View v){
        SegundaRevision segunda = new SegundaRevision();
        String idDocente=editIdDocente.getText().toString();
        String idOtroDocente=editIdOtroDocente.getText().toString();
        String carnet=editCarnet.getText().toString();
        String numeva=editNumEva.getText().toString();
        String respuesta=editrespSociedadEstud.getText().toString();
        String nota=editNotaDefinitiva.getText().toString();

        String regInsertados;
        segunda.setIdDocente(Integer.parseInt(idDocente));
        segunda.setIdOtroDocente(Integer.parseInt(idOtroDocente));
        segunda.setRespSociedadEstud(respuesta);
        segunda.setNotaDefinitiva(Float.parseFloat(nota));
        helper.abrir();
        regInsertados= String.valueOf(helper.insertarSegundaRevision(segunda,carnet,numeva));
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

        //Parte del servicio de consumo web.
        String url = null;
        String sentencia= "insert into segundaRevision (idExamen,respSociedadEstud,idDocente,idOtroDocente,notaDefinitiva) values ("+numeva+",'"+respuesta+"',"+idDocente+","+idOtroDocente+","+nota+");";
        sentencia=sentencia.replaceAll("\\s","%20");
        url = urlS+ "?sentencia="+sentencia;
        ControladorServicio.insertar(url, this);}}