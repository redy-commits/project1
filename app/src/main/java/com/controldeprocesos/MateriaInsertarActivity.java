package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MateriaInsertarActivity extends AppCompatActivity {

    private ControlBDGpo16 helper;
    private EditText edtCodMateria,edtCodEscuela,edtNombre;
    private String urlS= "https://cn18006pdm115.000webhostapp.com/p1/insertarMateria.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_insertar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlBDGpo16(this);
        edtCodMateria=(EditText)findViewById(R.id.edtIdCiclo);
        edtCodEscuela=(EditText)findViewById(R.id.edtAnio);
        edtNombre=(EditText)findViewById(R.id.edtNumCiclo);}

    public void insertarMateria(View v) {
        String codMateria=edtCodMateria.getText().toString();
        int codEscuela=Integer.parseInt(edtCodEscuela.getText().toString());
        String nombre=edtNombre.getText().toString();

        String regInsertados;
        Materia materia=new Materia();
        materia.setCodMateria(codMateria);
        materia.setCodEscuela(codEscuela);
        materia.setNombre(nombre);
        helper.abrir();
        regInsertados=helper.insertar(materia);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

        //Parte del servicio de consumo web.
        String url = null;
        String sentencia= "insert into materia value ('"+codMateria+"',"+codEscuela+",'"+nombre+"');";
        sentencia=sentencia.replaceAll("\\s","%20");
        url = urlS+ "?sentencia="+sentencia;
        ControladorServicio.insertar(url, this);}}