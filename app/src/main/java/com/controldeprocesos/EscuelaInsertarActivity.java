package com.controldeprocesos;

import android.os.Bundle;
import android.app.Activity;
import android.os.StrictMode;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

public class EscuelaInsertarActivity extends Activity {

    ControlBDGpo16 helper;
    EditText editcodEscuela;
    EditText editNombre;
    private String urlS= "https://cn18006pdm115.000webhostapp.com/p1/insertarEscuela.php";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_insertar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlBDGpo16(this);
        editcodEscuela = (EditText) findViewById(R.id. editcodEscuela);
        editNombre = (EditText) findViewById(R.id.editNombre);}

    public void insertarEscuela(View v) {
        int coEscuela= Integer.parseInt(editcodEscuela.getText().toString());
        String nombre=editNombre.getText().toString();
        String regInsertados;

        Escuela escuela=new Escuela();
        escuela.setcodEscuela(coEscuela);
        escuela.setNombre(nombre);
        helper.abrir();
        regInsertados=helper.insertar(escuela);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

        //Parte del servicio de consumo web.
        String url = null;
        String sentencia= "insert into escuela value ("+coEscuela+",'"+nombre+"');";
        sentencia=sentencia.replaceAll("\\s","%20");
        url = urlS+ "?sentencia="+sentencia;
        ControladorServicio.insertar(url, this);}

    public void limpiarTexto(View v){
        editcodEscuela.setText("");
        editNombre.setText("");}}