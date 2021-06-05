package com.controldeprocesos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;

public class LocalInsertarActivity extends Activity {

    private ControlBDGpo16 helper;
    private EditText edtIdLocal,edtNombre;
    //Para el servicio web
    private String urlS= "https://cn18006pdm115.000webhostapp.com/p1/insertarLocal.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_insertar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        helper = new ControlBDGpo16(this);
        edtIdLocal=(EditText)findViewById(R.id.edtIdLocal);
        edtNombre=(EditText)findViewById(R.id.edtNombre);
    }

    public void insertarLocal(View v) {
        int idLocal=Integer.parseInt(edtIdLocal.getText().toString());
        String nombre=edtNombre.getText().toString();
        String regInsertados;
        Local local=new Local();
        local.setIdLocal(idLocal);
        local.setNombre(nombre);
        helper.abrir();
        regInsertados=helper.insertar(local);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

        //servicio de consumo web.
        String url = null;
        url = urlS+ "?idLocal=" +idLocal+ "&nombre="+nombre;
        ControladorServicio.insertar(url, this);
    }
}
